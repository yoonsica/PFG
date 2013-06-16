package com.ceit.device.action;

import java.util.List;

import com.ceit.defect.constant.Constant;
import com.ceit.defect.service.DefectService;
import com.ceit.ico.dto.CodeNameDTO;
import com.opensymphony.xwork2.ActionSupport;

public class UnitDeviceAction  extends ActionSupport{
	private static final long serialVersionUID = 9100317667833761171L;
	private List<CodeNameDTO> voltageClassList;
	private List<CodeNameDTO> deviceTypeList;
	private List<CodeNameDTO> departmentList;
	private List<String> tableHeadList;
	private List<Object[]> dataList;//返回查询结果
	private String tyrq_start;//投运时间区间（开始）
	private String tyrq_end;//投运时间区间结束2004-1-1
	private String[] dydj;//用户配置页面选择的要显示的电压等级
	private String[] sblx;//用户配置页面选择的要显示的设备类型
	private String[] ssds;//用户配置页面选择的要显示的所属地市
	public List<String> getTableHeadList() {
		return tableHeadList;
	}
	public void setTableHeadList(List<String> tableHeadList) {
		this.tableHeadList = tableHeadList;
	}
	public List<Object[]> getDataList() {
		return dataList;
	}
	public void setDataList(List<Object[]> dataList) {
		this.dataList = dataList;
	}
	public String[] getSblx() {
		return sblx;
	}
	public void setSblx(String[] sblx) {
		this.sblx = sblx;
	}
	public String[] getSsds() {
		return ssds;
	}
	public void setSsds(String[] ssds) {
		this.ssds = ssds;
	}
	public String[] getDydj() {
		return dydj;
	}
	public void setDydj(String[] dydj) {
		this.dydj = dydj;
	}
	public String getTyrq_start() {
		return tyrq_start;
	}
	public void setTyrq_start(String tyrq_start) {
		this.tyrq_start = tyrq_start;
	}
	public String getTyrq_end() {
		return tyrq_end;
	}
	public void setTyrq_end(String tyrq_end) {
		this.tyrq_end = tyrq_end;
	}
	public List<CodeNameDTO> getDepartmentList() {
		return departmentList;
	}
	public void setDepartmentList(List<CodeNameDTO> departmentList) {
		this.departmentList = departmentList;
	}
	private DefectService defectService;
	public void setDefectService(DefectService defectService) {
		this.defectService = defectService;
	}
	public List<CodeNameDTO> getDeviceTypeList() {
		return deviceTypeList;
	}
	public void setDeviceTypeList(List<CodeNameDTO> deviceTypeList) {
		this.deviceTypeList = deviceTypeList;
	}
	public List<CodeNameDTO> getVoltageClassList() {
		return voltageClassList;
	}
	public void setVoltageClassList(List<CodeNameDTO> voltageClassList) {
		this.voltageClassList = voltageClassList;
	}
	@Override
	public String execute() throws Exception {
		setDeviceTypeList(defectService.getCodeNameDTOList(Constant.DEVTYPE_QUERY,false));
		setVoltageClassList(defectService.getCodeNameDTOList(Constant.VOLTAGECLASS_QUERY, false));
		setDepartmentList(defectService.getCodeNameDTOList(Constant.DEPT_QUERY, false));
		setTyrq_end("2013-1-1");
		setTyrq_start("2004-1-1");
		return SUCCESS;
	}
	
	public String config() throws Exception{
		System.out.println(dydj.length);
		//首先生成sql语句，存入数据库中？
		String sql = defectService.generateQuery(dydj,sblx,ssds,tyrq_start,tyrq_end);
		System.out.println(sql);
		setDataList(defectService.getUnitDeviceData(sql));
		setTableHeadList(defectService.getTableHeadList(dydj));
		return "json";
	}
}
