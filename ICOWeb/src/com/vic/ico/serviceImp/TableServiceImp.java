package com.vic.ico.serviceImp;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ceit.ico.dao.TableDao;
import com.vic.beans.DataTable;
import com.vic.beans.TFoot;
import com.vic.ico.service.TableService;

public class TableServiceImp implements TableService{
	private TableDao tableDao; 
	public TableDao getTableDao() {
		return tableDao;
	}

	public void setTableDao(TableDao tableDao) {
		this.tableDao = tableDao;
	}

	@Override
	public void addTable(Map parameterMap) {
		String tableName = ((String[])parameterMap.get("tableName"))[0];
		String tHeadHTML = ((String[])parameterMap.get("tHeadHTML"))[0];
		String tBodySql = ((String[])parameterMap.get("tBodySql"))[0];
		String[] tFoot_titles = (String[]) parameterMap.get("tFoot_title");
		String[] tFoot_sqls = (String[]) parameterMap.get("tFoot_sql");
		if (!tBodySql.equals("")) {
			List<Object[]> tbodyList = tableDao.nameQuery(tBodySql, parameterMap);
		}
		Set<TFoot> tFoots = new HashSet<TFoot>();
		if (tFoot_titles.length>0&&tFoot_sqls.length>0) {
			for (int i = 0; i < tFoot_sqls.length; i++) {
				if (tFoot_titles[i].equals("")&&tFoot_sqls[i].equals("")) {
				}else {
					TFoot tFoot = new TFoot(tFoot_titles[i], tFoot_sqls[i]);
					tFoots.add(tFoot);
				}
			}
		}
		DataTable table = new DataTable(tableName, tHeadHTML, tBodySql, tFoots);
		tableDao.insert(table);
	}

	@Override
	public void updateTable(DataTable tableId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DataTable getTableById(String tableId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DataTable> getAllTables() {
		List<DataTable> list = tableDao.findAllTables();
		for (DataTable dataTable : list) {
			if (dataTable.gettBodySql()!=null) {
				dataTable.setTbodyList(tableDao.nameQuery(dataTable.gettBodySql(), null));
			}
			Set<TFoot> set = dataTable.gettFoots();
			for (TFoot tFoot : set) {
				tFoot.setDataList(tableDao.nameQuery(tFoot.getSql(), null));
			}
			dataTable.setTableHTML(dataTable.toHTML());
		}
		return list;
	}

}
