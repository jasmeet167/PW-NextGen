package com.csc.fsg.life.dbutils.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeException;

/* Modifications: WMA-916, T0199 */

/**
 * Custom MyBatis type handler, used to facilitate access to CHAR fields.
 * These String fields will be upshifted but no trim processing will
 * occur.
 */
@MappedTypes(value=String.class)
@MappedJdbcTypes(value={JdbcType.CHAR,JdbcType.VARCHAR}, includeNullJdbcType=true)
public class StringNonTrimUpperCaseTypeHandler
	extends BaseTypeHandler<String>
{
	@Override
	public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
		throws SQLException
	{
		if (parameter == null) {
			try {
				ps.setInt(i, Types.CHAR);
			}
			catch (SQLException e) {
				throw new TypeException("Error setting null for parameter #"
									   + i
									   + " with JdbcType "
									   + jdbcType
									   + " . "
									   + "Try setting a different JdbcType for this parameter or a different jdbcTypeForNull configuration property. "
									   + "Cause: "
									   + e, e);
			}
		}
		else {
			setNonNullParameter(ps, i, parameter, jdbcType);
		}
	}

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
			return columnValue.toUpperCase();
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
			return columnValue.toUpperCase();
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
			return columnValue.toUpperCase();
		}
		else {
			return null;
		}
	}
}