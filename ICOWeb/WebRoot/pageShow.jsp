<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
	String pageId = request.getParameter("pageId");
	request.setAttribute("pageId", pageId);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title><s:property value="page.pageName"/></title>
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
	/*margin: 10px,100px,10px,100px;
	width: 960px;*/
}

.titleDiv {
	background-color: green;
	color: white;
	width:960px;
	font-weight: 800;
	height: 30px;
	line-height: 30px;
}

.conditionsDiv {
	width:960px;
	height: 30px;
	line-height: 30px;
	border:1px solid black;
}

.conditionDiv {
	float:left;
	height: 30px;
	line-height: 30px;
	width:auto;
	padding-right:10px;
	text-align:left;
	font-weight: bolder;
}

.chartsDiv{
	width:960px;
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
	width:960px;
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
}
.tableDiv table td{
	border:1px solid black;
}
</style>
<script type="text/javascript">
Ext.onReady( function() {
	var myMask = new Ext.LoadMask(Ext.getBody(), {msg:"Please wait..."});
	myMask.show();
	
function myformatter(date){  
    var y = date.getFullYear();  
   var m = date.getMonth()+1;  
    var d = date.getDate();  
   return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);  
} 

function conditionHandler(data){
	var conditionHTML="";
	$.each(data.conditionSet, function(i, condition) {
		conditionHTML+= "<div class=\"conditionDiv\"><label for=\"";
		conditionHTML += condition.name + "\">" + condition.label + "</label>";
		if(condition.type=="select"){
			conditionHTML+="<select id=\"" + condition.name	+ "\" name=\""+condition.name+"\"><option value=\"all\">全部</option>";
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
			if(condition.name=="dateStart"){
				conditionHTML += "<input id='"+condition.name+"' type='text' name='"+condition.name+"' value='2003-1-1'/>";
			}else{
				conditionHTML += "<input id='"+condition.name+"' type='text' name='"+condition.name+"' value='2013-1-1'/>";
			}
		}
		conditionHTML+="</div>";
	});
	conditionHTML+="<div class=\"conditionDiv\"><input type=\"button\" value=\"提交\" id=\"sub\"/></div>";
	$("#conditionsDiv").html(conditionHTML);
	$("#titleDiv").text(data.pageName);
	$.each(data.conditionSet, function(i, condition) {
		if(condition.type=="dateTimePicker"){
			$('#'+condition.name).datebox({  
			    required:true  
			});
		}
	});
}

	function chartHandler(data){
		$.each(data.chartSet, function(i, chart) {
			var divId = "chartDiv"+chart.chartId;
			$("#chartsDiv").append("<div id='"+divId+"' class='chartDiv'></div>");
			var fschart = new FusionCharts("${basePath }/flashChart/"+chart.chartType+".swf","ChartId", "480", "340", "0", "0");
			fschart.setDataXML(chart.chartXmlStr);
			fschart.render(divId);
		});
	}

	function tableHandler(data){
		$.each(data.tableSet, function(i, table) {
			var divId = "tableDiv"+table.tableId;
			$("#tablesDiv").append("<div id='"+divId+"' class='tableDiv'></div>");
			$("#"+divId).html(table.tableHTML);
		});
		myMask.hide();
	}
	
	function init(){
		$.ajax({
			type:"POST",
			url:"${basePath }page!show",
			data:"&pageId=${pageId }",
			success:function(data){
				conditionHandler(data.page);
				chartHandler(data.page);
				tableHandler(data.page);
			}
		});
	}
	
	$(function() {
		init();
	})

});
</script>
</head>
<center>
<body>
<form id="form">
<div class="titleDiv" id="titleDiv"></div>
	<div class="conditionsDiv" id="conditionsDiv">
	</div><%--
	<input type="button" value="提交" id="sub"/></br>
	--%><div id="chartsDiv" class="chartsDiv">
	</div>
	<div id="tablesDiv" class="tablesDiv">
	</div>
</form>
</body>
</center>
</html>
