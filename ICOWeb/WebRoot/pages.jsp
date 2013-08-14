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

<title>页面列表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="页面列表">
<style type="text/css">
body {
	margin: auto; /* center in viewport */
	width: 960px;
}
</style>
</head>
<center>
<body>
<ul>
	<s:iterator value="pageList" var="page">
	<li><a href="pageShow.jsp?pageId=<s:property value="#page.pageId"/>">
	<s:property value="#page.pageName"/></a></li>
	</s:iterator>
	</ul>
</body>
</center>
</html>
