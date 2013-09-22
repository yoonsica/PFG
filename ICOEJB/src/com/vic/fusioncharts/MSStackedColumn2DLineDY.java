package com.vic.fusioncharts;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.ws.rs.DefaultValue;
import com.vic.beans.Chart;
@Entity
@Table(name="MSStackedColumn2DLineDY")
@DiscriminatorValue("MSStackedColumn2DLineDY")  
public class MSStackedColumn2DLineDY extends Chart{
	private String caption;
	private String xAxisName;
	private String PYaxisname;
	private String SYAxisName;
	private String sNumberSuffix;
	private String bgColor;
	private String baseFont;
	private String baseFontSize;
	private String baseFontColor;
	private String setAdaptiveSYMin;
	private String showPlotBorder;
	private String palette;
	private String useRoundEdges;
	private Categories categories;
	private Set<DataSet> dataSets = new HashSet<DataSet>();
	private Set<LineSet> lineSets = new HashSet<LineSet>();
	
	@Column(nullable=false)
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	public String getxAxisName() {
		return xAxisName;
	}
	public void setxAxisName(String xAxisName) {
		this.xAxisName = xAxisName;
	}

	public String getPYaxisname() {
		return PYaxisname;
	}
	public void setPYaxisname(String pYaxisname) {
		PYaxisname = pYaxisname;
	}

	public String getSYAxisName() {
		return SYAxisName;
	}
	public void setSYAxisName(String sYAxisName) {
		SYAxisName = sYAxisName;
	}

	@DefaultValue(value="%25")
	public String getsNumberSuffix() {
		return sNumberSuffix;
	}
	public void setsNumberSuffix(String sNumberSuffix) {
		this.sNumberSuffix = sNumberSuffix;
	}

	@DefaultValue(value="99CCFF,FFFFFF")
	public String getBgColor() {
		return bgColor;
	}
	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	@DefaultValue(value="Arial")
	public String getBaseFont() {
		return baseFont;
	}
	public void setBaseFont(String baseFont) {
		this.baseFont = baseFont;
	}

	@DefaultValue(value="12")
	public String getBaseFontSize() {
		return baseFontSize;
	}
	public void setBaseFontSize(String baseFontSize) {
		this.baseFontSize = baseFontSize;
	}

	@DefaultValue(value="000000")
	public String getBaseFontColor() {
		return baseFontColor;
	}
	public void setBaseFontColor(String baseFontColor) {
		this.baseFontColor = baseFontColor;
	}
	
	@DefaultValue(value="1")
	public String getSetAdaptiveSYMin() {
		return setAdaptiveSYMin;
	}
	public void setSetAdaptiveSYMin(String setAdaptiveSYMin) {
		this.setAdaptiveSYMin = setAdaptiveSYMin;
	}

	@DefaultValue(value="1")
	public String getShowPlotBorder() {
		return showPlotBorder;
	}
	public void setShowPlotBorder(String showPlotBorder) {
		this.showPlotBorder = showPlotBorder;
	}

	@DefaultValue(value="3")
	public String getPalette() {
		return palette;
	}
	public void setPalette(String palette) {
		this.palette = palette;
	}

	@DefaultValue(value="1")
	public String getUseRoundEdges() {
		return useRoundEdges;
	}
	public void setUseRoundEdges(String useRoundEdges) {
		this.useRoundEdges = useRoundEdges;
	}

	@OneToOne(optional=true,cascade=CascadeType.ALL)
	@JoinColumn(name="id",unique=true)
	public Categories getCategories() {
		return categories;
	}
	public void setCategories(Categories categories) {
		this.categories = categories;
	}

	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@OrderBy(value = "id ASC")
	@JoinColumn(name = "chartId")
	public Set<DataSet> getDataSets() {
		return dataSets;
	}
	public void setDataSets(Set<DataSet> dataSets) {
		this.dataSets = dataSets;
	}

	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@OrderBy(value = "id ASC")
	@JoinColumn(name = "chartId")
	public Set<LineSet> getLineSets() {
		return lineSets;
	}
	public void setLineSets(Set<LineSet> lineSets) {
		this.lineSets = lineSets;
	}
	public MSStackedColumn2DLineDY(){}
	public MSStackedColumn2DLineDY(String caption, String xAxisName,
			String pYaxisname, String sYAxisName, Categories categories,
			Set<DataSet> dataSets, Set<LineSet> lineSets) {
		super();
		this.caption = caption;
		this.xAxisName = xAxisName;
		PYaxisname = pYaxisname;
		SYAxisName = sYAxisName;
		this.categories = categories;
		this.dataSets = dataSets;
		this.lineSets = lineSets;
	}
	
	@Override
	public String toXMLStr() {
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
		if (categories!=null) {
			sb.append(categories.toXmlStr());
		}
		
		if (null!=dataSets) {
			sb.append("<dataset>");
			for (DataSet dataSet : dataSets) {
				sb.append(dataSet.dataSetToXMLString());
			}
			sb.append("</dataset>");
		}
		
		if (null!=lineSets) {
			for (LineSet line : lineSets) {
				sb.append(line.toXmlString());
			}
		}
		sb.append("</chart>");
		return sb.toString();
	}
}
