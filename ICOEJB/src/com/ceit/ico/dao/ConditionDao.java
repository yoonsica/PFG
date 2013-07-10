package com.ceit.ico.dao;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import com.vic.beans.Condition;

@Remote
public interface ConditionDao {
	public Set<Condition> getConditionsByPageId(String pageId);
	public List<Condition> getAllConditions();
	public Condition getConditionByName(String name);
	public Condition getConditionById(String conditionId);
	public boolean insertNewCondition(Condition	condition);
	public void update(Condition condition);
}