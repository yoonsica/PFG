package com.vic.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="DATATABLE")
public class DataTable implements Serializable{
	private String tableId;
	private String tableName;
	private String tableType;
	private String tHeadHtml;
	private String tBodySql;
	private List<Object[]> tbodyList;//根据tbodysql去查询得到
	private Set<TFoot> tFoots = new HashSet<TFoot>();
	private String tableHTML;
	private Set<Page> pageSet = new HashSet<Page>();
	
	public DataTable() {
		super();
	}
	public DataTable(String tableName, String tHeadHtml, String tBodySql,
			Set<TFoot> tFoots) {
		super();
		this.tableName = tableName;
		this.tHeadHtml = tHeadHtml;
		this.tBodySql = tBodySql;
		this.tFoots = tFoots;
	}
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
	public String gettHeadHtml() {
		return tHeadHtml;
	}
	public void settHeadHtml(String tHeadHtml) {
		this.tHeadHtml = tHeadHtml;
	}
	public String gettBodySql() {
		return tBodySql;
	}
	public void settBodySql(String tBodySql) {
		this.tBodySql = tBodySql;
	}
	@ManyToMany(mappedBy="tableSet",fetch=FetchType.EAGER)
	public Set<Page> getPageSet() {
		return pageSet;
	}
	public void setPageSet(Set<Page> pageSet) {
		this.pageSet = pageSet;
	}
	@Transient
	public String getTableHTML() {
		return tableHTML;
	}
	public void setTableHTML(String tableHTML) {
		this.tableHTML = tableHTML;
	}
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@OrderBy(value = "id ASC")
	@JoinColumn(name = "tableId")
	public Set<TFoot> gettFoots() {
		return tFoots;
	}
	public void settFoots(Set<TFoot> tFoots) {
		this.tFoots = tFoots;
	}
	@Transient
	public List<Object[]> getTbodyList() {
		return tbodyList;
	}
	public void setTbodyList(List<Object[]> tbodyList) {
		this.tbodyList = tbodyList;
	}
	public String toHTML(){
		StringBuilder sb = new StringBuilder("<table>");
		sb.append(tHeadHtml);
		if (tbodyList!=null) {
			sb.append("<tbody>");
			for (Object[] objects : tbodyList) {
				sb.append("<tr>");
				for (Object object : objects) {
					sb.append("<td>").append(object.toString()).append("</td>");
				}
				sb.append("</tr>");
			}
		}
		if (tFoots.size()>0) {
			sb.append("<tfoot>");
			for (TFoot tFoot : tFoots) {
				sb.append(tFoot.toHTML());
			}
			sb.append("<tfoot>");
		}
		sb.append("</table>");
		return sb.toString();
	}
}
