package com.csc.fsg.life.dbutils.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import com.csc.fsg.life.common.util.DateHelper;

/* Modifications: T0199 */

/**
 * This class handles taking a date stored as a string from a DB and converting
 * it to a Date object for use in code when using MyBatis. The date format is a
 * number in the format of yyyyMMDD.
 */
@MappedTypes(value=Date.class)
@MappedJdbcTypes(value={JdbcType.CHAR,JdbcType.VARCHAR}, includeNullJdbcType=true)
public class CharDateTypeHandler
	extends BaseTypeHandler<Date>
{
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType)
		throws SQLException
	{
		ps.setString(i, DateHelper.toFormattedNumericString(parameter));
	}

	@Override
	public Date getNullableResult(ResultSet rs, String columnName)
		throws SQLException
	{
		String result = rs.getString(columnName);
		if (result == null)
			return null;

		return DateHelper.parseDate(result);
	}

	@Override
	public Date getNullableResult(ResultSet rs, int columnIndex)
		throws SQLException
	{
		String result = rs.getString(columnIndex);
		if (result == null)
			return null;

		return DateHelper.parseDate(result);
	}

	@Override
	public Date getNullableResult(CallableStatement cs, int columnIndex)
		throws SQLException
	{
		String result = cs.getString(columnIndex);
		if (result == null)
			return null;

		return DateHelper.parseDate(result);
	}
}
