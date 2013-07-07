package com.vic.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="CODENAME")
public class CodeName implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5036762481166963030L;
	private String id;
	private String code;
	private String name;
	private int display;
	private List<Condition> conditionList = new ArrayList<Condition>();
	
	public CodeName() {
	}
	@Id
	@GeneratedValue
	public String getId() {
		return id;
	}
	public int getDisplay() {
		return display;
	}
	public void setDisplay(int display) {
		this.display = display;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToMany(mappedBy="codeNameList",fetch=FetchType.EAGER)
	public List<Condition> getConditionList() {
		return conditionList;
	}
	public void setConditionList(List<Condition> conditionList) {
		this.conditionList = conditionList;
	}
	
	/*@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof CodeName)) {
			return false;
		}
		CodeName other = (CodeName)obj;
		if (this.code==other.code&&this.name==other.name) {
			return true;
		}
		return false;
	}*/
}
