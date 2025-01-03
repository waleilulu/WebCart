<%@ page import="model.dto.UserDto"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!-- 配合插入在pom.xml的JSTL -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>訂單統計資料</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
    	  var data = google.visualization.arrayToDataTable([
	          ['product name', 'total'],
	          
	          <c:forEach var="pss" items="${productSalesSummaries}">
	          
	          	['${pss.productName}', ${pss.total}],
	          	
	          </c:forEach>
	          	
	        ]);

        var options = {
          title: 'Product Sales Summaries',
          is3D: true, //使用true 或 false來開啟關閉3D
        };

        var chart1 = new google.visualization.PieChart(document.getElementById('piechart'));
		//var chart2 = new google.visualization.ColumnChart(document.getElementById('piechart'));
		var chart3 = new google.visualization.BarChart(document.getElementById('barchart'));
		//var chart4 = new google.visualization.LineChart(document.getElementById('piechart'));
		
        chart1.draw(data, options);
        chart3.draw(data, options);
      }
    </script>
	
</head>
<body>
	<%@ include file="/WEB-INF/view/menu.jspf"%>
	<div class="pure-form" style="padding: 15px">
		<fieldset>
			<legend>訂單統計資料</legend>
<%-- 			${ productSalesSummaries } --%>
				<table>
					<td valign="top">
						<div id="piechart" style="width: 800px; height: 500px;"></div>
					</td valign>
					<td>
						<div id="barchart" style="width: 800px; height: 500px;"></div>
					</td>
				</table>
		</fieldset>
	</div>
</body>
</html>