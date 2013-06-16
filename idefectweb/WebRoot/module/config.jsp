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
    
    <title>My JSP 'config.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${basePath }css/config.css">
	<script type="text/javascript">
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
		
		$(function(){
			//设置日期控件
			$("#sub").click(function(){
				query();
			});	
			function query(){
				var paraStr=$("#configForm").serialize();
				alert(paraStr);
				/* var dydj = document.getElementById("dydj");
				alert(dydj);
				var defect_find_date_start = $("#defect_find_date_start").datebox('getValue');
				var defect_find_date_end = $("#defect_find_date_end").datebox('getValue');
				//paraStr ="deviceTypeSelect="+deviceTypeSelect+"&defect_find_date_start="+defect_find_date_start+"&defect_find_date_end="+defect_find_date_end; */
				$.ajax({
					type:"POST",
					url:"${basePath }config.action",
					data:paraStr,
					success:function(data){
					}
				});
			}
		})
	</script>
  </head>
  
  <body>
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
  </body>
</html>
