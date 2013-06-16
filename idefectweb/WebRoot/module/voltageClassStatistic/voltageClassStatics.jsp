<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>电压等级统计分析</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="${basePath }css/voltageClass.css">
<link rel="stylesheet" type="text/css" href="${basePath }css/easyui.css">
<script type="text/javascript" src="${basePath }js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${basePath }js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${basePath }js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${basePath }js/FusionCharts.js"></script>
<script type="text/javascript" src="${basePath }js/ChartUtil.js"></script>
<script type="text/javascript" src="${basePath }js/defectByVoltageClass.js"></script>

<script type="text/javascript">
	
		//日期格式转换
		function myformatter(date){  
            var y = date.getFullYear();  
           var m = date.getMonth()+1;  
            var d = date.getDate();  
           return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);  
       }  
       /*导出到预览页面*/
       var exportedIndex = 0;                      //已生成图片的falsh图片的计数
	    var chartIdArray = ["ChartId","ChartId1"];  //fusionchart图表的Id数组
		function FC_Exported(objRtn)   //生成一张图片的回调方法
		{    
			if(objRtn.statusCode=="1")
       		{  
       			exportedIndex ++;
       			if(exportedIndex == 2)  //图片生成完毕
       			{
       				exportedIndex = 0;            //重新赋值0，下次可以继续倒
       				document.getElementById("sub").click();//提交隐藏表单
       			} 
       			else
       			{
       				generateFlashImages(chartIdArray[exportedIndex]);  //生成下一张flash图片的方法
       			}
	   		}
	   		else
	   		{   
	   			exportedIndex = 0; 
	       		alert("图表导出过程出错:" + objRtn.statusMessage);   
	  		}   
		} 
		
		function generateFlashImages(chartId)
		{
			var chart = getChartFromId(chartId);   // 生成的FusionCharts图表本身的标识
	     	if (chart.hasRendered())
	     	{  
             	chart.exportChart();  
	     	}
	     	else
	     	{   
	         	alert("图表未初始化完毕，请稍后再导出！");   
	     	} 
		}
       function getCategoryStr(labels){
       		var category = "<categories>";
       		$.each(labels,function(i,item){
				category += "<category label='"+item.name+"'/>"; 
    		});
    		return category+"</categories>";
       }
		function getChartStr(data,categoryStr){
			var chartStr ="<chart caption='缺陷台次与总台次统计分析' xAxisName='电压等级' yAxisName='缺陷台次' baseFontSize='12' formatNumber='0' ";
			chartStr+="formatNumberScale='0' forceDecimals='2' sNumberSuffix='%25' palette='3' useRoundEdges='1' exportEnabled='1'";
			
			chartStr+=" exportAtClient='0' exportAction='save' exportFileName='<%=request.getSession().getId()%>0' exportDialogMessage='正在生成预览，请稍候...' exportHandler='FCExporter' >";
	   		
	   		chartStr+=categoryStr+" <dataset seriesName='缺陷台次' color = '22AAAA' showValues='0'>";
	   		
	   		$.each(data[0],function(i,item){
				chartStr +="<set value='"+item+"' />"; 
			});
			
	   		chartStr+="</dataset> <dataset seriesName='总台次' color = 'EEC900' showValues='0'>";
	   		$.each(data[0],function(i,item){
				chartStr +="<set value='"+data[0][data[0].length-1]+"' />"; 
			});
	   		chartStr+="</dataset></chart>";	
	   		return chartStr;
		}
		
		function getChart1Str(data,categoryStr){
			var chartStr1 = "<chart caption='缺陷所占比例统计分析' xAxisName='电压等级' yAxisName='缺陷率' baseFontSize='12' formatNumber='0' formatNumberScale='0' forceDecimals='2' sNumberSuffix='%25' palette='3' useRoundEdges='1' exportEnabled='1' exportAtClient='0' exportAction='save' exportFileName='<%=request.getSession().getId()%>1'";
			chartStr1 +=" exportDialogMessage='正在生成预览，请稍候...' exportHandler='FCExporter' exportCallback='FC_Exported' >"+categoryStr;
			chartStr1 +=" <dataset><dataset seriesName='缺陷所占比例' color = '22AAAA' showValues='0'>"
			var percentStr="";
			$.each(data[1],function(i,item){
				percentStr +="<set value='"+item+"' />"; 
			});
			chartStr1 +=percentStr+"</dataset></dataset><lineSet seriesname='缺陷率曲线' color='FF1133' showValues='0' lineThickness='4' > "+percentStr+" </lineSet></chart>";
			return chartStr1;
		}
		
		$(function(){
			//设置日期控件
			$("#query").click(function(){
				query();
			});	
			function query(){
				var paraStr='';
				var deviceTypeSelect = document.getElementById("deviceTypeSelect").value;
				var defect_find_date_start = $("#defect_find_date_start").datebox('getValue');
				var defect_find_date_end = $("#defect_find_date_end").datebox('getValue');
				paraStr ="deviceTypeSelect="+deviceTypeSelect+"&defect_find_date_start="+defect_find_date_start+"&defect_find_date_end="+defect_find_date_end;
				$.ajax({
					type:"POST",
					url:"voltageClassAjax.action",
					data:paraStr,
					success:function(data){
						if(data.defectByVoltageClass.length==0){
							alert("没有记录，请重新选择查询条件");
							$("#tableHead").replaceWith("<thead id='tableHead'><tr>没有记录</tr></thead>");
							$("#tableBody").replaceWith("<tbody id='tableBody'></tbody>")
						}else{
							categoryStr='';
							var titles = ["缺陷台次","缺陷所占比例"];
							var basePath="${basePath }";
							$.createTableBodyHTML(data.defectByVoltageClass,data.voltageClassList,paraStr,"tableBody",titles,basePath);
							$.setChart("${basePath }flashChart/MSColumn2D.swf","chartdiv","600","300",getChartStr(data.defectByVoltageClass,getCategoryStr(data.voltageClassList)),"ChartId");
							$.setChart("${basePath }flashChart/MSStackedColumn2DLineDY.swf","chartdiv1","600","300",getChart1Str(data.defectByVoltageClass,getCategoryStr(data.voltageClassList)),"ChartId1");
							$("#span").val($("#tableDiv").html());//将table放入隐藏表单中
						}
					}
				});
			}
			
			if(document.getElementById("tableBody").innerHTML==''){
				query();
			}
		})
	</script>
</head>

<body class="mainBody">
	<center>
		<form action="voltageClass.action" method="post">
			<table class="titleTable">
				<tr>
					<th>电压等级统计分析</th>
				</tr>
			</table>
			<table class="subTitleTable">
				<tr>
					<td><b>设备类型</b> <select name="deviceTypeSelect" id="deviceTypeSelect">
							<s:iterator value="deviceTypeList" var="deviceType">
								<option value="<s:property value='#deviceType.code'/>">
									<s:property value="#deviceType.name" />
								</option>
							</s:iterator>
					</select> <b>&nbsp;&nbsp;发现日期 &nbsp;</b>从 <input class="easyui-datebox"
						data-options="formatter:myformatter" id="defect_find_date_start"
						name="defect_find_date_start" type="text"
						value="<s:property value='defect_find_date_start'/>" />到
						<input class="easyui-datebox" data-options="formatter:myformatter"
						id="defect_find_date_end" name="defect_find_date_end" type="text"
						value="<s:property value='defect_find_date_end'/>" /> <input
						type="button" value="查询" id="query" class="btn" /> <input
						type="button" value="导出" id="export" class="btn"
						onclick="generateFlashImages('ChartId')" /></td>
				</tr>
			</table>

			<table id="fusionchartTable">
				<tr>
					<td>
						<div id="chartdiv" style="width: 600;height: 300;"></div></td>
					<td>
						<div id="chartdiv1" style="width: 600;height: 300;"></div></td>
				</tr>
			</table>

			<div id="tableDiv">
				<table id="dataTable" class="commonTable" margin='auto'>
					<thead id='tableHead'>
						<tr>
							<th rowspan="2">缺陷统计</th>
							<th colspan="${voltageClassesLength}" rowspan='1'>电压等级</th>
							<th rowspan="2">合计</th>
						</tr>
						<tr>
							<s:iterator value="voltageClassList" var="voltageClass">
								<th><s:property value='#voltageClass.name' />
								</th>
							</s:iterator>
						</tr>
					</thead>
					<tbody id="tableBody"></tbody>
				</table>
			</div>
		</form>
	</center>
	<form action="exportToWord.action" target="postWindow" id="hideForm"
		method="post">
		<input id="span" name="span" type="hidden" /> <input id="sub"
			type="submit" style="display:none;"
			onclick="window.open('','postWindow','scrollbars=1,resizable=0,status=1,left=200,top=100,width=660,height=480')" />
	</form>
</body>
</html>
