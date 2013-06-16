package com.ceit.defect.dao;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface UnitDeviceDao {
	public List<Object[]> getUnitDeviceData(String sql);

	public List<String> getVoltagesByIds(String[] dydj);
}
