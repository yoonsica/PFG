<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>配置页面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="${basePath}JQuery/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${basePath}JQuery/themes/icon.css">
<script type="text/javascript" src="${basePath}JQuery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${basePath}JQuery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${basePath}js/FusionCharts.js"></script>
<link rel="stylesheet" type="text/css"
			href="${basePath }js/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="${basePath }js/ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="${basePath }js/ext/ext-all.js"></script>
		<script type="text/javascript" src="${basePath }js/ext/ext-lang-zh_CN.js"></script>
<style>
body {
	margin: auto; /* center in viewport */
	width: 960px;
}

.titleDiv {
	background-color: green;
	color: white;
	font-weight: 800;
	height: 30px;
	line-height: 30px;
}

.conditionsDiv {
	background-color: gray;
	height: auto;
}

.conditionDiv {
	padding-left: 5px;
	padding-right: 5px;
	line-height: 50px;
}
.chartDiv{
	float: left;
}
.tableDiv{
	float:left;
}
.tablesDiv{
	width:960px;
}

.tableDiv table{
	width:"100%";
	border-collapse:collapse;
	border:1px solid black;
	text-align:center;
	font-size:12;
}
.tableDiv table th{
	font-size:12px;	
	border:1px solid black;
	background:#6baa6b;
	font-weight:bold;
	height:25px;
	text-align: center;
}
.tableDiv table td{
	border:1px solid black;
}
</style>
<script type="text/javascript">
Ext.onReady( function() {
	var myMask = new Ext.LoadMask(Ext.getBody(), {msg:"Please wait..."});
	myMask.show();

$(function() {
	$.ajax({
		type : "POST",
		url : "page!toAddPage",
		success : function(data) {
			
			//myMask.show();
			conditionHandler(data);
			chartHandler(data);
			tableHandler(data);
			//myMask.hide();
		}
	});
})

function myformatter(date){  
    var y = date.getFullYear();  
   var m = date.getMonth()+1;  
    var d = date.getDate();  
   return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);  
} 

function conditionHandler(data){
	var conditionHTML="";
	$.each(data.conditionList, function(i, condition) {
		conditionHTML+= "<div class=\"conditionDiv\"><input type=\"checkbox\" name=\"conditionChecked\" value=\""+condition.conditionId+"\"\><label for=\"";
		conditionHTML += condition.name + "\">" + condition.label + "</label>";
		if(condition.type=="select"){
			conditionHTML+="<select id=\"" + condition.name	+ "\"><option value=\"all\">全部</option>";
			$.each(condition.codeNameSet,function(j,codeName){
				conditionHTML+="<option value=\""+codeName.code+"\">"+codeName.name+"</option>";
			});
			conditionHTML+="</select>";
		}else if(condition.type=="checkbox"){
			$.each(condition.codeNameSet,function(j,codeName){
				conditionHTML+="<checkbox name='"+condition.Name+"' value=\""+codeName.code+"\">"+codeName.name+"</checkbox>";
			});
		}else if(condition.type=="input"){
			conditionHTML += "<input name='"+condition.name+"' type='text' />";
		}else if(condition.type=="dateTimePicker"){
			conditionHTML += "<input id='"+condition.name+"' type='text' name='"+condition.name+"' />";
		}
		conditionHTML += "<a href=\"${basePath }condition!toBeEdit?conditionId="+condition.conditionId+"\">编辑</a></div>";
	});
	$("#conditionsDiv").html(conditionHTML);
	$.each(data.conditionList, function(i, condition) {
		if(condition.type=="dateTimePicker"){
			$('#'+condition.name).datebox({  
			    required:true  
			});
		}
	});
}

	function chartHandler(data){
		$.each(data.chartList, function(i, chart) {
			var divId = "chartDiv"+chart.chartId;
			$("#chartsDiv").append("<div id='"+divId+"' class='chartDiv'></div>");
			var fschart = new FusionCharts("${basePath }/flashChart/"+chart.chartType+".swf","ChartId", "498", "340", "0", "0");
			fschart.setDataXML(chart.chartXmlStr);
			fschart.render(divId);
			$("#"+divId).append("</br><input type=\"checkbox\" name=\"chartChecked\" value='"+chart.chartId+"' />"+chart.caption);
		});
	}

	function tableHandler(data){
		$.each(data.tableList, function(i, table) {
			var divId = "tableDiv"+table.tableId;
			$("#tablesDiv").append("<div id='"+divId+"' class='tableDiv'></div>");
			$("#"+divId).html(table.tableHTML);
			$("#"+divId).append("</br><input type=\"checkbox\" name=\"tableChecked\" value='"+table.tableId+"' />"+table.tableName);
		});
		myMask.hide();
	}
	
});
	
</script>
</head>

<body>
	<form action="${basePath }page!add">
		页面名称<input type="text" name="pageName"></br>
		查询条件
		<a href="${basePath }condition!toConditionAdd" target="">添加</a>
		<div class="conditionsDiv" id="conditionsDiv">
		</div>
		fusionChart
		<a href="${basePath }addNewChart.jsp">添加</a>
		<div id="chartsDiv">
		</div>
		<div id="tablesDiv" class="tablesDiv">
		表格
		<a href="${basePath }addNewTable.jsp">添加</a>
		</div>
		<input type="submit" value="提交">
	</form>
</body>
</html>
