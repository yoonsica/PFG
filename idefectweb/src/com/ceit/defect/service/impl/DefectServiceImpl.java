package com.ceit.defect.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.ceit.defect.service.DefectService;
import com.ceit.defect.constant.Constant;
import com.ceit.defect.dao.UnitDeviceDao;
import com.ceit.defect.dao.VoltageClassDefectDao;
import com.ceit.ico.dao.NativeQueryDao;
import com.ceit.ico.dto.CodeNameDTO;

public class DefectServiceImpl implements DefectService {
	private NativeQueryDao nativeQueryDao;
	private VoltageClassDefectDao voltageClassDefectDao;
	private UnitDeviceDao unitDeviceDao;
	public UnitDeviceDao getUnitDeviceDao() {
		return unitDeviceDao;
	}

	public void setUnitDeviceDao(UnitDeviceDao unitDeviceDao) {
		this.unitDeviceDao = unitDeviceDao;
	}

	public VoltageClassDefectDao getVoltageClassDefectDao() {
		return voltageClassDefectDao;
	}

	public void setVoltageClassDefectDao(VoltageClassDefectDao voltageClassDefectDao) {
		this.voltageClassDefectDao = voltageClassDefectDao;
	}

	public NativeQueryDao getNativeQueryDao() {
		return nativeQueryDao;
	}

	public void setNativeQueryDao(NativeQueryDao nativeQueryDao) {
		System.out.println("setNativeQueryDao*************");
		this.nativeQueryDao = nativeQueryDao;
	}

	/*获取电压等级统计表格*/
	public List<Object[]> getVoltageClassTableData(String devSelected,
			String startTime, String endTime) {
		String sql = nativeQueryDao.getQuerySql(Constant.DEFECT_STATIC_VOL, Constant.RECORD_LIST);
		//String sql = "select t1.name, count(*), t.defect_voltage_class_code  from fz_defect t, volmap t1 where t.belong_device_id is not null and t1.display > 0 and t.defect_voltage_class_code = t1.code and t.device_type_code like ?1 and t.find_date between to_date(?2,'yyyy-mm-dd') and to_date(?3,'yyyy-mm-dd') group by t1.name,t.defect_voltage_class_code order by t1.name,t.defect_voltage_class_code";
		List<Object[]> tempList = voltageClassDefectDao.getDefectByVoltageClass(sql, devSelected, startTime, endTime);
		List<Object[]> resultList = new ArrayList<Object[]>();
		List<CodeNameDTO> voltageClasses = nativeQueryDao.getCodeNameDTOList(Constant.VOLTAGECLASS_QUERY, false);
		Object[] objects = new Object[voltageClasses.size()+1];//+1为总计
		for (int i = 0; i < voltageClasses.size(); i++) {
			for (int j = 0; j < tempList.size(); j++) {
				if (voltageClasses.get(i).getCode().equals(tempList.get(j)[2])) {
					objects[i]= tempList.get(j)[1];
					break;
				}else {
					objects[i]=new Long(0);
				}
			}
		}
		objects[voltageClasses.size()]=new Long(0);
		System.out.println(objects[objects.length-1]);
		BigDecimal temp =new BigDecimal(0);
		for (int i = 0; i < objects.length-1; i++) {
			temp=temp.add((BigDecimal)objects[i]);
		}
		objects[objects.length-1]=temp;
		resultList.add(objects);//第一行为对应电压等级的个数和总数
		//计算缺陷率
		Float[] defectPercent = new Float[voltageClasses.size()+1];//+1为总计
		for (int i = 0; i < defectPercent.length; i++) {
			if (((BigDecimal)objects[objects.length-1]).intValue()!=0) {
				int a = ((BigDecimal)objects[i]).intValue();
				int b = ((BigDecimal)objects[objects.length-1]).intValue();
				defectPercent[i]=((float)a*100/b);
				DecimalFormat myFormat = new DecimalFormat("0.00");
				String strFloat = myFormat.format(defectPercent[i]);
				defectPercent[i]=Float.parseFloat(strFloat);
			}else {
				defectPercent[i]=(float) 0.00;
			}
			
		}
		resultList.add(defectPercent);
		return resultList;
	}

	public List<CodeNameDTO> getCodeNameDTOList(String id, boolean addAll) {
		return nativeQueryDao.getCodeNameDTOList(id, addAll);
	}
	
	public String generateQuery(String[] dydj, String[] sblx, String[] ssds,
			String tyrq_start, String tyrq_end){
		StringBuffer sb = new StringBuffer("select t7.name,");
		for (int i = 0; i < dydj.length-1; i++) {
			sb.append("sum(decode(dy, '"+dydj[i]+"', count, 0)),");
		}
		sb.append("sum(decode(dy, '"+dydj[dydj.length-1]+"', count, 0)) ")
		  .append("from (select t.ssds id, t.dydj dy, t.sblx sblx, count(*) count  from pms.equ_bd_equipment t,")
          .append("pms.equ_bd_yw_yxwz t1,pms.equ_bd_yw_jg t2,pms.equ_bd_yw_bdz t3,pms.equ_codemap t4,pms.equ_codemap t5 ")    
          .append("where t.ls_yxwz_id = t1.obj_id and t1.ls_jg = t2.obj_id and t3.obj_id = t2.ls_bdz ")
          .append("and t4.stdcodeid = t.yxzt and t4.codetypename = '运行状态' and t5.codeid = t.sblx ")
          .append("and t5.codetypename = '设备类型' and t.tyrq > to_date('"+tyrq_start+"', 'yyyy-MM-dd') ")
          .append("and t.tyrq < to_date('"+tyrq_end+"', 'yyyy-MM-dd') and t.dydj in (");
		for (int i = 0; i < dydj.length-1; i++) {
			sb.append(dydj[i]+",");
		}
        sb.append(dydj[dydj.length-1]+") and t.ssds in(");
        for (int i = 0; i < ssds.length-1; i++) {
			sb.append(ssds[i]+",");
		}
        sb.append(ssds[ssds.length-1]+") and t.sblx in(");
        for (int i = 0; i < sblx.length; i++) {
        	sb.append(sblx[i]+",");
		}
        sb.append(sblx[sblx.length-1]+") group by t.ssds, t.dydj, t.sblx) t6 ")
        .append("left join pms.DEPARTMENT t7 on t6.id=t7.id  group by name"); 
        System.out.println(sb.toString());
		return sb.toString();
	}
	
	public List<Object[]> getUnitDeviceData(String sql){
		return unitDeviceDao.getUnitDeviceData(sql);
	}
	
	public List<String> getTableHeadList(String[] dydj){
		return unitDeviceDao.getVoltagesByIds(dydj);
	}
}
