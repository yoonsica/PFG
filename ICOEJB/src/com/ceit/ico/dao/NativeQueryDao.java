package com.ceit.ico.dao;

import java.util.List;
import javax.ejb.Remote;

import com.ceit.ico.dto.CodeNameDTO;

@Remote
public interface NativeQueryDao {
	/**
	 * 查询sql语句
	 * @param functionId 功能id
	 * @param queryTypeId 查询语句类型（1-记录，2-总数）
	 * @return sql语句
	 */
	public String getQuerySql(String functionId,String queryTypeId);
	
	/**
	 * 获得页面查询条件的sql，例如所属单位列表，设备类型列表等
	 * @param id FZ_CONDITION表中的id字段
	 * @return sql语句
	 */
	public String getConditionSql(String id);
	
	/**
	 * 获得设备（所属单位，电压等级等）的集合
	 * @param id
	 * @param addAll 是否添加全部（code=-1）
	 * @return
	 */
	public List<CodeNameDTO> getCodeNameDTOList(String id, boolean addAll);

	
}