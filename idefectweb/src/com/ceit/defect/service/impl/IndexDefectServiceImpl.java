package com.ceit.defect.service.impl;

import com.ceit.defect.dao.IndexDefectDao;
import com.ceit.defect.service.IndexDefectService;

public class IndexDefectServiceImpl implements IndexDefectService {
	private IndexDefectDao indexDefectDao;

	public IndexDefectDao getIndexDefectDao() {
		return indexDefectDao;
	}

	public void setIndexDefectDao(IndexDefectDao indexDefectDao) {
		this.indexDefectDao = indexDefectDao;
	}
	
	

}
