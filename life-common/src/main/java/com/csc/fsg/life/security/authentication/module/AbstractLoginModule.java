package com.csc.fsg.life.security.authentication.module;

import java.security.Principal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

/**
 * Template login module that aids in the maintenance of state appropriately.
 */
@SuppressWarnings("unchecked")
public abstract class AbstractLoginModule implements LoginModule
{
    // Injected State, must be saved for subsequent use
    private Subject         _subject;
    private CallbackHandler _callbackHandler;
    private Map             _sharedState;
    private Map             _options;

    // Local state, must be cleared at abort or logout
    private boolean         _loginSuccess;
    private Set<Principal>             _principals;

    public final boolean abort() throws LoginException
    {
        cleanupState();

        return true;
    }

    public final boolean commit() throws LoginException
    {
        boolean loginSuccess = _loginSuccess;

        reset();

        if (loginSuccess)
            loadPrincipals(_sharedState, _options);

        return true;
    }

    /**
     * Subclasses must implement this method to load the user and role principals using the
     * {@link #addPrincipal(Principal)} method. This method will be invoked when this module is
     * committed and the login was successful.
     * 
     * @param sharedState
     *            the state shared between all login modules.
     * @param options
     *            this module's configuration properties.
     */
    protected abstract void loadPrincipals(Map sharedState, Map options);

    /**
     * Subclasses must use this method to load the user and role principal into this module's
     * subject.
     */
    protected final void addPrincipal(Principal principal)
    {
        _subject.getPrincipals().add(principal);
        _principals.add(principal);
    }

    public final boolean login() throws LoginException
    {
        try
        {
            _loginSuccess = login(_callbackHandler, _sharedState, _options);

            return _loginSuccess;
        }
        catch (LoginException e)
        {
            _loginSuccess = false;

            throw e;
        }
    }

    /**
     * Subclasses use this method to implement the login behavior.
     * 
     * @param handler
     *            the callback handler that must be used to retrieve the user ID and password.
     * @param sharedState
     *            state that is shared between all modules.
     * @param options
     *            properties specific to this login module.
     * @return true when the login is successful, false when this login module needs to be ignored.
     * @throws LoginException
     *             when this login module failed.
     */
    protected abstract boolean login(CallbackHandler handler, Map sharedState, Map options)
        throws LoginException;

    public final boolean logout() throws LoginException
    {
        cleanupState();

        return true;
    }

    private void reset()
    {
        // subject is shared between login modules and hence it has to be cleared of the principals
        // and credentials specific to this login module, but this module does not assign any
        // credentials and hence only the principals.
        for (Iterator itr = _subject.getPrincipals().iterator(); itr.hasNext();)
        {
            Object prin = itr.next();

            if (_principals.contains(prin))
                itr.remove();
        }

        _principals = new HashSet<Principal>();
        _loginSuccess = false;
    }

    private void cleanupState()
    {
        reset();
        cleanup();
    }

    /**
     * Subclasses must implement this method to clean up any state cached locally.
     */
    protected abstract void cleanup();

    /**
     * This module does not use any shared state and so it is ignored.
     * 
     * @see LoginModule#initialize(javax.security.auth.Subject,
     *      javax.security.auth.callback.CallbackHandler, java.util.Map, java.util.Map)
     */
    public final void initialize(Subject subject, CallbackHandler callbackHandler, Map sharedState,
        Map options)
    {
        _subject = subject;
        _callbackHandler = callbackHandler;
        _sharedState = sharedState;
        _options = options;
        _principals = new HashSet<Principal>();
    }
}
