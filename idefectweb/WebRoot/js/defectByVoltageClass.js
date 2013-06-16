(function($){
	$.createTableBodyHTML = function(data,voltageClasses,paraStr,id,titles,basePath){
		//data为行数据object【】数组，voltageClasses为要显示的电压等级对象数组，parastr为页面获取的参数，id为tableBody的id，titles为每行开头的第一个td
		var tableBody = "<tbody id='"+id+"'>";
		for(var i=0;i<data.length-1;i++)
		{
			tableBody += "<tr><td>"+titles[i]+"</td>";
			for(var j=0;j<data[i].length-1;j++){
				var para = basePath+"numberHref.action?"+paraStr+"&defect_voltageClass="+voltageClasses[j].code+"&totalNum="+data[i][j];
				tableBody +="<td><a href=\"javascript:void(0)\" onclick=\"window.open('"+para+"','abc','scrollbars=1,resizable=0,status=1,left=200,top=100,width=1400,height=600')\">"+data[i][j]+"</a></td>";
			}
			tableBody +="<td>"+data[i][j]+"</td></tr>";
		}
		tableBody += "<tr><td>"+titles[data.length-1]+"</td>";
		for(var k=0;k<data[data.length-1].length;k++){
			tableBody += "<td>"+data[data.length-1][k]+"</td>";
		}
		tableBody +="</tr></tbody>";
		$("#tableBody").replaceWith(tableBody);
	}
})(jQuery);
