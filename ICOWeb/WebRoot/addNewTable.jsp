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
    
    <title>新建表格</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${basePath }js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript">
	function addTfoot(div1,div2){
		$(div1).append($(div2).html());
	}
	
	$(function(){
		document.getElementById("TFootDiv").style.display="none";
		$("#sub").click(function(){
			query();
		});	
		function query(){
			var paraStr=$("#form").serialize();
			$.ajax({
				type:"POST",
				url:"${basePath }tableAdd.action",
				data:paraStr,
				success:function(data){
					alert("添加成功！");
				}
			});
		}
	});
	</script>
  </head>
  
  <body>
  <form id="form">
  		<div id="TableDiv" >
  			<table>
  				<tr>
  					<td>tableName</td><td><input name="tableName" type="text"/></td>
  				</tr>
  				<tr>
  					<td>tHeadHTML</td><td><textarea name="tHeadHTML" type="text" style="ime-mode:Disabled" cols="60" rows="10"></textarea></td>
  				</tr>
  				<tr>
  					<td>tBodySql</td><td><textarea name="tBodySql" type="text" style="ime-mode:Disabled" cols="60" rows="10"></textarea></td>
  				</tr>
  			</table>
  		</div>
  		<div id="TFootsDiv">
  			<table>
  				<tr>
  					<td>tFoot_title</td><td><input name="tFoot_title" type="text"></td>
  				</tr>
  				
  				<tr>
  					<td>tFoot_sql</td><td><textarea name="tFoot_sql" type="text" style="ime-mode:Disabled" cols="60" rows="10"></textarea></td>
  				</tr>
  			</table>
  		</div>
  		<input type="button" value="添加tfoot" onclick="addTfoot(TFootsDiv,TFootDiv)">
  		<div id="TFootDiv">
  			<table>
  				<tr>
  					<td>tFoot_title</td><td><input name="tFoot_title" type="text"></td>
  				</tr>
  				
  				<tr>
  					<td>tFoot_sql</td><td><textarea name="tFoot_sql" type="text" style="ime-mode:Disabled" cols="60" rows="10"></textarea></td>
  				</tr>
  			</table>
  		</div>
  		<input type="button" value="提交" id="sub"/></br>
  	</form>
  </body>
</html>
