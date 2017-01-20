package com.csc.fsg.life.security.authorization.strategy;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.acls.domain.AclAuthorizationStrategyImpl;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.SidRetrievalStrategyImpl;
import org.springframework.security.acls.model.Acl;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.acls.model.SidRetrievalStrategy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

/* Modifications: ENH1220 */

/**
 * This is essentially a copy of the Spring framework's class
 * {@code AclAuthorizationStrategyImpl}, with two minor modifications:
 * <ol>
 * <li>
 * Since applications based on <i>life-common</i> architecture are based on JAAS
 * authentication, {@code authentication.getAuthorities()} returns a list of
 * {@code JaasGrantedAuthority}. The injected instances of
 * {@code GrantedAuthority} are implemented using {@code SimpleGrantedAuthority}
 * , because injection of {@code JaasGrantedAuthority} would require specific
 * Principal. Because of this,
 * {@code authentication.getAuthorities().contains(requiredAuthority)} in method
 * {@code securityCheck} of the super class never finds matching authorities.
 * The logic is customized in this class to allow such direct comparison.</li>
 * <li>
 * {@code NotFoundException} is caught in order not to mask
 * {@code AccessDeniedException} if the user is not authorized.</li>
 * </ol>
 */
public class AclAuthorizationCustomStrategyImpl
	extends AclAuthorizationStrategyImpl
{
    private final GrantedAuthority gaGeneralChanges;
    private final GrantedAuthority gaModifyAuditing;
    private final GrantedAuthority gaTakeOwnership;
    private SidRetrievalStrategy sidRetrievalStrategy = new SidRetrievalStrategyImpl();

    /**
     * Constructor. The only mandatory parameter relates to the system-wide {@link GrantedAuthority} instances that
     * can be held to always permit ACL changes.
     *
     * @param auths the <code>GrantedAuthority</code>s that have
     * special permissions (index 0 is the authority needed to change
     * ownership, index 1 is the authority needed to modify auditing details,
     * index 2 is the authority needed to change other ACL and ACE details) (required)
     * <p>
     * Alternatively, a single value can be supplied for all three permissions.
     */
    public AclAuthorizationCustomStrategyImpl(GrantedAuthority... auths) {
    	super(auths);

        Assert.isTrue(auths != null && (auths.length == 3 || auths.length == 1),
                "One or three GrantedAuthority instances required");
        if (auths.length == 3) {
            gaTakeOwnership = auths[0];
            gaModifyAuditing = auths[1];
            gaGeneralChanges = auths[2];
        } else {
            gaTakeOwnership = gaModifyAuditing = gaGeneralChanges = auths[0];
        }
    }

    @Override
	public void securityCheck(Acl acl, int changeType) {
        if ((SecurityContextHolder.getContext() == null)
            || (SecurityContextHolder.getContext().getAuthentication() == null)
            || !SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            throw new AccessDeniedException("Authenticated principal required to operate with ACLs");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if authorized by virtue of ACL ownership
        // Sid currentUser = new PrincipalSid(authentication);

        // if (currentUser.equals(acl.getOwner())
        //        && ((changeType == CHANGE_GENERAL) || (changeType == CHANGE_OWNERSHIP))) {
        //    return;
        //}

        // Not authorized by ACL ownership; try via adminstrative permissions
        GrantedAuthority requiredAuthority;

        if (changeType == CHANGE_AUDITING) {
            requiredAuthority = this.gaModifyAuditing;
        } else if (changeType == CHANGE_GENERAL) {
            requiredAuthority = this.gaGeneralChanges;
        } else if (changeType == CHANGE_OWNERSHIP) {
            requiredAuthority = this.gaTakeOwnership;
        } else {
            throw new IllegalArgumentException("Unknown change type");
        }

        // Iterate this principal's authorities to determine right
        String requiredAuthorityRole = requiredAuthority.getAuthority().toUpperCase();
        for (GrantedAuthority authority : authentication.getAuthorities()) {
        	if (requiredAuthorityRole.equals(authority.getAuthority()))
        		return;
        }

        try {
	        // Try to get permission via ACEs within the ACL
	        List<Sid> sids = sidRetrievalStrategy.getSids(authentication);
	
	        if (acl.isGranted(Arrays.asList(BasePermission.ADMINISTRATION), sids, false)) {
	            return;
	        }
        }
        catch (NotFoundException e) {
        	// Fall-through to the exception thrown below
        }

        throw new AccessDeniedException(
                "Principal does not have required ACL permissions to perform requested operation");
    }

    @Override
	public void setSidRetrievalStrategy(SidRetrievalStrategy sidRetrievalStrategy) {
        Assert.notNull(sidRetrievalStrategy, "SidRetrievalStrategy required");
        this.sidRetrievalStrategy = sidRetrievalStrategy;
    }
}
