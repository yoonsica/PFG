package com.vic.fusioncharts;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.ws.rs.DefaultValue;

import com.vic.beans.Chart;

@Entity
@Table(name="Pie2D")
@DiscriminatorValue("Pie2D")
public class Pie2D extends Chart{
	private String caption;
	private String baseFontSize;
	private String formatNumber;
	private String formatNumberScale;
	private String forceDecimals;
	private String enableSmartLabels;
	private String enableRotation;
	private String bgColor;
	private String bgAlpha;
	private String bgRatio;
	private String bgAngle;
	private String showBorder;
	private String startingAngle;
	private String setSql;
	private List<LabelValue> lvSet;//这个字段不映射。

	public Pie2D() {
		super();
	}
	
	public Pie2D(String caption, String setSql) {
		super();
		this.caption = caption;
		this.setSql = setSql;
	}


	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	@DefaultValue(value="12")
	public String getBaseFontSize() {
		return baseFontSize;
	}

	public void setBaseFontSize(String baseFontSize) {
		this.baseFontSize = baseFontSize;
	}
	
	@DefaultValue(value="0")
	public String getFormatNumber() {
		return formatNumber;
	}

	public void setFormatNumber(String formatNumber) {
		this.formatNumber = formatNumber;
	}

	@DefaultValue(value="0")
	public String getFormatNumberScale() {
		return formatNumberScale;
	}

	public void setFormatNumberScale(String formatNumberScale) {
		this.formatNumberScale = formatNumberScale;
	}
	
	@DefaultValue(value="2")
	public String getForceDecimals() {
		return forceDecimals;
	}
	public void setForceDecimals(String forceDecimals) {
		this.forceDecimals = forceDecimals;
	}
	
	@DefaultValue(value="1")
	public String getEnableSmartLabels() {
		return enableSmartLabels;
	}
	public void setEnableSmartLabels(String enableSmartLabels) {
		this.enableSmartLabels = enableSmartLabels;
	}
	
	@DefaultValue(value="1")
	public String getEnableRotation() {
		return enableRotation;
	}
	public void setEnableRotation(String enableRotation) {
		this.enableRotation = enableRotation;
	}
	
	@DefaultValue(value="99CCFF,FFFFFF")
	public String getBgColor() {
		return bgColor;
	}
	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	@DefaultValue(value="40,100")
	public String getBgAlpha() {
		return bgAlpha;
	}
	public void setBgAlpha(String bgAlpha) {
		this.bgAlpha = bgAlpha;
	}
	
	@DefaultValue(value="0,100")
	public String getBgRatio() {
		return bgRatio;
	}
	public void setBgRatio(String bgRatio) {
		this.bgRatio = bgRatio;
	}

	@DefaultValue(value="360")
	public String getBgAngle() {
		return bgAngle;
	}
	public void setBgAngle(String bgAngle) {
		this.bgAngle = bgAngle;
	}

	@DefaultValue(value="1")
	public String getShowBorder() {
		return showBorder;
	}
	public void setShowBorder(String showBorder) {
		this.showBorder = showBorder;
	}

	@DefaultValue(value="70")
	public String getStartingAngle() {
		return startingAngle;
	}
	public void setStartingAngle(String startingAngle) {
		this.startingAngle = startingAngle;
	}

	@Transient
	public List<LabelValue> getLvSet() {
		return lvSet;
	}
	public void setLvSet(List<LabelValue> lvSet) {
		this.lvSet = lvSet;
	}

	@Column(length=1024)
	public String getSetSql() {
		return setSql;
	}

	public void setSetSql(String setSql) {
		this.setSql = setSql;
	}

	
	@Override
	public String toXMLStr() {
		StringBuilder sb = new StringBuilder("<chart caption='");
		sb.append(caption).append("' baseFontSize='");
		if (null!=baseFontSize) {
			sb.append(baseFontSize).append("' formatNumber='");
		}else {
			sb.append("12' formatNumber='");
		}
		if (null!=formatNumber) {
			sb.append(formatNumber).append("' formatNumberScale='");
		}else {
			sb.append("0' formatNumberScale='");
		}
		if (null!=formatNumberScale) {
			sb.append(formatNumberScale).append("' forceDecimals='");
		}else {
			sb.append("0' forceDecimals='");
		}
		if (null!=forceDecimals) {
			sb.append(forceDecimals).append("' enableSmartLabels='");
		}else {
			sb.append("2' enableSmartLabels='");
		}
		if (null!=enableSmartLabels) {
			sb.append(enableSmartLabels).append("' enableRotation='");
		}else {
			sb.append("1' enableRotation='");
		}
		if (null!=enableRotation) {
			sb.append(enableRotation).append("' bgColor='");
		}else {
			sb.append("0' bgColor='");
		}
		if (null!=bgColor) {
			sb.append(bgColor).append("' bgAlpha='");
		}else {
			sb.append("99CCFF,FFFFFF' bgAlpha='");
		}
		if (null!=bgAlpha) {
			sb.append(bgAlpha).append("' bgRatio='");
		}else {
			sb.append("40,100' bgRatio='");
		}
		if (null!=bgRatio) {
			sb.append(bgRatio).append("' bgAngle='");
		}else {
			sb.append("0,100' bgAngle='");
		}
		if (null!=bgAngle) {
			sb.append(bgAngle).append("' showBorder='");
		}else {
			sb.append("360' showBorder='");
		}
		if (null!=showBorder) {
			sb.append(showBorder).append("' startingAngle='");
		}else {
			sb.append("1' startingAngle='");
		}
		if (null!=startingAngle) {
			sb.append(startingAngle).append("'>");
		}else {
			sb.append("70'>");
		}
		if (setSql!=null) {
			for (LabelValue lv : lvSet) {
				sb.append("<set label='").append(lv.getLabel()).append("' value='").append(lv.getValue()).append("'/>");
			}
		}
		sb.append("</chart>");
		return sb.toString();
	}
}
