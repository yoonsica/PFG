package com.vic.fusioncharts;

import java.util.Map;

public class Pie2D {
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
	private Map<String, String> set;
	public Pie2D(String caption, Map<String, String> set) {
		super();
		this.caption = caption;
		this.set = set;
	}
	
	/**
	 * 根据设定的属性值生成chart的DataXml字符串
	 * @return
	 */
	public String toXMLString() {
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
		for (String key : set.keySet()) {
			sb.append("<set label='").append(key).append("' value='").append(set.get(key)).append("'/>");
		}
		sb.append("</chart>");
		return sb.toString();
	}
}
