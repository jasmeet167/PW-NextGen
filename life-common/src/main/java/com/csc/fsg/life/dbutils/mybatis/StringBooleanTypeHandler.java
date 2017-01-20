package com.csc.fsg.life.dbutils.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeException;

/* Modifications: T0199 */

/**
 * This class handles taking a string from a DB and converting it to a Boolean
 * object for use in code when using MyBatis. Valid values for the database are:
 * Y or N.
 */
@MappedTypes(value=Boolean.class)
@MappedJdbcTypes(value={JdbcType.CHAR,JdbcType.VARCHAR}, includeNullJdbcType=true)
public class StringBooleanTypeHandler
	extends BaseTypeHandler<Boolean>
{
	@Override
	public void setParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType)
		throws SQLException
	{
		if (parameter == null) {
			try {
				ps.setString(i, "N");
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
	public Boolean getResult(ResultSet rs, String columnName)
		throws SQLException
	{
		Boolean result = getNullableResult(rs, columnName);
		if (rs.wasNull()) {
			return Boolean.FALSE;
		}
		else {
			return result;
		}
	}

	@Override
	public Boolean getResult(ResultSet rs, int columnIndex)
		throws SQLException
	{
		Boolean result = getNullableResult(rs, columnIndex);
		if (rs.wasNull()) {
			return Boolean.FALSE;
		}
		else {
			return result;
		}
	}

	@Override
	public Boolean getResult(CallableStatement cs, int columnIndex)
		throws SQLException
	{
		Boolean result = getNullableResult(cs, columnIndex);
		if (cs.wasNull()) {
			return Boolean.FALSE;
		}
		else {
			return result;
		}
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType)
		throws SQLException
	{
		ps.setString(i, parameter.equals(Boolean.TRUE) ? "Y" : "N");
	}

	@Override
	public Boolean getNullableResult(ResultSet rs, String columnName)
		throws SQLException
	{
		String result = rs.getString(columnName);
		if (result != null)
			return (result.equalsIgnoreCase("Y") ? Boolean.TRUE : Boolean.FALSE);

		return Boolean.FALSE;
	}

	@Override
	public Boolean getNullableResult(ResultSet rs, int columnIndex)
		throws SQLException
	{
		String result = rs.getString(columnIndex);
		if (result != null)
			return (result.equalsIgnoreCase("Y") ? Boolean.TRUE : Boolean.FALSE);

		return Boolean.FALSE;
	}

	@Override
	public Boolean getNullableResult(CallableStatement cs, int columnIndex)
		throws SQLException
	{
		String result = cs.getString(columnIndex);
		if (result != null)
			return (result.equalsIgnoreCase("Y") ? Boolean.TRUE : Boolean.FALSE);

		return Boolean.FALSE;
	}
}
