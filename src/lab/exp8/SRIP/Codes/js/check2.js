//script file to display graph on clickcheckbox
function checkbox2(){
  //alert(outputValues.qv);
  //alert(arr[0][0]);
  	var checkBox = document.getElementById("show_graph");

  	
if (checkBox.checked == true){
	var ch = document.getElementById("chartContainer");
	ch.style.display = "block";

	var chart = new CanvasJS.Chart("chartContainer", {
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
	else{
		//alert("asad");
		var ch = document.getElementById("chartContainer");
		ch.style.display = "none";
		//alert("hgjg");
	}

}