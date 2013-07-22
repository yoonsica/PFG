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
	height: 300px;
}

.conditionDiv {
	padding-left: 5px;
	padding-right: 5px;
	line-height: 50px;
}
</style>
<script type="text/javascript">
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
		conditionHTML += "<a href=\"${basePath }conditionToBeEdit.action?conditionId="+condition.conditionId+"\">编辑</a></div>";
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
		alert(chart.caption);
	});
}
	
	$(function() {
		$.ajax({
			type : "POST",
			url : "icoConfigJson.action",
			success : function(data) {
				conditionHandler(data);
				
			}
		});
	})
</script>
</head>

<body>
	<form action="${basePath }icoConfig.action">
		查询条件
		<a href="${basePath }toConditionAdd.action">添加</a>
		<div class="conditionsDiv" id="conditionsDiv">
		</div>
		<input type="submit" value="提交">
	</form>
</body>
</html>
