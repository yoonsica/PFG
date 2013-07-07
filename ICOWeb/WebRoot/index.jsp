<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    
    <title>配置页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css"
	href="${basePath}css/easyui.css">
	<script type="text/javascript"
		src="${basePath}js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript"
		src="${basePath}js/jquery.easyui.min.js"></script>
	<style>
		body {
			margin: auto; /* center in viewport */
			width: 960px;
		}
		.titleDiv{
			background-color: green;
			color: white;
			font-weight: 800;
			height:30px;
			line-height:30px;
		}
		.conditionsDiv{
			background-color:gray;
			height:300px;
		}
		.conditionDiv{
		padding-left:5px;
		padding-right:5px;
		line-height: 50px;
		}
	</style>
  </head>
  
  <body>
    选择查询条件
    <div class="conditionsDiv">
		<s:iterator value="conditionHTMLList" var="fuck">
			<s:property escape="0" value="#fuck"/>
			<%--
			如果不加escape=0，会把html当作正常string输出在页面
			--%>
		</s:iterator>
	</div>
  </body>
</html>
