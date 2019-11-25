function viewchart(){
	
	 var arr = JSON.parse(localStorage["array"]); 

	var ch = document.getElementById("graph");
	ch.style.display = "block";

	var chart = new CanvasJS.Chart("graph", {
		animationEnabled: true,
		title:{
			text: "flow of discharge(q) vs Height"
		},
		axisX: {
			title: "Height",
			xValueFormatString: "Height ##.##",
		},
		axisY: {
			title: "flow of discharge",
			yValueFormatString: "q ##.##",
			includeZero: true,
			scaleBreaks: {
				autoCalculate: true
			}	

		},
		data: [{
			type: "line",
			color: "#F08080",

			dataPoints: [
				
				{ x:parseFloat(arr[0][0]), y:parseFloat(arr[0][1]) },
				{ x:parseFloat(arr[1][0]), y:parseFloat(arr[1][1]) },
				{ x:parseFloat(arr[2][0]), y:parseFloat(arr[2][1]) }
			]
		}]
	});
	
	chart.render();
	
}