
(function($){
	$.setPie2DChart=function(path,divname, width, height, chartXML){
		var chart = new FusionCharts(path+"flashChart/Pie2D.swf", "ChartId",
			width, height, "0", "0");
		//chart.setDataURL("${basePath }module/mis/qxzs/Line2D.xml");
		chart.setDataXML(chartXML);
		chart.render(divname);
	}
})(jQuery);


(function($){
	$.setBar2DChart=function(path,divname, width, height, chartXML){
		var chart = new FusionCharts(path+"flashChart/Bar2D.swf", "ChartId",
			width, height, "0", "0");
		chart.setDataXML(chartXML);
		chart.render(divname);
	}
})(jQuery);


(function($){
	$.setCombineChart=function(path,divname, width, height, chartXML){
		var chart = new FusionCharts(
			path+"flashChart/MSStackedColumn2DLineDY.swf", "ChartId",
			width, height, "0", "0");
		chart.setDataXML(chartXML);
		chart.render(divname);
	}
})(jQuery);


(function($){
	$.setChart=function(path,divName,width,height,chartXML,chartId){
		var chart = getChartFromId(chartId);
		if(chart == null) 
		{
			chart = new FusionCharts(path, chartId, width, height);
			chart.setDataXML(chartXML);
			chart.render(divName);
			
		} else {
			chart.setDataXML(chartXML);
		}
	}
})(jQuery);


