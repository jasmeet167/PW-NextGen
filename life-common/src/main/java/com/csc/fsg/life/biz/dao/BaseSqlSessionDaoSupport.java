package com.csc.fsg.life.biz.dao;

import java.util.List;

import org.apache.ibatis.executor.result.DefaultResultHandler;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.csc.fsg.life.biz.bo.BaseBusinessObject;
import com.csc.fsg.life.tools.xml.simple.SimpleXMLEncoder;

/* Modifications: T0166, T0199 */

/**
 * Extends the MyBatis-Spring class <code>SqlSessionDaoSupport</code> in order
 * to simplify the data access logic and to include the use of a ResultHandler.
 * The ResultHandler, in this case, will be called for each result object in a
 * ResultSet. It will then set each result object's initial state, so that
 * subsequent processing can easily determine if the object has been updated.
 * <p>
 * With very few exceptions, all methods in this class and in its subclasses
 * should not communicate directly with <code>SqlSession</code>, Instead, they
 * should use <code>SqlSessionTemplateDelegate</code>, which overrides methods
 * for access to <code>SqlSession</code>. Both of these custom classes wrap JDBC
 * access with performance logging logic.
 */
abstract public class BaseSqlSessionDaoSupport extends SqlSessionDaoSupport
{
    public void insert(String statement, Object model) {       
        getSqlSessionTemplateDelegate().insert(statement, model);
        
        // update the initial state of the object after successful insert
        if (model instanceof BaseBusinessObject) {
            BaseBusinessObject bo = (BaseBusinessObject)model;           
            String state = new SimpleXMLEncoder().buildXML(bo);
            bo.setInitialState(state);
        }
    }
    
    public void update(String statement, Object model) {
        getSqlSessionTemplateDelegate().update(statement, model);
    }
    
    public void delete(String statement, Object model) {
        getSqlSessionTemplateDelegate().delete(statement, model);
    }
    
    public <T> T selectOne(String statement, Object model) {
        return getSqlSessionTemplateDelegate().selectOne(statement, model);
    }
    
	@SuppressWarnings("unchecked")
	public <E> List<E> selectList(String statement, Object model) {
    	DefaultResultHandler resultHandler = new ObjectStateResultHandler();
    	getSqlSessionTemplateDelegate().select(statement, model, resultHandler);
    	return (List<E>) resultHandler.getResultList();
    }

	/**
	 * Create and return a new instance of the utility class wrapping
	 * {@link org.apache.ibatis.session.SqlSession}, which
	 * contains logic supporting performance logging of JDBC operations.
	 * 
	 * @return new instance of the delegate wrapper.
	 */
	protected SqlSessionTemplateDelegate getSqlSessionTemplateDelegate()
	{
		return new SqlSessionTemplateDelegate(getSqlSession());
	}

}