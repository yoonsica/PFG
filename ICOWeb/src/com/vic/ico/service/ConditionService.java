package com.vic.ico.service;

import java.util.List;

import com.vic.beans.Condition;

public interface ConditionService {
	//直接生成html坏处是不能统一修改，下面增加两个接口，传对象到js进行解析，这样方便统一修改
	public List<String> getConditionsHtmlByPageId(String pageId);
	public List<String> getAllConditionsHtml();
	
	public List<Condition> getConditionsByPageId(String pageId);
	public List<Condition> getAllConditions();
	/**
	 * 添加新的条件（Ｃｏｎｄｉｔｉｏｎ）接口
	 * @param name　新条件的英文名，在页面里用于控件的id和name
	 * @param label 新条件的中文名
	 * @param type 条件类型
	 * @param items 供选择的条目列表
	 * @return 是否添加成功
	 */
	public boolean addNewCondition(String name,String label,String type,String[] items);
}
