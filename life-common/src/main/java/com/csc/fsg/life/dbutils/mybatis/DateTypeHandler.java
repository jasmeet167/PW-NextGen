package com.csc.fsg.life.dbutils.mybatis;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.ibatis.type.DateOnlyTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

/* Modifications: T0199 */

/**
 * This class handles date values by translating SQL Dates according to the
 * following algorithm:
 * <ul>
 *    <li>If the value in database table is null, then return null</li>
 *    <li>If the value in database is 1/1/0001, then return null</li>
 *    <li>Otherwise return an instance of java.util.Date, with value matching that
 *        in the database.</li>
 * </ul>
 */
@MappedTypes(value=Date.class)
@MappedJdbcTypes(value=JdbcType.DATE, includeNullJdbcType=true)
public class DateTypeHandler
	extends DateOnlyTypeHandler
{
	@Override
	public Date getNullableResult(ResultSet rs, String columnName)
		throws SQLException
	{
		java.sql.Date sqlDate = rs.getDate(columnName);
		if (sqlDate != null) {
			return createLogicalDate(sqlDate);
		}
		return null;
	}

	@Override
	public Date getNullableResult(ResultSet rs, int columnIndex)
		throws SQLException
	{
		java.sql.Date sqlDate = rs.getDate(columnIndex);
		if (sqlDate != null) {
			return createLogicalDate(sqlDate);
		}
		return null;
	}

	@Override
	public Date getNullableResult(CallableStatement cs, int columnIndex)
		throws SQLException
	{
		java.sql.Date sqlDate = cs.getDate(columnIndex);
		if (sqlDate != null) {
			return createLogicalDate(sqlDate);
		}
		return null;
	}

	private Date createLogicalDate(java.sql.Date sqlDate)
	{
		if (sqlDate.toString().equals("0001-01-01"))
			return null;
		else
			return new Date(sqlDate.getTime());
	}
}
