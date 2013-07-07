package com.ceit.ico.dao;

import java.util.List;

import javax.ejb.Remote;

import com.vic.beans.Chart;

@Remote
public interface ChartQueryDao {
	public List<Chart> getCharts(String pageId);
}
