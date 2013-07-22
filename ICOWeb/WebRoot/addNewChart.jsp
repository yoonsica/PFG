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
    
    <title>新建FusionChart</title>
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
		if(obj.value =="Pie2D"){
			document.getElementById("MSStackedColumn2DLineDYDiv").style.display="none";
			document.getElementById("Pie2DDiv").style.display="block";
		}else if(obj.value =="MSStackedColumn2DLineDY"){
			document.getElementById("Pie2DDiv").style.display="none";
			document.getElementById("MSStackedColumn2DLineDYDiv").style.display="block";
		}
	}
	
	function addSet(div1,div2){
		$(div1).append($(div2).html());
	}
	
	function removeSet(div){
		$(div).remove();
	}
	
	$(function(){
		document.getElementById("MSStackedColumn2DLineDYDiv").style.display="none";
		document.getElementById("Pie2DDiv").style.display="block";
		$("#sub").click(function(){
			query();
		});	
		function query(){
			var paraStr=$("#form").serialize();
			$.ajax({
				type:"POST",
				url:"${basePath }chartAdd.action",
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
  		<div style="height:20px;width:280px;line-height:20px;">控件类型
  		<select name="chartType" onchange="change(this)">
  			<option value="Pie2D">Pie2D</option>
  			<option value="MSStackedColumn2DLineDY">MSStackedColumn2DLineDY</option>
  		</select>
  		</div>
  		<div id="Pie2DDiv" >
  			<table>
  				<tr>
  					<td>caption</td><td><input name="Pie2D_caption" type="text"/></td>
  				</tr>
  				<tr>
  					<td>setSql</td><td><textarea name="Pie2D_setSql" type="text" style="ime-mode:Disabled" cols="60" rows="10"></textarea></td>
  				</tr>
  			</table>
  		</div>
  		<div id="MSStackedColumn2DLineDYDiv">
  			<table>
  				<tr>
  					<td>caption</td><td><input name="MSStackedColumn2DLineDY_caption" type="text"></td>
  				</tr>
  				<tr>
  					<td>xAxisName</td><td><input name="MSStackedColumn2DLineDY_xAxisName" type="text"></td>
  				</tr>
  				<tr>
  					<td>PYaxisname</td><td><input name="MSStackedColumn2DLineDY_PYaxisname" type="text"></td>
  				</tr>
  				<tr>
  					<td>SYAxisName</td><td><input name="MSStackedColumn2DLineDY_SYAxisName" type="text"></td>
  				</tr>
  				<tr>
  					<td>categoriesSql</td><td><textarea name="MSStackedColumn2DLineDY_categoriesSql" type="text" style="ime-mode:Disabled" cols="60" rows="10"></textarea></td>
  				</tr>
  			</table>
  			<div id="dataSetDiv">
	  		<table>
	  			<tr>
	  				<td>dataSet_seriesName</td><td><input type="text" name="MSStackedColumn2DLineDY_dataSet_seriesName"></td>
	  			</tr>
	  			<tr>
					<td>dataSetSql</td><td><textarea name="MSStackedColumn2DLineDY_dataSetsSql" type="text" style="ime-mode:Disabled" cols="60" rows="10"></textarea></td>
	  			</tr>
	  		</table>
	  	</div>
  		<div id="dataSetsDiv">
  		</div>
  		<input type="button" value="添加dataset" onclick="addSet(dataSetsDiv,dataSetDiv)">
  		<div id="lineSetDiv">
	  		<table>
	  			<tr>
	  				<td>lineSet_seriesName</td><td><input type="text" name="MSStackedColumn2DLineDY_lineSet_seriesName"></td>
	  			</tr>
	  			<tr>
  					<td>lineSetSql</td><td><textarea name="MSStackedColumn2DLineDY_lineSetsSql" type="text" style="ime-mode:Disabled" cols="60" rows="10"></textarea></td>
  				</tr>
	  		</table>
  		</div>
  		<div id="lineSetsDiv">
  		</div>
  		<input type="button" value="添加lineSet" onclick="addSet(lineSetsDiv,lineSetDiv)">
  		</div>
  		
  		<input type="button" value="提交" id="sub"/></br>
  		<a href="http://user.qzone.qq.com/470614197" target="_blank"><img src="http://r.qzone.qq.com/cgi-bin/cgi_get_user_pic?openid=0000000000000000000000000ABD9B35&pic=1.jpg&key=5b67fdceb97b590fbcaf6e7601cc5eba"></a>
  	</form>
  </body>
</html>
