// Apply the grid theme
Highcharts.setOptions({                 
   colors: ['#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4'],
   chart: {
      backgroundColor: {
         linearGradient: [0, 0, 500, 500],
         stops: [
            [0, 'rgb(255, 255, 255)'],
            [1, 'rgb(240, 240, 255)']
         ]
      },
      borderWidth: 2,
      plotBackgroundColor: 'rgba(255, 255, 255, .9)',
      plotShadow: true,
      plotBorderWidth: 1
   },
   title: {
      style: { 
         color: '#3E576F',
         font: 'bold 16px Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif'
      }
   },
   subtitle: {
      style: { 
         color: '#6D869F',
         font: 'bold 12px Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif'
      }
   },
   xAxis: {
      gridLineWidth: 1,
      lineColor: '#C0D0E0',
      tickColor: '#C0D0E0',
      labels: {
         style: {
            color: '#666',
            fontWeight: 'bold'
         }
      },
      title: {
         style: {
            color: '#6D869F',
            font: 'bold 12px Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif'
         }            
      }
   },
   yAxis: {
      alternateGridColor: null,
      minorTickInterval: 'auto',
      lineColor: '#000',
      lineWidth: 1,
      tickWidth: 1,
      tickColor: '#000',
      labels: {
         style: {
            color: '#666',
            fontWeight: 'bold'
         }
      },
      title: {
         style: {
            color: '#6D869F',
            font: 'bold 12px Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif'
         }            
      }
   },
   legend: {
      itemStyle: {
         color: '#3E576F'
      },
      itemHoverStyle: {
         color: 'black'
      },
      itemHiddenStyle: {
         color: 'silver'
      }
   },
   credits: {
      style: {
         right: '10px'
      }
   },
   labels: {
      style: {
         color: '#3E576F'
      }
   }
});