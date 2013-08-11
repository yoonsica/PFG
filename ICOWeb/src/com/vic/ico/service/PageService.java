package com.vic.ico.service;

import java.util.List;
import java.util.Map;
import com.vic.beans.DataTable;
import com.vic.beans.Page;

public interface PageService {
	public void addPage(Map parameterMap);
	public List<Page> getAllPages();
	public Page getPageById(String pageId,Map<String, String[]> map);
}
