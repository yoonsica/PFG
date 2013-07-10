package com.vic.fusioncharts;

import java.util.Set;

public class Categories {
	private String font;
	private String fontSize;
	private String fontColor;
	private Set<String> category;
	public Categories(Set<String> category) {
		super();
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
