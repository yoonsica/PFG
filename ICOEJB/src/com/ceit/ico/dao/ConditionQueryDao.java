package com.ceit.ico.dao;

import java.util.List;
import javax.ejb.Remote;

import com.vic.beans.Condition;

@Remote
public interface ConditionQueryDao {
	public List<Condition> getConditions(String pageId);
}