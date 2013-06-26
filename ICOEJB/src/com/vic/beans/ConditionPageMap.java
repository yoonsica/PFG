package com.vic.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * codition和page两个表的主键是多对多关系，
 * 所以建立一个中间表
 * @author VIC
 *
 */
@Entity
@Table(name="ConditionPageMap")
public class ConditionPageMap {
	@Id
	@GeneratedValue
	private String id;
	private String conditonId;
	private String pageId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getConditonId() {
		return conditonId;
	}
	public void setConditonId(String conditonId) {
		this.conditonId = conditonId;
	}
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	
}
