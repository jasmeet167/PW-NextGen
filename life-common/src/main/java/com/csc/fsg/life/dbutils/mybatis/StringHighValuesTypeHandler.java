package com.csc.fsg.life.dbutils.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import com.csc.fsg.life.common.config.ApplicationEnvironmentBean;

/* Modifications: T0199 */

/**
 * Custom MyBatis type handler, used to facilitate access to CHAR fields,
 * which may be partially or fully filled with high-value characters.
 * Any such characters are replaced with '*'. Additionally, on input strings are
 * trimmed and upshifted.
 */
@MappedTypes(value=String.class)
@MappedJdbcTypes(value={JdbcType.CHAR,JdbcType.VARCHAR}, includeNullJdbcType=true)
public class StringHighValuesTypeHandler
	extends BaseTypeHandler<String>
{
	public final static char WILD_CARD_CHARACTER = '*';

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
		throws SQLException
	{
		char highValuesChar = getHighValuesCharacter();
		String value = (String) parameter;
		char[] chars = value.toCharArray();
		for (int j = 0, n = chars.length; j < n; j++)
			if (chars[j] == WILD_CARD_CHARACTER)
				chars[j] = highValuesChar;
		ps.setString(i, new String(chars));
	}

	@Override
	public String getNullableResult(ResultSet rs, String columnName)
		throws SQLException
	{
		String result = rs.getString(columnName);
		if (result == null)
			return null;

		char highValuesChar = getHighValuesCharacter();

		char[] chars = result.toCharArray();
		for (int i = 0, n = chars.length; i < n; i++)
			if (chars[i] == highValuesChar)
				chars[i] = WILD_CARD_CHARACTER;

		result = new String(chars);
		return result.trim().toUpperCase();
	}

	@Override
	public String getNullableResult(ResultSet rs, int columnIndex)
		throws SQLException
	{
		String result = rs.getString(columnIndex);
		if (result == null)
			return null;

		char highValuesChar = getHighValuesCharacter();

		char[] chars = result.toCharArray();
		for (int i = 0, n = chars.length; i < n; i++)
			if (chars[i] == highValuesChar)
				chars[i] = WILD_CARD_CHARACTER;

		result = new String(chars);
		return result.trim().toUpperCase();
	}

	@Override
	public String getNullableResult(CallableStatement cs, int columnIndex)
		throws SQLException
	{
		String result = cs.getString(columnIndex);
		if (result == null)
			return null;

		char highValuesChar = getHighValuesCharacter();

		char[] chars = result.toCharArray();
		for (int i = 0, n = chars.length; i < n; i++)
			if (chars[i] == highValuesChar)
				chars[i] = WILD_CARD_CHARACTER;

		result = new String(chars);
		return result.trim().toUpperCase();
	}

	/**
	 * @return Value of the character, which is to be used for matching of
	 *         high-values in key columns, is obtained from the Application
	 *         Environment associated with the current session.
	 */
	private char getHighValuesCharacter()
	{
		ApplicationEnvironmentBean environment = ApplicationEnvironmentBean.getEnvironment();
		return environment.getHighValueChar();
	}
}
