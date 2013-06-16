package com.ceit.defect.action;

import com.ceit.defect.service.IndexDefectService;
import com.opensymphony.xwork2.ActionSupport;

public class IndexDefectAction extends ActionSupport {
	private IndexDefectService indexDefectService;

	public IndexDefectService getIndexDefectService() {
		return indexDefectService;
	}

	public void setIndexDefectService(IndexDefectService indexDefectService) {
		this.indexDefectService = indexDefectService;
	}
	
	

}
