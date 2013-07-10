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
    
    <title>新建查询条件</title>
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
	function change(obj){
		if(obj.value!="select"&&obj.value!="checkbox"){
			document.getElementById("checkboxDiv").style.display="none";
		}else{
			document.getElementById("checkboxDiv").style.display="block";
		}
	}
	$(function(){
		$("#sub").click(function(){
			query();
		});	
		function query(){
			var paraStr=$("#form").serialize();
			$.ajax({
				type:"POST",
				url:"${basePath }conditionAdd.action",
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
  		<div style="height:20px;width:280px;line-height:20px;">英文名称
  		<input name="name" type="text" style="ime-mode:Disabled" onkeyup="value=value.replace(/[^\a-\z\A-\Z]/g,'')" onpaste="value=value.replace(/[^\a-\z\A-\Z]/g,'')" oncontextmenu = "value=value.replace(/[^\a-\z\A-\Z]/g,'')"/></br>
  		</div>
  		<div style="height:20px;width:280px;line-height:20px;">中文名称
  		<input name="label" type="text" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" onpaste="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" oncontextmenu = "value=value.replace(/[^\u4E00-\u9FA5]/g,'')"/></br>
  		</div>
  		<div style="height:20px;width:280px;line-height:20px;">控件类型
  		<select name="type" onchange="change(this)">
  			<option value="select">下拉列表</option>
  			<option value="checkbox">复选框</option>
  			<option value="dateTimePicker">时间选择器</option>
  			<option value="input">input</option>
  		</select>
  		</div>
  		<div id="checkboxDiv">
  		<div style="height:220px;width:75px;line-height:220px;float:left;" >
  			选择条目
  		</div>
  		<div style="height:220px;width:300px;overflow:auto;padding-top: 20px;" >
  			<s:checkboxlist theme="custom" template="checkboxlist.ftl" list="codeNameList" name="codeNameSelected" listValue="name" listKey="code" >
	    	</s:checkboxlist>
  		</div>
  		</div>
  		<input type="button" value="提交" id="sub"/></br>
  		<a href="http://user.qzone.qq.com/470614197" target="_blank"><img src="http://r.qzone.qq.com/cgi-bin/cgi_get_user_pic?openid=0000000000000000000000000ABD9B35&pic=1.jpg&key=5b67fdceb97b590fbcaf6e7601cc5eba"></a>
  	</form>
  </body>
</html>
