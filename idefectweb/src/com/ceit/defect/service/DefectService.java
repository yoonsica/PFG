package com.ceit.defect.service;

import java.util.List;

import com.ceit.ico.dto.CodeNameDTO;

public interface DefectService {
	public List<Object[]> getVoltageClassTableData(String devSelected,
			String startTime, String endTime);
	/**
	 * 查询电压等级、地市局、设备类型等条件
	 * @param id 查询类别（常量Constant.*）
	 * @param addAll 是否添加全部
	 * @return
	 */
	public List<CodeNameDTO> getCodeNameDTOList(String id, boolean addAll);
	/**
	 * 根据配置生成sql，保存到数据库中
	 * @param dydj
	 * @param sblx
	 * @param ssds
	 * @param tyrq_start
	 * @param tyrq_end
	 * @return
	 */
	public String generateQuery(String[] dydj, String[] sblx, String[] ssds,
			String tyrq_start, String tyrq_end);
	/**
	 * 根据生成的sql查询表格数据
	 * @param sql
	 * @return
	 */
	public List<Object[]> getUnitDeviceData(String sql);
	public List<String> getTableHeadList(String[] dydj);
}
