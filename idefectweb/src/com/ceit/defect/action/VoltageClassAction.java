package com.ceit.defect.action;

import java.io.File;
import java.text.ParseException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

import com.ceit.defect.constant.Constant;
import com.ceit.defect.service.DefectService;
import com.ceit.ico.dto.CodeNameDTO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class VoltageClassAction extends ActionSupport {
	/**
	 * 电压等级统计分析
	 */
	private static final long serialVersionUID = 3332035958706764536L;
	private List<CodeNameDTO> deviceTypeList;
	private String deviceTypeSelect;//下拉框所选的值
	private String defect_find_date_start;//发现时间区间（开始）
	private String defect_find_date_end;//发现时间区间结束
	private int voltageClassesLength;
	private List<Object[]> defectByVoltageClass;//缺陷台次 交流500KV，429，33……
	private List<CodeNameDTO> voltageClassList;
	private String span;//对应页面上的隐藏面板
	private DefectService defectService;
	public void setDefectService(DefectService defectService) {
		this.defectService = defectService;
	}
	public List<CodeNameDTO> getVoltageClassList() {
		return voltageClassList;
	}
	public void setVoltageClassList(List<CodeNameDTO> voltageClassList) {
		this.voltageClassList = voltageClassList;
	}
	public int getVoltageClassesLength() {
		return voltageClassesLength;
	}
	public void setVoltageClassesLength(int voltageClassesLength) {
		this.voltageClassesLength = voltageClassesLength;
	}
	public String getSpan() {
		return span;
	}
	public void setSpan(String span) {
		this.span = span;
	}
	public String getDeviceTypeSelect() {
		return deviceTypeSelect;
	}
	public void setDeviceTypeSelect(String deviceTypeSelect) {
		this.deviceTypeSelect = deviceTypeSelect;
	}
	public List<Object[]> getDefectByVoltageClass() {
		return defectByVoltageClass;
	}
	public void setDefectByVoltageClass(List<Object[]> defectByVoltageClass) {
		this.defectByVoltageClass = defectByVoltageClass;
	}
	public String getDefect_find_date_start() {
		return defect_find_date_start;
	}
	public void setDefect_find_date_start(String defect_find_date_start) {
		this.defect_find_date_start = defect_find_date_start;
	}
	public String getDefect_find_date_end() {
		return defect_find_date_end;
	}
	public void setDefect_find_date_end(String defect_find_date_end) {
		this.defect_find_date_end = defect_find_date_end;
	}
	public List<CodeNameDTO> getDeviceTypeList() {
		return deviceTypeList;
	}
	public void setDeviceTypeList(List<CodeNameDTO> deviceTypeList) {
		this.deviceTypeList = deviceTypeList;
	}

	@Override
	public String execute() throws Exception {
		System.out.println("execute");
		setDeviceTypeList(defectService.getCodeNameDTOList(Constant.DEVTYPE_QUERY,true));
		this.setDefect_find_date_start("2004-1-1");
		this.setDefect_find_date_end("2013-1-1");
		setVoltageClassList(defectService.getCodeNameDTOList(Constant.VOLTAGECLASS_QUERY, false));
		setVoltageClassesLength(voltageClassList.size());
		return SUCCESS;
	}
	
	public String deleteFlashImages() throws Exception{
		ActionContext context = ActionContext.getContext();  
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST); 
		HttpServletResponse response =(HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		String fileNames = request.getParameter("fileNames");
		if(fileNames != null && fileNames.length()>0)
		{
			String fileName[] = fileNames.split(",");
			for(int i = 0; i < fileName.length; i++)
			{
				File f = new File(request.getSession().getServletContext().getRealPath("/")+"/flashImages/"+fileName[i]+".jpg");
				if(f.exists())
				{
					f.delete();
				}
			}
				
		}
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-store");//Http1.1
		response.setHeader("Pragma", "no-cache");//Http1.0
		response.setDateHeader("Expires", 0);//prevents catching at proxy server
		response.getWriter().write("deleteFlashImages success");
		return null;
	}
	public String exportToWord() throws Exception{
		ActionContext context = ActionContext.getContext();  
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST); 
		request.setAttribute("tableData", span);
		request.setAttribute("chartNum", "2");
		request.setAttribute("titleName", "电压等级统计分析");
		return "export";
	}
	
	public String ajax() throws ParseException{
		System.out.println(deviceTypeSelect+"||"+defect_find_date_end+"****************");
		setVoltageClassList(defectService.getCodeNameDTOList(Constant.VOLTAGECLASS_QUERY, false));
		//避免日期转换异常
		defect_find_date_start = null==defect_find_date_start?"1996-02-07":defect_find_date_start;
		defect_find_date_end = null==defect_find_date_end?"2012-08-19":defect_find_date_end;
		if (deviceTypeSelect.equals("-1")) {
			deviceTypeSelect="%%";
		}
		System.out.println(deviceTypeSelect);
		setDefectByVoltageClass(defectService.getVoltageClassTableData(deviceTypeSelect,defect_find_date_start, defect_find_date_end));
		return "json";
	}
}
