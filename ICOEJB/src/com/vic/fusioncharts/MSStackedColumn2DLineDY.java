package com.vic.fusioncharts;

import java.util.Set;

public class MSStackedColumn2DLineDY {
	private String caption;
	private String xAxisName;
	private String PYaxisname;
	private String SYAxisName;
	private String sNumberSuffix="%25";
	private String bgColor="99CCFF,FFFFFF";
	private String baseFont="Arial";
	private String baseFontSize="12";
	private String baseFontColor="000000";
	private String setAdaptiveSYMin="1";
	private String showPlotBorder="1";
	private String palette="3";
	private String useRoundEdges="1";
	private Categories categories;
	private Set<DataSet> dataSets;
	private Set<LineSet> lineSets;
	public MSStackedColumn2DLineDY(String caption, String xAxisName,
			String pYaxisname, String sYAxisName, Categories categories,
			Set<DataSet> dataSets,Set<LineSet> lineSets) {
		super();
		this.caption = caption;
		this.xAxisName = xAxisName;
		PYaxisname = pYaxisname;
		SYAxisName = sYAxisName;
		this.categories = categories;
		this.dataSets = dataSets;
		this.lineSets = lineSets;
	}
	
	public String toXmlString(){
		StringBuilder sb = new StringBuilder("<chart caption='");
		sb.append(caption).append("' xAxisName='").append(xAxisName)
		.append("' PYaxisname='").append(PYaxisname).append("' SYAxisName='")
		.append(SYAxisName).append("' sNumberSuffix='");
		if(sNumberSuffix!=null){
			sb.append(sNumberSuffix).append("' bgColor='");
		}else {
			sb.append("%25'  bgColor='");
		}
		if(bgColor!=null){
			sb.append(bgColor).append("' baseFont='");
		}else {
			sb.append("99CCFF,FFFFFF'  baseFont='");
		}
		if(baseFont!=null){
			sb.append(baseFont).append("' baseFontSize='");
		}else {
			sb.append("Arial'  baseFontSize='");
		}
		if(baseFontSize!=null){
			sb.append(baseFontSize).append("' baseFontColor='");
		}else {
			sb.append("12'  baseFontColor='");
		}
		if(baseFontColor!=null){
			sb.append(baseFontColor).append("' setAdaptiveSYMin='");
		}else {
			sb.append("000000'  setAdaptiveSYMin='");
		}
		if(setAdaptiveSYMin!=null){
			sb.append(setAdaptiveSYMin).append("' showPlotBorder='");
		}else {
			sb.append("1'  showPlotBorder='");
		}
		if(showPlotBorder!=null){
			sb.append(showPlotBorder).append("' palette='");
		}else {
			sb.append("1'  palette='");
		}
		if(palette!=null){
			sb.append(palette).append("' useRoundEdges='");
		}else {
			sb.append("3'  useRoundEdges='");
		}
		if(useRoundEdges!=null){
			sb.append(useRoundEdges).append("'>");
		}else {
			sb.append("1'>");
		}
		sb.append(categories.toXmlStr()).append("<dataset>");
		for (DataSet dataSet : dataSets) {
			sb.append(dataSet.dataSetToXMLString());
		}
		sb.append("</dataset>");
		for (LineSet line : lineSets) {
			sb.append(line.toXmlString());
		}
		sb.append("</chart>");
		return sb.toString();
	}
}
