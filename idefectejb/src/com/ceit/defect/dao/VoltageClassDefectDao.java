package com.ceit.defect.dao;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface VoltageClassDefectDao {
	public List<Object[]> getDefectByVoltageClass(String sql,String devSelectCode,String startTime,String endTime);
}
