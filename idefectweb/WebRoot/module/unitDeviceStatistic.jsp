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

<title>单位设备统计分析</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="${basePath }css/unitDevice.css">
<link rel="stylesheet" type="text/css" href="${basePath }css/config.css">
<script type="text/javascript" src="${basePath }js/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="${basePath }jquery-easyui-1.3.1/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${basePath }jquery-easyui-1.3.1/themes/icon.css">
<script type="text/javascript" src="${basePath }js/FusionCharts.js"></script>
<script type="text/javascript" src="${basePath }jquery-easyui-1.3.1/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${basePath }jquery-easyui-1.3.1/jquery.easyui.min.js"></script>
<script>
		function resize(){
			$('#w').window('resize',{
				width: 600,
				height: 300
			});
		}
		function open1(){
			$('#w').window('open');
		}
		function close1(){
			$('#w').window('close');
		}
		function test(){
			$('#test').window('open');
		}
		//日期格式转换
		function myformatter(date){  
            var y = date.getFullYear();  
           var m = date.getMonth()+1;  
            var d = date.getDate();  
           return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);  
       }  
		function checkAll(name,obj) 
		{ 
			var code_Values = document.getElementsByName(name);
			var flag = true;//是否已经全选
			for(i = 0;i < code_Values.length;i++){ 
				if(code_Values[i].type == "checkbox") 
				{ 
					if(!code_Values[i].checked){
						flag = false;
						break;
					};
				} 
			}
			if(!flag){//如果不是全选
				for(i = 0;i < code_Values.length;i++){ 
					if(code_Values[i].type == "checkbox") 
					{ 
						code_Values[i].checked = true;
					} 
				} 
			}else{//如果已经全选，就全不选
				if(!obj.checked){
					for(i = 0;i < code_Values.length;i++){ 
						if(code_Values[i].type == "checkbox") 
						{ 
							code_Values[i].checked = false;
						} 
					}
				}
			}
		} 
		function check(obj,id) 
		{ 
			if(!obj.checked){
				document.getElementById(id).checked=false;
			}
		}
		function getTableHead(data){
			var tableHead="<thead id='tableHead'><tr><th>地区局</th>";
			for(var j=0;j<data.length;j++){
				tableHead+="<th>"+data[j]+"</th>";
			}
			tableHead+="</tr></thead>";
			$("#tableHead").replaceWith(tableHead);
		}
		function getTableBody(data){
			var tableBody = "<tbody id='tableBody'>";
			for(var i=0;i<data.length;i++)
			{
				tableBody +="<tr><td>"+data[i][0]+"</td>";
				for(var j=1;j<data[i].length;j++){
					//var para = basePath+"numberHref.action?"+paraStr+"&defect_voltageClass="+voltageClasses[j].code+"&totalNum="+data[i][j];
					var para = '#';
					tableBody +="<td><a href=\"javascript:void(0)\" onclick=\"window.open('"+para+"','abc','scrollbars=1,resizable=0,status=1,left=200,top=100,width=1400,height=600')\">"+data[i][j]+"</a></td>";
				}
				tableBody +="</tr>";
			}
			tableBody += "</tbody>";
			$("#tableBody").replaceWith(tableBody);
		}
		
		function getTableFoot(){
			var tableFoot = "<tfoot id='tableFoot'><tr><td>总计</td>";
            //获得列表对象
            var table = document.getElementById("dataTable");
            //计算每列的和
           	for(var j=1;j<table.rows[0].cells.length;j++){
           		var sumValue=0;	
	       		for(var i=1;i<table.rows.length-1;i++)
				{
					sumValue = sumValue +parseInt(table.rows[i].cells[j].innerText);
				}
				tableFoot+="<td>"+sumValue+"</td>";
           	}
            tableFoot+="</tr></tfoot>";
            $("#tableFoot").replaceWith(tableFoot);
            var cell=table.rows[0].insertCell(table.rows[0].cells.length);
            cell.innerHTML="<b>总计</b>";
            cell.style.background="#6baa6b";  
            for(var i=1;i<table.rows.length;i++)
			{	
				var sumValue=0;	
				for(var j=1;j<table.rows[0].cells.length-1;j++){
					sumValue = sumValue +parseInt(table.rows[i].cells[j].innerText);
				}
				table.rows[i].insertCell(table.rows[i].cells.length).innerText=sumValue;
			}
		}
		
		function generateChart(){
			var str =" <chart caption='按单位设备统计分析' baseFontSize='12' formatNumber='0' formatNumberScale='0' forceDecimals='2' enableSmartLabels='1' enableRotation='0' bgColor='99CCFF,FFFFFF' bgAlpha='40,100' bgRatio='0,100' bgAngle='360' showBorder='1' startingAngle='70'>";
			var table = document.getElementById("dataTable");
			for(var i=1;i<table.rows.length-1;i++)
			{	
				str +="<set label='"+table.rows[i].cells[0].innerText+"' value='"+table.rows[i].cells[table.rows[i].cells.length-1].innerText+"'/>";
			}
			str+="</chart>";
			var chart = new FusionCharts("${basePath }/flashChart/Pie2D.swf",
			"ChartId", "1200", "300", "0", "0");
			chart.setDataXML(str);
			chart.render("chartdiv");
		}
		
		$(function(){
			//设置日期控件
			$("#sub").click(function(){
				query();
			});	
			function query(){
				var paraStr=$("#configForm").serialize();
				$.ajax({
					type:"POST",
					url:"${basePath }config.action",
					data:paraStr,
					success:function(data){
						getTableBody(data.dataList);
						getTableHead(data.tableHeadList);
						getTableFoot();
						generateChart();
					}
				});
			}
			if(document.getElementById("tableBody").innerHTML==''){
				query();
			}
		})
	</script>

</head>

<body class="mainBody" onload="close1()">
	<center>
		<table class="titleTable">
			<tr>
				<th>单位设备统计<a href="javascript:void(0)" class="easyui-linkbutton" onclick="open1()">配置</a></th>
			</tr>
		</table>
			<table id="dataTable" class="commonTable" margin='auto'>
			<thead id="tableHead">
				<tr>
					<th>地区局</th>
					<s:iterator value="voltageClassList" var="voltageClass">
						<th><s:property value='#voltageClass.name' />
						</th>
					</s:iterator>
				</tr>
			</thead>
			<tbody id="tableBody"></tbody>
			<tfoot id="tableFoot"><tr></tr></tfoot>
			</table>
		<div id="chartdiv"></div>
		
		
		<div id="w" class="easyui-window" data-options="title:'配置窗口',iconCls:'icon-save'" style="width:350px;height:520px;padding:5px;">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',border:false" style="padding:10px;background:#fff;border:1px solid #ccc;">
				<form id="configForm">
			    	<div id="dydjDiv">
			    		<div id="dydjTitleDiv">
				    	电压等级
				    	</div>
				    	<div id="checkboxDiv">
				    		<input type="checkbox" id="dydjAll" onclick="checkAll('dydj',this)"/>全选
				    		<s:iterator value="voltageClassList" var="voltageClass">
								<input type="checkbox" name="dydj" checked="checked" value="${voltageClass.code }" onchange="check(this,'dydjAll')"/>${voltageClass.name }
							</s:iterator>
						</div>
			    	</div>
			    	<div id="sblxDiv">
				    	<div id="sblxTitleDiv">
				    	设备类型
				    	</div>
				    	<div id="checkboxDiv">
				    		<input type="checkbox" id="sblxAll" onclick="checkAll('sblx',this)"/>全选</br>
				    		<s:iterator value="deviceTypeList" var="deviceType">
								<input type="checkbox" name="sblx" checked="checked" value="${deviceType.code }" onchange="check(this,'sblxAll')"/>${deviceType.name }</br>
							</s:iterator>
				    	</div>
			    	</div>
			    	<div id="ssdsDiv">
			    		<div id="ssdsTitleDiv">
				    	所属地市
				    	</div>
				    	<div id="checkboxDiv">
				    		<input type="checkbox" id="ssdsAll" onclick="checkAll('ssds',this)"/>全选</br>
				    		<s:iterator value="departmentList" var="department">
								<input type="checkbox" name="ssds" checked="checked" value="${department.code }" onchange="check(this,'ssdsAll')"/>${department.name }</br>
							</s:iterator>
						</div>
			    	</div>
			    	<div id="tyrqDiv">
			    		<div id="tyrqTitleDiv">
				    	投运日期
				    	</div>
				    	<div id="dateBoxDiv">
			    		<input class="easyui-datebox" style="width:100px;"  data-options="formatter:myformatter" name="tyrq_start" id="tyrq_start" type="text" value="<s:property value='tyrq_start'/>" />
			    		到<input class="easyui-datebox" style="width:100px;" data-options="formatter:myformatter" name="tyrq_end" id="tyrq_end" type="text" value="${tyrq_end }" />
			    		</div>
			    	</div>
			    	<div data-options="region:'south',border:false" style="text-align:right;padding:5px 0;">
							<a id="sub" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" >Ok</a>
							<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:alert('cancel')">Cancel</a>
					</div>
			    </form>
			</div>
		</div>
	</div>
	</center>
</body>
</html>
