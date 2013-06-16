<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

			<title>单位设备统计</title>

			<meta http-equiv="pragma" content="no-cache">
				<meta http-equiv="cache-control" content="no-cache">
					<meta http-equiv="expires" content="0">
						<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
							<meta http-equiv="description" content="This is my page">
								<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
								<link rel="stylesheet" type="text/css"
									href="${basePath}css/public.css" />

								<script type="text/javascript"
									src="${basePath }js/orderTable.js">
</script>
								<script type="text/javascript"
									src="${basePath }js/FusionCharts.js">
</script>
								<script language="JavaScript"
									src="${basePath }resource/My97DatePicker/WdatePicker.js"
									type="text/javascript" defer="defer">
</script>
								<script>
function init() {
	var chart = new FusionCharts("${basePath }resource/flashChart/Pie2D.swf",
			"ChartId", "500", "350", "0", "0");
	if ("${xmlStr}".length == 0)
		chart.setDataXML("<chart palette='4'></chart>");
	else
		chart.setDataXML("${xmlStr}");
	chart.render("chartdiv");
	alert("${xmlStr}");
}
</script>
	</head>

	<body onload="init()" class="mainBody">
		<form method="post"
			action="${basePath }/module/runAnalysis/countDevice.do">
			<table width="100%">
				<tr>
					<td align="center" valign="top"
						style="background: #e9f1f7; border: #168101 1px solid; padding: 5px;">
						<table width="100%" border="1" cellspacing="0" cellpadding="0"
							bgcolor="#FFFFFF">
							<tr>
								<td style="padding: 5px;">
									<table width="100%" border="0" bordercolor="#CCCCCC"
										cellspacing="0" cellpadding="0"
										style="border-collapse: collapse;">
										<tr style="font-size: 13px;">
											<td align="left" style="padding: 5px;">
												<b>&nbsp;电压等级</b>
												<select name="dydjSelect" class="selfont">
													<option value="all">
														全部
													</option>
													${dydjOptions }
												</select>
												<b>&nbsp;设备类型</b>
												<select name="sblxSelect" class="selfont">
													<option value="all">
														全部
													</option>
													${sblxOptions }
												</select>
												<b>&nbsp;&nbsp;投运日期</b> &nbsp;从
												<input name="startdate"
													style="height: 18px; width: 100px; padding-bottom: 3px; font-size: 12px;"
													id="starttime" value="${starttime }" class="Wdate"
													type="text"
													onclick="WdatePicker({el:$dp.$('starttime'),maxDate:'%y-%M-%d',readOnly:true,isShowClear:false})"
													src="${basePath }resource/My97DatePicker/skin/datePicker.gif"
													width="16" height="22" align="middle" />
												到
												<input name="enddate"
													style="height: 18px; width: 100px; padding-bottom: 3px; font-size: 12px;"
													id="endtime" value="${endtime }" class="Wdate" type="text"
													onclick="WdatePicker({el:$dp.$('endtime'),maxDate:'%y-%M-%d',readOnly:true,isShowClear:false})"
													src="${basePath }resource/My97DatePicker/skin/datePicker.gif"
													width="16" height="22" align="middle" />
												<input type="submit" value="查询" class="btsc2" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						<table width="100%" class="subTitleTable" border="1"
							bordercolor="#168101" cellspacing="0" cellpadding="0"
							bgcolor="#FFFFFF"
							style="margin-top: 5px; border-collapse: collapse;">
							<tr>
								<th colspan="2">
									单位设备统计
								</th>
							</tr>
						</table>
						<table width="100%" border="0" bordercolor="#168101"
							cellspacing="0" cellpadding="0" bgcolor="#FFFFFF"
							style="margin-top: 1px; border-collapse: collapse;">
							<tr>
								<table width="100%" height="350">
									<tr align="center">
										
										<td width="50%">
											<table id="tblSort" class="detailInfoTable" border="1"
												bordercolor="#168101" cellspacing="0" cellpadding="0">
												<thead>
													<tr>
														<th onclick="sortTable('tblSort', 0 )"
															style="cursor: pointer">
															地区局
														</th>
														<th onclick="sortTable('tblSort', 1 , 'int')"
															style="cursor: pointer">
															500kV
														</th>
														<th onclick="sortTable('tblSort', 2 , 'int')"
															style="cursor: pointer">
															220kV
														</th>
														<th onclick="sortTable('tblSort', 3 , 'int')"
															style="cursor: pointer">
															110kV
														</th>
														<th onclick="sortTable('tblSort', 4 , 'int')"
															style="cursor: pointer">
															35kV
														</th>
														<th onclick="sortTable('tblSort', 5 , 'int')"
															style="cursor: pointer">
															10kV
														</th>
														<th onclick="sortTable('tblSort', 6 , 'int')"
															style="cursor: pointer">
															6kV
														</th>
														<th onclick="sortTable('tblSort', 7 , 'int')"
															style="cursor: pointer">
															合计
														</th>
													</tr>
												</thead>
												${tableData }
											</table>
										</td>
										<td width="50%">
											<div id="chartdiv">
												FusionCharts1
											</div>
										</td>
									</tr>
								</table>
							</tr>



						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
