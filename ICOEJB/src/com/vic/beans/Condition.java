package com.vic.beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name="CONDITION")
public class Condition {
	@Id
	@GeneratedValue
	private int conditionId;
	private String name;
	private String sqlStr;
	private String type;
	@Transient
	private List<CodeName> dataList;
	
	public int getConditionId() {
		return conditionId;
	}
	public void setConditionId(int conditionId) {
		this.conditionId = conditionId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSqlStr() {
		return sqlStr;
	}
	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<CodeName> getDataList() {
		return dataList;
	}
	public void setDataList(List<CodeName> dataList) {
		this.dataList = dataList;
	}
}
