package com.vic.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

@Entity
@Table(name="CONDITION")
public class Condition implements Serializable{
	private static final long serialVersionUID = -5684724157296460963L;
	private String conditionId;
	private String name;//英文名，页面里用作控件id，要求唯一
	private String label;
	//private String sqlStr;
	private String type;
	private Set<Page> pageSet = new HashSet<Page>();
	private Set<CodeName> codeNameSet = new HashSet<CodeName>();
	
	public Condition(){}
	public Condition(String name, String label, String type,
			Set<CodeName> codeNameSet) {
		this.name = name;
		this.label = label;
		this.type = type;
		this.codeNameSet = codeNameSet;
	}

	public Condition(String conditionId, String name, String label,
			String type, Set<Page> pageSet, Set<CodeName> codeNameSet) {
		super();
		this.conditionId = conditionId;
		this.name = name;
		this.label = label;
		this.type = type;
		this.pageSet = pageSet;
		this.codeNameSet = codeNameSet;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	@ManyToMany(cascade = CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinTable(name="Condition_CodeName",joinColumns={@JoinColumn(name="conditionId",referencedColumnName="conditionId")},
	inverseJoinColumns={ @JoinColumn(name = "id", referencedColumnName = "id") })	
	public Set<CodeName> getCodeNameSet() {
		return codeNameSet;
	}
	public void setCodeNameSet(Set<CodeName> codeNameSet) {
		this.codeNameSet = codeNameSet;
	}
	@ManyToMany(mappedBy="conditionSet",fetch=FetchType.EAGER)
	public Set<Page> getPageSet() {
		return pageSet;
	}
	public void setPageSet(Set<Page> pageSet) {
		this.pageSet = pageSet;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}
