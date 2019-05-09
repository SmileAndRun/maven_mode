var count;
$(function(){

	function getCount(){
    	var url = "/grapha/getPageView";
		var data = {
		};
		var s_function = function(result){
			count = result.activeSessions;
		}
		var e_function = function(){
			layer.msg("The server is error!!!");
		}
		$.commonAjax(url,data,s_function,e_function);
	}
	var chart = {
		      type: 'spline',
		      animation: Highcharts.svg, // don't animate in IE < IE 10.
		      marginRight: 10,
		      events: {
		         load: function () {
		            var series = this.series[0];
		            //getCount();
		            setInterval(function () {
		               getCount();
		               var x = (new Date()).getTime(), // current time
		               y = count;
		               series.addPoint([x, y], true, true);
		            }, 1000);
		         }
		      }
		   };
		   var title = {
		      text: $(".CurrentPpageView").val()   
		   };   
		   var xAxis = {
		      type: 'datetime',
		      tickPixelInterval: 150
		   };
		   var yAxis = {
		      title: {
		         text: $(".pageView").val()
		      },
		      plotLines: [{
		         value: 0,
		         width: 1,
		         color: '#808080'
		      }],
		      tickInterval: 1
		   };
		   var tooltip = {
		      formatter: function () {
		      return '<b>' + this.series.name + '</b><br/>' +
		         Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
		         Highcharts.numberFormat(this.y, 0);
		      }
		   };
		   var plotOptions = {
		      area: {
		         pointStart: 1940,
		         marker: {
		            enabled: false,
		            symbol: 'circle',
		            radius: 2,
		            states: {
		               hover: {
		                 enabled: true
		               }
		            }
		         }
		      }
		   };
		   var legend = {
		      enabled: false
		   };
		   var exporting = {
		      enabled: false
		   };
		   var series= [{
			      name: $(".pageView").val(),
			      data: (function () {
			    	 var initData = $(".initData").val();
			    	 var counts = initData.split(",");
			    	 console.log(counts);
			         var data = [],time = (new Date()).getTime(),i;
			         for (i = 0; i <= 9; i += 1) {
	     				data.push({
				               x: time + i * 1000,
				               y: parseInt(counts[i])
				            });
			           
			         }
			         return data;
			      }())    
			   }];  
		   
		   var credits = {//去掉默认的highcharts.com
               enabled: false
           }
		   var json = {};   
		   json.chart = chart; 
		   json.title = title;     
		   json.tooltip = tooltip;
		   json.xAxis = xAxis;
		   json.yAxis = yAxis; 
		   json.legend = legend;  
		   json.exporting = exporting;   
		   json.series = series;
		   json.plotOptions = plotOptions;
		   json.credits = credits;
		   
		   Highcharts.setOptions({
		      global: {
		         useUTC: false
		      }
		   });
		   $('.userNums').highcharts(json);
		  
		   
})