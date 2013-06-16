package com.ceit.tool.util;

import java.util.List;
import java.util.Map;

/**
 * FusionChart相关操作
 * @author ceit
 *
 */
public class FusionChartUtil {
	/**
	 * 生成fusionchart 适用于pie2D，bar2D，line2D
	 * @param Title
	 * @param map
	 * @return
	 */
	public static String getSingleChart(String title, Map<String, String> map,
			Map<String,String> paraMap) {
		StringBuilder chartXML=new StringBuilder();
		chartXML.append("<chart caption='").append(title)
		.append("' baseFontSize='12' formatNumber='0' "); //字体,格式化数据整数部分每 整数部分每 3位是否用逗号分隔
		if (paraMap != null) {
			for (String para : paraMap.keySet()) {
				chartXML.append(para).append("='").append(paraMap.get(para))
						.append("' ");
			}
		}
		//formatNumberScale是否格式化精度。 默认为1000 ->1K 1000 >1K 1000>1K 1000>1K 1000 K->1M >1M 1000M 
		//forceDecimals 精度不够的时候是否 精度不够的时候是否 末尾填0
		chartXML.append(" formatNumberScale='0' forceDecimals='1' ")
		//：enableSmartLabels 是否启用 智能标签
		.append(" enableSmartLabels='1' enableRotation='0'")
		//背景颜色、透明度
		.append(" bgColor='99CCFF,FFFFFF' bgAlpha='40,100'")
		//各个背景色的比例和渐变方向
		.append(" bgRatio='0,100' bgAngle='360'")
		//是否显示变框
		.append(" showBorder='1' startingAngle='70'>");
		for(String key:map.keySet()){
			chartXML.append("<set label='").append(key).append("' value='")
					.append(map.get(key)).append("'/>");
		}
		chartXML.append("</chart>");
		return chartXML.toString();
	}
	
	/** 
	 * 饼图
	 * @param title
	 * @param map
	 * @return
	 */
	public static String getPie2D(String title,Map<String,String> map){
		Map<String,String> paraMap=CollectionUtil.newInstanceHashMap();
		paraMap.put("startingAngle", "70");							//起始角度
		
		return getSingleChart(title,map,paraMap);
	}
	
	/**
	 *  柱状图
	 * @param title
	 * @param map
	 * @param xAxisName
	 * @param yAxisName
	 * @param numberSuffix
	 * @return
	 */
	public static String getBar2D(String title,Map<String,String> map,String xAxisName,String yAxisName,String numberSuffix){
		//chart caption='按缺陷等级消缺率统计' baseFontSize='12' xAxisName='缺陷等级' yAxisName='消缺率' showValues='0' decimals='0' formatNumberScale='0' bgColor='99CCFF,FFFFFF' bgAlpha='20,100' chartRightMargin='30' useRoundEdges='1'><
		Map<String,String> paraMap=CollectionUtil.newInstanceHashMap();
		paraMap.put("xAxisName", xAxisName); 
		paraMap.put("yAxisName", yAxisName);
		paraMap.put("numberSuffix", numberSuffix);			//数字后缀
		paraMap.put("useRoundEdges", "1");					//是否用圆角矩形 是否用圆角矩形 和玻璃效果 。
		paraMap.put("showValues", "0");
		return getSingleChart(title,map,paraMap);
		
	}
	
	
	
	
	/**
	 * "<chart caption='按供电公司分类统计'  baseFontSize='12' formatNumber='0' 
	 * formatNumberScale='0' forceDecimals='2' sNumberSuffix='%25'
	 *  palette='3' useRoundEdges='1'>
	 *  <categories fontColor='000000'>
	 *  <category label='保定供电公司'/><category label='沧州供电公司'/>
	 *  <category label='邯郸供电公司'/><category label='河北省电力公司超高压输变电分公司'/>
	 *  <category label='衡水供电公司'/><category label='石家庄供电公司'/><category label='邢台供电公司'/>
	 *  </categories>
	 *  <dataSet seriesName='已消缺' color='00AA33' showValues='0'><
	 *  set value='579' /><set value='7422' /><set value='5447' />
	 *  <set value='1781' /><set value='8977' /><set value='4730' />
	 *  <set value='7874' /></dataSet>
	 *  <dataSet seriesName='未消缺' showValues='0'><set value='1131' />
	 *  <set value='372' /><set value='192' /><set value='716' />
	 *  <set value='237' /><set value='450' /><set value='90' />
	 *  </dataSet>
	 *  <lineSet seriesname='消缺率' color='FF1133' showValues='0' lineThickness='4' >
	 *  <set value='33' /><set value='95' /><set value='96' />
	 *  <set value='71' /><set value='97' /><set value='91' />
	 *  <set value='98' />
	 *  </lineSet>
	 *  </chart>"
	 *  
	 *  
	 */
	
	
	/**
	 * 
	 * @param title				标题
	 * @param map				x轴数组和对应的值  值是数组
	 * @param datasetArray		dataset数组
	 * @param linesetArray		lineset数组
	 * @param paraMap			图形参数
	 * @return
	 */
	public static String getMutiChart(String title,Map<String,String[]> map ,String[] datasetArray,String[] linesetArray,Map<String,String> paraMap){
		StringBuilder chartXML=new StringBuilder();
		
		
		StringBuilder categeryString=new StringBuilder("<categories fontColor='000000'>");
		List<StringBuilder> setList=CollectionUtil.newInstanceArrayList();
		for(int i=0;i<datasetArray.length;i++){
			StringBuilder setString=new StringBuilder();
			setString.append("<dataSet seriesName='").append(datasetArray[i]).append("'  showValues='0'>");
			
			setList.add(setString);
			
		}
		for(int i=0;i<linesetArray.length;i++){
			StringBuilder setString=new StringBuilder();
			setString.append("<lineSet seriesName='").append(linesetArray[i]).append("'  showValues='0'>");
			setList.add(setString);
			
		}
		
		for(String key:map.keySet()){
			categeryString.append("<category label='").append(key).append("'/>");
			String[] valueArray=map.get(key);
			for(int i=0;i<setList.size();i++){
				StringBuilder s=setList.get(i);
				s.append("<set value='").append(valueArray[i]).append("' />");
			}
			
		}
		categeryString.append("</categories>");
		chartXML.append("<chart caption='").append(title)
		.append("' baseFontSize='12' formatNumber='0' ");
		if (paraMap != null) {
			for (String para : paraMap.keySet()) {
				chartXML.append(para).append("='").append(paraMap.get(para))
						.append("' ");
			}
		}
		//formatNumberScale是否格式化精度。 默认为1000 ->1K 1000 >1K 1000>1K 1000>1K 1000 K->1M >1M 1000M 
		//forceDecimals 精度不够的时候是否 精度不够的时候是否 末尾填0
		chartXML.append(" formatNumberScale='0' forceDecimals='1' ")
		//：enableSmartLabels 是否启用 智能标签
		.append(" enableSmartLabels='1' enableRotation='0'")
		//背景颜色、透明度
		.append(" bgColor='99CCFF,FFFFFF' bgAlpha='40,100'")
		//各个背景色的比例和渐变方向
		.append(" bgRatio='0,100' bgAngle='360'").append(" useRoundEdges='1' ")
		//是否显示变框
		.append(" showBorder='1' startingAngle='70'>");
		chartXML.append(categeryString).append("<dataset>");
		for(int i=0;i<setList.size();i++){
			chartXML.append(setList.get(i));
			if(i<datasetArray.length){
				chartXML.append("</dataSet>");
				if(i==datasetArray.length-1){
					chartXML.append("</dataset>");
				}
			}else{
				chartXML.append("</lineSet>");
			}
		}
		chartXML.append("</chart>");
		

		
		
		
		return chartXML.toString();
	}
	
	/**
	 * 
	 * @param title
	 * 		标题
	 * @param map
	 * 		横轴标签和纵轴相应的值
	 * @param datasetArray
	 * 		柱状名称
	 * @param linesetArray
	 * 		曲线名称
	 * @param paraMap
	 * 		图的参数
	 * @param paraList
	 * 		每个数据集的参数
	 * @return
	 */
	public static String getMutiChart(String title,Map<String,String[]> map ,String[] datasetArray,String[] linesetArray,Map<String,String> paraMap,List<Map<String,String>> paraList){
		StringBuilder chartXML=new StringBuilder();
		
		
		StringBuilder categeryString=new StringBuilder("<categories fontColor='000000'>");
		List<StringBuilder> setList=CollectionUtil.newInstanceArrayList();
		for(int i=0;i<datasetArray.length;i++){
			StringBuilder setString=new StringBuilder();
			setString.append("<dataSet seriesName='").append(datasetArray[i]).append("' ");
			if(paraList!=null&&paraList.size()>i){
				Map<String,String> setParaMap=paraList.get(i);
				for(String key:setParaMap.keySet()){
					setString.append(key).append("='").append(setParaMap.get(key)).append("' ");
				}
			}
			setString.append("  showValues='0'>");
			setList.add(setString);
			
		}
		for(int i=0;i<linesetArray.length;i++){
			StringBuilder setString=new StringBuilder();
			setString.append("<lineSet seriesName='").append(linesetArray[i]).append("' ");
			if(paraList!=null&&paraList.size()>i+datasetArray.length){
				Map<String,String> setParaMap=paraList.get(i+datasetArray.length);
				for(String key:setParaMap.keySet()){
					setString.append(key).append("='").append(setParaMap.get(key)).append("' ");
				}
			}
			setString.append("  showValues='0'>");
			setList.add(setString);
			
		}
		
		for(String key:map.keySet()){
			categeryString.append("<category label='").append(key).append("'/>");
			String[] valueArray=map.get(key);
			for(int i=0;i<setList.size();i++){
				StringBuilder s=setList.get(i);
				s.append("<set value='").append(valueArray[i]).append("' />");
			}
			
		}
		categeryString.append("</categories>");
		chartXML.append("<chart caption='").append(title)
		.append("' baseFontSize='12' formatNumber='0' ");
		if (paraMap != null) {
			for (String para : paraMap.keySet()) {
				chartXML.append(para).append("='").append(paraMap.get(para))
						.append("' ");
			}
		}
		//formatNumberScale是否格式化精度。 默认为1000 ->1K 1000 >1K 1000>1K 1000>1K 1000 K->1M >1M 1000M 
		//forceDecimals 精度不够的时候是否 精度不够的时候是否 末尾填0
		chartXML.append(" formatNumberScale='0' forceDecimals='1' ")
		//：enableSmartLabels 是否启用 智能标签
		.append(" enableSmartLabels='1' enableRotation='0'")
		//背景颜色、透明度
		.append(" bgColor='99CCFF,FFFFFF' bgAlpha='40,100'")
		//各个背景色的比例和渐变方向
		.append(" bgRatio='0,100' bgAngle='360'").append(" useRoundEdges='1' ")
		//是否显示变框
		.append(" showBorder='1' startingAngle='70'>");
		chartXML.append(categeryString).append("<dataset>");
		for(int i=0;i<setList.size();i++){
			chartXML.append(setList.get(i));
			if(i<datasetArray.length){
				chartXML.append("</dataSet>");
				if(i==datasetArray.length-1){
					chartXML.append("</dataset>");
				}
			}else{
				chartXML.append("</lineSet>");
			}
		}
		chartXML.append("</chart>");
		

		
		
		
		return chartXML.toString();
	}
	
	
	

}
