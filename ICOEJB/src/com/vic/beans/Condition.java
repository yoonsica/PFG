package com.vic.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="CONDITION")
public class Condition implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5684724157296460963L;
	private String conditionId;
	private String name;//英文名，页面里用作控件id，要求唯一
	private String lable;
	//private String sqlStr;
	private String type;
	private List<Page> pageList = new ArrayList<Page>();
	private List<CodeName> codeNameList = new ArrayList<CodeName>();
	
	public Condition(){}
	public Condition(String name, String lable, String type,
			List<CodeName> codeNameList) {
		this.name = name;
		this.lable = lable;
		this.type = type;
		this.codeNameList = codeNameList;
	}

	@Id
	@GeneratedValue
	public String getConditionId() {
		return conditionId;
	}
	public void setConditionId(String conditionId) {
		this.conditionId = conditionId;
	}
	
	@Column(unique=true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
/*	public String getSqlStr() {
		return sqlStr;
	}
	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}*/
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	@ManyToMany(cascade = CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinTable(name="Condition_CodeName",joinColumns={@JoinColumn(name="conditionId",referencedColumnName="conditionId")},
	inverseJoinColumns={ @JoinColumn(name = "id", referencedColumnName = "id") })	
	public List<CodeName> getCodeNameList() {
		return codeNameList;
	}
	public void setCodeNameList(List<CodeName> codeNameList) {
		this.codeNameList = codeNameList;
	}
	@ManyToMany(mappedBy="conditionList")
	public List<Page> getPageList() {
		return pageList;
	}
	public void setPageList(List<Page> pageList) {
		this.pageList = pageList;
	}
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
}
