package com.vic.ico.service;

import java.util.List;
import java.util.Map;
import com.vic.beans.DataTable;

public interface TableService {
	public void addTable(Map parameterMap);
	public void updateTable(DataTable tableId);
	public DataTable getTableById(String tableId);
	public List<DataTable> getAllTables();
}
