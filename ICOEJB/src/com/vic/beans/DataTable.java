package com.vic.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="DATATABLE")
public class DataTable implements Serializable{
	private String tableId;
	private String tableName;
	private String tableType;
	private String tHead;
	private String sqlStr;
	private Set<Page> pageSet = new HashSet<Page>();
	private Set<Object[]> dataSet;
	
	@Id
	@GeneratedValue
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableType() {
		return tableType;
	}
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	public String gettHead() {
		return tHead;
	}
	public void settHead(String tHead) {
		this.tHead = tHead;
	}
	public String getSqlStr() {
		return sqlStr;
	}
	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}
	@ManyToMany(mappedBy="tableSet")
	public Set<Page> getPageSet() {
		return pageSet;
	}
	public void setPageSet(Set<Page> pageSet) {
		this.pageSet = pageSet;
	}
	
	@Transient
	public Set<Object[]> getDataSet() {
		return dataSet;
	}
	public void setDataSet(Set<Object[]> dataSet) {
		this.dataSet = dataSet;
	}
}
