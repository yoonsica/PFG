<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>ICO配置平台</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<title>ICO平台</title>
		<link rel="stylesheet" type="text/css"
			href="${basePath }js/ext/resources/css/ext-all.css" />
		<script type="text/javascript" src="${basePath }js/ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="${basePath }js/ext/ext-all.js"></script>
		<script type="text/javascript" src="${basePath }js/ext/ext-lang-zh_CN.js"></script>
		<style type="text/css">
html,body {
	font: normal 12px verdana;
	margin: 0;
	padding: 0;
	border: 0 none;
	overflow: hidden;
	height: 100%;
}

.empty .x-panel-body {
	padding-top: 0;
	text-align: center;
	font-style: italic;
	color: gray;
	font-size: 11px;
}

.x-btn button {
	font-size: 14px;
}

.x-panel-header {
	font-size: 14px;
}
</style>
<script type="text/javascript">
	Ext.onReady( function() {
		
		//Ext.Msg.alert('ext','welcome you!');
		var addPanel = function(btn,event) {
			var n;
			n = tabPanel.getComponent(btn.id);
			if(n) {
				tabPanel.setActiveTab(n);
				return;
			}
			n = tabPanel.add( {
				id : btn.id,
				title : btn.text,
				html : '<iframe width=100% height=100% src=' + btn.id + ' />',
				//autoLoad : '',
				closable : 'true'
			});
			tabPanel.setActiveTab(n);
		}

		var item1 = new Ext.Panel( {
			title : 'Page管理',
			//html : '&lt;empty panel&gt;',
			cls : 'empty',
			items : [ 
				new Ext.Button({
					id : 'page!allPages',
					text : 'Page列表',
					width : '100%',
					listeners : {
						click : addPanel
					}

				}),

				new Ext.Button({
					id : 'addNewPage.jsp',
					text : '添加Page',
					width : '100%',
					listeners : {
						click : addPanel
					}

				})

				]
		});

		var item2 = new Ext.Panel( {
			title : 'Condition管理',
			cls : 'empty',
			items : [ 
						new Ext.Button({
							id : 'condition!allConditions',
							text : 'Condition列表',
							width : '100%',
							listeners : {
								click : addPanel
							}

						}),

						new Ext.Button({
							id : 'condition!toConditionAdd',
							text : '添加condition',
							width : '100%',
							listeners : {
								click : addPanel
							}
						})
						]
		});

		var item3 = new Ext.Panel( {
			title : 'FusionChart管理',
			cls : 'empty',
			items : [ 
						new Ext.Button({
							id : 'chart!allCharts',
							text : 'chart列表',
							width : '100%',
							listeners : {
								click : addPanel
							}

						}),
						new Ext.Button({
							id : 'addNewChart.jsp',
							text : '添加chart',
							width : '100%',
							listeners : {
								click : addPanel
							}
						})
						]
		});

		var item4 = new Ext.Panel( {
			title : 'Table管理',
			cls : 'empty',
			items : [ 
						new Ext.Button({
							id : 'table!allTables',
							text : 'Table列表',
							width : '100%',
							listeners : {
								click : addPanel
							}

						}),

						new Ext.Button({
							id : 'addNewTable.jsp',
							text : '添加表格',
							width : '100%',
							listeners : {
								click : addPanel
							}
						})
						]
		});


		var accordion = new Ext.Panel( {
			region : 'west',
			margins : '5 0 5 5',
			split : true,
			width : 210,
			layout : 'accordion',
			items : [ item1, item2, item3, item4]
		});

		var tabPanel = new Ext.TabPanel( {
			region : 'center',
			enableTabScroll : true,
			deferredRender : false,
			activeTab : 0,
			items : [ {
				id : 'default',
				title : '欢迎界面',
				//html : 'aaaaaa'
				autoLoad : 'default.jsp'
			} ]
		});

		var viewport = new Ext.Viewport( {
			layout : 'border',
			items : [ accordion, tabPanel ]
		});

	});
	
</script>
  </head>
  <body>
   
  </body>
</html>
