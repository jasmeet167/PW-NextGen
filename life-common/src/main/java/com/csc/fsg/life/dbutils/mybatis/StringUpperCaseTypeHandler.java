package com.csc.fsg.life.dbutils.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

/* Modifications: T0199 */

/**
 * This class handles taking a string from a DB and converting it to a upper
 * case string object for use in code when using MyBatis.
 */
@MappedTypes(value = String.class)
@MappedJdbcTypes(value={JdbcType.CHAR,JdbcType.VARCHAR}, includeNullJdbcType=true)
public class StringUpperCaseTypeHandler
	extends BaseTypeHandler<String>
{
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
		throws SQLException
	{
		ps.setString(i, parameter.toUpperCase());
	}

	@Override
	public String getNullableResult(ResultSet rs, String columnName)
		throws SQLException
	{
		String columnValue = rs.getString(columnName);
		if (columnValue != null) {
			return columnValue.trim().toUpperCase();
		}
		else {
			return null;
		}
	}

	@Override
	public String getNullableResult(ResultSet rs, int columnIndex)
		throws SQLException
	{
		String columnValue = rs.getString(columnIndex);
		if (columnValue != null) {
			return columnValue.trim().toUpperCase();
		}
		else {
			return null;
		}
	}

	@Override
	public String getNullableResult(CallableStatement cs, int columnIndex)
		throws SQLException
	{
		String columnValue = cs.getString(columnIndex);
		if (columnValue != null) {
			return columnValue.trim().toUpperCase();
		}
		else {
			return null;
		}
	}
}
