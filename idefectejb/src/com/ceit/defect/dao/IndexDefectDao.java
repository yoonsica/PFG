package com.ceit.defect.dao;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface IndexDefectDao {
	public List<Object[]> findIndexResult(String sql);
}
