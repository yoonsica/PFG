package com.ceit.ico.dao;

import java.util.List;
import javax.ejb.Remote;

import com.vic.beans.Condition;

@Remote
public interface ConditionDao {
	public List<Condition> getConditionsByPageId(String pageId);
	public List<Condition> getAllConditions();
	public Condition getConditionByName(String name);
	public boolean insertNewCondition(Condition	condition);
}