package com.vic.fusioncharts;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table
public class Categories implements Serializable {
	private String font;
	private String fontSize;
	private String fontColor;
	private String categoriesSql;
	private String id;
	private List<String> category;
	public Categories(){}
	
	public Categories(String categoriesSql) {
		super();
		this.categoriesSql = categoriesSql;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}

	public String getFontSize() {
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}

	public String getFontColor() {
		return fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	@Column(length=1024)
	public String getCategoriesSql() {
		return categoriesSql;
	}

	public void setCategoriesSql(String categoriesSql) {
		this.categoriesSql = categoriesSql;
	}
	@Id
	@GeneratedValue
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Transient
	public List<String> getCategory() {
		return category;
	}
	public void setCategory(List<String> category) {
		this.category = category;
	}
	
	public String toXmlStr(){
		StringBuilder sb = new StringBuilder("<categories font='");
		if (null!=font) {
			sb.append(font).append("' fontSize='");
		}else {
			sb.append("Arial' fontSize='");
		}
		if (null!=fontSize) {
			sb.append(fontSize).append("' fontColor='");
		}else {
			sb.append("12' fontColor='");
		}
		if (null!=fontColor) {
			sb.append(fontColor).append("' >");
		}else {
			sb.append("000000' >");
		}
		for (String  label: category) {
			sb.append("<category label='").append(label).append("'/>");
		}
		sb.append("</categories>");
		return sb.toString();
	}

}
