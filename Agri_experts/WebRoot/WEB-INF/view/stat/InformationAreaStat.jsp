<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>咨询问题领域数据统计</title>
</head>
<body style="height: 100%; margin: 0">
       <div id="container" style="height: 100%"></div>
       <script type="text/javascript" src="${ctx }/resources/echarts/js/echarts.min.js"></script>
       <script type="text/javascript" src="${ctx }/resources/echarts/js/jquery-3.1.1.min.js"></script>
       <script type="text/javascript">
		$.get("${ctx}/stat/loadInformationAreaStatJSON.action",function(data){
			var dom = document.getElementById("container");
			var myChart = echarts.init(dom);
			var app = {};
			option = null;
			option = {
				    tooltip: {
				        trigger: 'item'
				    },
				    legend: {
				        top: '5%',
				        left: 'center'
				    },
				    series: [
				        {
				            name: data.name, 
				            type: 'pie',
				            radius: ['40%', '70%'],
				            avoidLabelOverlap: false,
				            itemStyle: {
				                borderRadius: 10,
				                borderColor: '#fff',
				                borderWidth: 2
				            },
				            label: {
				                show: false,
				                position: 'center'
				            },
				            emphasis: {
				                label: {
				                    show: true,
				                    fontSize: '40',
				                    fontWeight: 'bold'
				                }
				            },
				            labelLine: {
				                show: false
				            },
				            data:data
				        }
				    ]
				};
			if (option && typeof option === "object") {
			    myChart.setOption(option, true);
			}
		})
       
       </script>
   </body>
</html>