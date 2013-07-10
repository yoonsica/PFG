package com.vic.fusioncharts;

import java.util.Set;

public class DataSet {
	private String seriesName;
	private String showValues;
	private Set<String> values;
	
	public DataSet(String seriesName, String showValues, Set<String> values) {
		super();
		this.seriesName = seriesName;
		this.showValues = showValues;
		this.values = values;
	}

	public String dataSetToXMLString(){
		StringBuilder sb = new StringBuilder("<dataset seriesName='");
		sb.append(seriesName).append("' showValues='").append(showValues).append("'");
		for (String value : values) {
			sb.append("<set value='").append(value).append("'/>");
		}
		sb.append("</dataset>");
		return sb.toString();
	}
}
