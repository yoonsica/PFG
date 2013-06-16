<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath",basePath);

System.out.println(request.getAttribute("tableData"));
System.out.println(request.getAttribute("chartNum"));
int cn = Integer.parseInt((String)request.getAttribute("chartNum"));
String sId = request.getSession().getId();
String fileNames = "";
for(int i = 0; i < cn; i ++)
{
  	String fileName = sId+i;
  	fileNames += fileName+",";
}
fileNames = fileNames.substring(0,fileNames.length()-1);
request.setAttribute("fileNames",fileNames);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>报表导出预览</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css"
	href="${basePath }css/export.css">
	<script type="text/javascript">
		function ExportToWord(){//导出word
			ExcelSheet = new ActiveXObject('Word.Application');
			ExcelSheet.Application.Visible = true;
			var mydoc=ExcelSheet.Documents.Add('',0,0);
			var sel=document.body.createTextRange();
			myRange =mydoc.Range(0,1);
			myRange =mydoc.Range(myRange.End-1,myRange.End);//设定起始点-标题表
			sel.moveToElementText(titleTable);              //选中第一张表
			sel.select();
			document.execCommand('Copy');
			sel.moveEnd('character');
			myRange.Paste();
			
			myRange =mydoc.Range(myRange.End-1,myRange.End);//设定起始点-标题表
			sel.moveToElementText(dataTable);              //选中第二张表
			sel.select();
			document.execCommand('Copy');
			sel.moveEnd('character');
			myRange.Paste();
			
			myRange =mydoc.Range(myRange.End-1,myRange.End);//设定起始点-标题表
			sel.moveToElementText(imageTable);              //选中第三张表
			sel.select();
			document.execCommand('Copy');
			sel.moveEnd('character');
			myRange.Paste();
			ExcelSheet.ActiveWindow.View.TableGridlines = false;
		}
		var req;
		if(window.XMLHttpRequest){
				req = new XMLHttpRequest();
			}else if(window.ActiveXObject){
				req = new ActiveXObject("Microsoft.XMLHttp");
			}
		function deleteFlashImages()
		{
			var url = "${basePath}deleteFlashImages.action?fileNames=${fileNames}";
			//适应不同浏览器
			req.open("POST",url,true);
			req.onreadystatechange = callback;
			req.send(null);
		}
		function callback(){
			if(req.readyState == 4){
				if(req.status == 200){
					//var msg = req.responseXML.getElementsByTagName("msg")[0];
					alert(req.responseText);
				}
			}
		}
	</script>
  </head>
  <body  class="mainBody" onunload="deleteFlashImages()"> 
  	<form>
  		<table width = "660" id = "exportTable" height="450">
  			<tr>
  				<td align="left" width = 660 valign="top" >
  					<table id = "titleTable" class="titleTable" width="660" >
  						<tr>
  							<th>${titleName}</th>
  						</tr>
  					</table>
  					<%=request.getAttribute("tableData") %>
  					<table id = "imageTable" border="0"  bordercolor="#168101" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style="margin-top:1px;border-collapse:collapse;">
  						<tr>
  						<% 
  						int chartNum =Integer.parseInt((String)request.getAttribute("chartNum"));
  						String sessionId = request.getSession().getId();
  						if(chartNum == 1)  //长度为1则不对图片进行缩小处理
  						{
  							System.out.println(request.getAttribute("basePath")+"flashImages/"+sessionId+"0.jpg");
  							%>
  							<td><img  width = "660" height = "320" src = "${basePath}flashImages/<%=sessionId+"0"%>.jpg"/></td><%
  						}
  						else
  						{
  							for(int i = 0; i < chartNum; i ++)
  							{
  								String fileName = sessionId+i;
  								System.out.println(request.getAttribute("basePath")+"flashImages/"+fileName+".jpg");
  								%>
  								<td><img  width = "<%=660/chartNum%>" height = "<%=480/chartNum%>" src = "${basePath}flashImages/<%=fileName%>.jpg"/></td>
  								<%
  							}
  						}
  						%>
  					</table>
  					<table align = "center">
  						<tr>
  							<td>
  								<input type = "button" value = "导出到WORD" onClick = "ExportToWord()"/>
  							</td>
  						</tr>
  					</table>
  				</td>
  			</tr>
  		</table>
  	
  	</form>
  </body>
</html>
