package com.vic.fusioncharts;

import java.util.Set;

public class LineSet {
	private String seriesName="百分比";
	private String color = "ff0000";
	private String showValues = "0";
	private String lineThickness = "4";
	private Set<String> valueSet;
	
	public LineSet(String seriesName, Set<String> valueSet) {
		super();
		this.seriesName = seriesName;
		this.valueSet = valueSet;
	}

	public String toXmlString(){
		StringBuilder sb = new StringBuilder("<lineSet seriesname='");
		sb.append(seriesName).append("' color='")
		.append(color).append("' showValues='").append(showValues)
		.append("' lineThickness=").append(lineThickness);
		for (String value : valueSet) {
			sb.append("<set value='").append(value).append("'/>");
		}
		sb.append("</lineSet>");
		return sb.toString();
	}
}
