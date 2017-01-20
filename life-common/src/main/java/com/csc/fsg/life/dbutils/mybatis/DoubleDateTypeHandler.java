package com.csc.fsg.life.dbutils.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import com.csc.fsg.life.common.util.DateHelper;

/* Modifications: T0199 */

/**
 * This class handles taking a date stored as a number from a DB and converting
 * it to a Date object for use in code when using MyBatis. The date format is a
 * number in the format of yyyyMMDD.
 */
@MappedTypes(value=Date.class)
@MappedJdbcTypes(value=JdbcType.DECIMAL, includeNullJdbcType=true)
public class DoubleDateTypeHandler
	extends BaseTypeHandler<Date>
{
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType)
		throws SQLException
	{
		ps.setInt(i, DateHelper.toInt(parameter));
	}

	@Override
	public Date getNullableResult(ResultSet rs, String columnName)
		throws SQLException
	{
		int result = rs.getInt(columnName);

		try {
			return DateHelper.parseDate9s(String.valueOf(result), "yyyyMMdd");
		}
		catch (ParseException e) {
			return null;
		}
	}

	@Override
	public Date getNullableResult(ResultSet rs, int columnIndex)
		throws SQLException
	{
		int result = rs.getInt(columnIndex);

		try {
			return DateHelper.parseDate9s(String.valueOf(result), "yyyyMMdd");
		}
		catch (ParseException e) {
			return null;
		}
	}

	@Override
	public Date getNullableResult(CallableStatement cs, int columnIndex)
		throws SQLException
	{
		int result = cs.getInt(columnIndex);

		try {
			return DateHelper.parseDate9s(String.valueOf(result), "yyyyMMdd");
		}
		catch (ParseException e) {
			return null;
		}
	}
}
