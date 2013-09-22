package com.vic.fusioncharts;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "DATASET")
public class DataSet implements Serializable {
	private String id;
	private String seriesName;
	private String showValues = "0";
	private List<Object> values;
	private String dataSetSql;
	private String color = "22AAAA";

	@Id
	@GeneratedValue
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public String getShowValues() {
		return showValues;
	}

	public void setShowValues(String showValues) {
		this.showValues = showValues;
	}

	@Transient
	public List<Object> getValues() {
		return values;
	}

	public void setValues(List<Object> values) {
		this.values = values;
	}

	@Column(length = 2000)
	public String getDataSetSql() {
		return dataSetSql;
	}

	public void setDataSetSql(String dataSetSql) {
		this.dataSetSql = dataSetSql;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public DataSet() {
	}

	public DataSet(String seriesName, String dataSetSql, String color) {
		super();
		this.seriesName = seriesName;
		this.dataSetSql = dataSetSql;
		this.color = color;
	}

	public DataSet(String seriesName, String dataSetSql) {
		super();
		this.seriesName = seriesName;
		this.dataSetSql = dataSetSql;
	}

	public String dataSetToXMLString() {
		StringBuilder sb = new StringBuilder("<dataset seriesName='");
		sb.append(seriesName).append("' showValues='").append(0)
				.append("' color='").append(color).append("'>");
		try {
			for (Object value : values) {
				sb.append("<set value='").append(value.toString())
						.append("' />");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		sb.append("</dataset>");
		return sb.toString();
	}
}
