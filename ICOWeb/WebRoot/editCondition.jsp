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
    
    <title>编辑查询条件</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${basePath }js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript">
	$(function(){
		//设置日期控件
		$("#sub").click(function(){
			query();
		});	
		function query(){
			var paraStr=$("#form").serialize();
			$.ajax({
				type:"POST",
				url:"${basePath }conditionEdit.action",
				data:paraStr,
				success:function(data){
					alert("s");
				}
			});
		}
	});
	</script>
  </head>
  
  <body>
  <form id="form">
  <input style="display: none;" type="text" name="conditionId" 
  		value="<s:property value='conditionId'/>">
  		<div style="height:20px;width:80px;line-height:20px;float:left;">英文名称</div>
  		<input name="name" type="text" style="ime-mode:Disabled" onkeyup="value=value.replace(/[^\a-\z\A-\Z]/g,'')"
  		onpaste="value=value.replace(/[^\a-\z\A-\Z]/g,'')" oncontextmenu = "value=value.replace(/[^\a-\z\A-\Z]/g,'')"
  		 value="<s:property value='Condition.name'/>"/></br>
  		<div style="height:20px;width:80px;line-height:20px;float:left;">中文名称</div>
  		<input name="label" type="text" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" 
  		onpaste="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" 
  		oncontextmenu = "value=value.replace(/[^\u4E00-\u9FA5]/g,'')" 
  		value="<s:property value='Condition.label'/>"/></br>
  		<div style="height:20px;width:80px;line-height:20px;float:left;">控件类型</div>
  		<select name="type">
  			<option value="select">下拉列表</option>
  			<option value="checkbox">复选框</option>
  		</select>
  		<div style="height:220px;width:80px;line-height:220px;float:left;" >
  			选择条目
  		</div>
  		<div style="height:220px;width:300px;overflow:auto;padding-top: 20px;" >
  			<s:checkboxlist theme="custom" template="checkboxlist.ftl" list="codeNameList" value="codeChecked" name="codeNameSelected" listValue="name" listKey="code" >
	    	</s:checkboxlist>
  		</div>
  		<input type="button" value="提交" id="sub"/></br>
  		<a href="http://user.qzone.qq.com/470614197" target="_blank"><img src="http://r.qzone.qq.com/cgi-bin/cgi_get_user_pic?openid=0000000000000000000000000ABD9B35&pic=1.jpg&key=5b67fdceb97b590fbcaf6e7601cc5eba"></a>
  	</form>
  	<s:debug></s:debug>
  </body>
</html>
