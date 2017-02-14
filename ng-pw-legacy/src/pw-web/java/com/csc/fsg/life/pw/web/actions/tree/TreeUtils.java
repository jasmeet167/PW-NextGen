/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.web.actions.tree;

import java.util.*;
import java.sql.*;

import com.csc.fsg.life.pw.common.StructureNode;
import com.csc.fsg.life.pw.web.io.*;
import com.csc.fsg.life.pw.web.log.PWServerLogManager;

import org.apache.commons.logging.Log;


/* Modifications: T0091, ENH962, T0106 */

/**
 * Class TreeUtils
 * 
 * @author CSC-FSG,E.Hartford
 * @version PW 2.0 , 09-24-2002
 */

public final class TreeUtils {

	private static Log _log = PWServerLogManager.getLog(TreeUtils.class
	        .getPackage().getName());

	private TreeUtils() {
	}

	/**
	 * Method getNodeId
	 * 
	 * @param productPrefix
	 * @param planType
	 * @param tableId
	 * @param var
	 * @param levelInd
	 * @param parentNodeId
	 * @return
	 */

	// ENH962 - added getStructureNode, removed getNodeId
	public static StructureNode getStructureNode(String productPrefix,
	        String planType, String tableId, String var, String levelInd,
	        String parentNodeId) {

		// parentNode if has a parent, parent's nodeId else 0
		if (levelInd.equals("1") && !productPrefix.equals("N")) {
			parentNodeId = "0";
		}
		StructureNode node = StructureTableCache.getInstance().getNode(productPrefix,
				planType, tableId, var, levelInd, parentNodeId);

		if ( node == null ) {
			List<StructureNode> allNodes = StructureTableCache.getInstance().getStructureNodes(productPrefix, planType);
			StructureNode parentNode = null;
			for ( StructureNode sn : allNodes ) {
				if ( Integer.toString(sn.getNodeId()).equals(parentNodeId) ) {
					parentNode = sn;
					break;
				}
			}
			String parentTableId = "";
			if ( parentNode != null )
				parentTableId = parentNode.getTableId();
			_log.error("getStructureNode: no node for pp=<" + productPrefix + ">"
					+ " plan type=<" + planType + "> tableId=<" + tableId + ">"
					+ " var=<" + var + "> level=<" + levelInd + "> parentNode=<" + parentNodeId + ">"
					+ " parent=<" + parentTableId + ">");
			node = new StructureNode(111111);
		}

		return node;
	}

	/**
	 * Method writeWhereKey
	 * 
	 * @param treeKey
	 * @param key
	 * @param whereClause
	 */

	public static void writeWhereKey(HashMap treeKey, String key,
	        StringBuffer whereClause) {

		if (treeKey.containsKey(key)) {
			Object value = treeKey.get(key);

			if (key.equals("company_code") && (value instanceof Vector)) {
				whereClause.append(" and ").append(key).append(" in");
				whereClause.append(" (").append(vectorToString(value)).append(
				        ") ");
			} else {
				whereClause.append(" and ").append(key).append(" =");
				whereClause.append(" '").append(value).append("' ");
			}
		}
	}

	/**
	 * Method vectorToString
	 * 
	 * @param list
	 * @return
	 */

	public static String vectorToString(Object list) {

		Vector items = (Vector) list;
		StringBuffer sb = new StringBuffer(50);

		for (int i = 0; i < items.size(); i++) {
			sb.append(",'");
			sb.append(items.elementAt(i));
			sb.append("'");
		}
		if (sb.length() != 0) {
			return sb.substring(1);
		}
		return null;
	}

	/**
	 * Method writeOrderKey
	 * 
	 * @param treeKey
	 * @param key
	 * @param orderClause
	 */

	public static void writeOrderKey(HashMap treeKey, String key,
	        StringBuffer orderClause) {

		if (treeKey.containsKey(key)) {
			orderClause.append(" ,").append(treeKey.get(key));
		}
	}

	/**
	 * Method convertToAL
	 * 
	 * @param rs
	 * @param al
	 * @throws Exception
	 */

	public static void convertToAL(ResultSet rs, ArrayList<String> al) throws Exception {

		while (rs.next()) {
			al.add(rs.getString(1));
		}
		al.trimToSize();
	}

	/**
	 * Method dump
	 * 
	 * @param rs
	 * @throws Exception
	 */

	public static void dump(ResultSet rs) throws Exception {

		
			if (!_log.isTraceEnabled())
				return;

			_log.trace("Getting result set...");
			// rs = stmt.getResultSet();
			ResultSetMetaData rsm = rs.getMetaData();
			// Display names of columns fetched
			int colcount = rsm.getColumnCount();

			_log.trace(colcount + " column(s) in result");
			int[] coltype = new int[colcount + 1]; // Do not slot 0

			StringBuffer sb = new StringBuffer();
			for (int i = 1; i < colcount + 1; i++) {
				sb.append(rsm.getColumnName(i) + "   ");
				coltype[i] = rsm.getColumnType(i);
			}

			_log.trace(sb.toString());

			while (rs.next()) {
				sb.setLength(0);
				for (int j = 1; j < colcount + 1; j++) {
					if (j != 1) {
						sb.append(",");
					}
					switch (coltype[j]) {

						case Types.TINYINT:
							sb.append("" + rs.getShort(j));
							break;

						case Types.SMALLINT:
							sb.append("" + rs.getShort(j));
							break;

						case Types.INTEGER:
							sb.append("" + rs.getInt(j));
							break;

						case Types.BIGINT:
							sb.append("" + rs.getLong(j));
							break;

						case Types.FLOAT:
							sb.append("" + rs.getFloat(j));
							break;

						case Types.REAL:
							sb.append("" + rs.getDouble(j));
							break;

						case Types.DOUBLE:
							sb.append("" + rs.getDouble(j));
							break;

						case Types.NUMERIC:
							sb.append("" + rs.getInt(j));
							break;

						case Types.DECIMAL:
							sb.append("" + rs.getInt(j));
							break;

						case Types.CHAR:
							sb.append("" + rs.getString(j));
							break;

						case Types.VARCHAR:
							sb.append("" + rs.getString(j));
							break;

						case Types.LONGVARCHAR:
							sb.append("" + rs.getString(j));
							break;

						case Types.DATE:
							sb.append("" + rs.getDate(j));
							break;

						case Types.TIME:
							sb.append("" + rs.getTime(j));
							break;

						case Types.TIMESTAMP:
							sb.append("" + rs.getTimestamp(j));
							break;

						case Types.BINARY:
						case Types.BIT:
						case Types.VARBINARY:
						case Types.LONGVARBINARY:
							byte b[] = rs.getBytes(j);

							for (byte element : b) {
								sb.append("" + element + "|");
							}
							break;

						case Types.NULL:
							sb.append("-");
							break;

						case Types.OTHER:
							sb.append("OTHER");
							break;

						default:
							sb.append("UNKNOWN-TYPE");
					}
				}
				_log.trace(sb.toString());

			}
	}

	/**
	 * Method unpadColumns
	 * 
	 * @param old_concat_key
	 * @param sbf
	 * @param plan
	 */

	public static void unpadColumns(String old_concat_key, StringBuffer sbf,
	        boolean plan) {

		if (plan) {
			sbf.append(old_concat_key.substring(0, 3)).append("|");
			sbf.append(old_concat_key.substring(3, 4)).append("|");
			sbf.append(old_concat_key.substring(4, 5)).append("|");
			sbf.append(old_concat_key.substring(5, 11)).append("|");
			sbf.append(old_concat_key.substring(11, 14)).append("|");
			sbf.append(old_concat_key.substring(14, 17)).append("|");
			sbf.append(old_concat_key.substring(17, 27)).append("|");
			sbf.append(old_concat_key.substring(27, 28)).append("|");
			sbf.append(old_concat_key.substring(28, 31)).append("|");
			sbf.append(old_concat_key.substring(31, 32)).append("|");
			sbf.append(old_concat_key.substring(32, 48));
		} else {
			sbf.append(old_concat_key.substring(0, 3)).append("|");
			sbf.append(old_concat_key.substring(3, 4)).append("|");
			sbf.append(old_concat_key.substring(4, 7)).append("|");
			sbf.append(old_concat_key.substring(7, 23)).append("|");
			sbf.append(old_concat_key.substring(23, 26)).append("|");
			sbf.append(old_concat_key.substring(26, 42)).append("|");
			sbf.append(old_concat_key.substring(42, 43)).append("|");
		}

	}

}
