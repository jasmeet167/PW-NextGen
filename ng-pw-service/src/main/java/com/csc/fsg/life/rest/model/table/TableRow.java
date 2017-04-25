package com.csc.fsg.life.rest.model.table;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.util.StringUtils;

public class TableRow
	implements Serializable
{
	private static final long serialVersionUID = 2313537125755705529L;

	private List<String> cellValues = Collections.emptyList();

	public TableRow(String delimitedData, int columnCount)
	{
		if (StringUtils.hasText(delimitedData)) {
			cellValues = new LinkedList<>();
			String[] dataValues = delimitedData.split("\t");
			for (int i = 0, n = columnCount; i < n; i++)
				cellValues.add(dataValues[i].trim());
		}
	}

	public List<String> getCellValues()
	{
		return Collections.unmodifiableList(cellValues);
	}
}
