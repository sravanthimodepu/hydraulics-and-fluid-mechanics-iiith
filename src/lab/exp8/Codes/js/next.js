//script for next button
var count=1;

function next(argument) {
	//clearTimeout(cl);
	//counter will increase for every onclick
	count+=1;
	//alert();
	switch (parseInt(count)) {
		case 7: case 13:
			experiment();break;
  		case 2: case 8: case 14:
  			vernierWithScale();break;
    	case 3: case 9: case 15:
    		drawBaffel(); break;
    	case 4:
    		 weirV();break;
    	case 5: case 11: case 17:
    		displayWater();break;
    	case 6: case 12: case 18:
    		observeReadings();break;
		case 10:
			weirR();break;
		case 16:
			weirE();break;
		default:
			complete();

	}	
}

function displayWater(argument) {

	document.getElementById("note").innerHTML = "Allow water to flow from pump";

	var c = document.getElementById("myCanvas");
	var cyl =c.getContext("2d");
	cyl.save();
	cyl.beginPath();
	drawCylinder(cyl ,445,240,90,63);//water level in sump 
	cyl.fillStyle="#1ca3ec";
	cyl.fill();

	cyl.beginPath();
	cyl.rect(445,245,90,52); //to display water in cylinder
	cyl.fillStyle= "#1ca3ec";
	cyl.fill();
	cyl.restore();


	var rctx = c.getContext("2d");
	rctx.save();
	rctx.rotate(4* Math.PI / 180);
	rctx.beginPath();
	rctx.rect(35,90, 325, 60);//water in cantainer
	rctx.fillStyle = "#1ca3ec";
	rctx.fill();
	rctx.restore();
	
	if((count==5)||(count==11)){
		var w=c.getContext("2d");
		w.save();
	    w.beginPath();
	    w.strokeStyle = "#1ca3ec";
	    w.lineWidth = 15;
    	w.bezierCurveTo(392, 120, 480, 200, 470,300);
    	//w.bezierCurveTo(414, 140, 500, 225, 480,300);
    	w.stroke();
    	w.restore();
    	w.save();
	    w.beginPath();
	    w.strokeStyle = "#1ca3ec";
	    w.lineWidth = 15;
    	w.bezierCurveTo(410, 140, 500, 225, 480,300);
    	w.stroke();
    	w.restore();
	}
	if(count==17){
		var w=c.getContext("2d");
		w.save();
	    w.beginPath();
	    w.strokeStyle = "#1ca3ec";
	    w.lineWidth = 15;
    	w.bezierCurveTo(400, 120, 480, 200, 470,300);
    	w.stroke();
    	w.restore();
    	w.save();
	    w.beginPath();
	    w.strokeStyle = "#1ca3ec";
	    w.lineWidth = 15;
    	w.bezierCurveTo(418,140, 500, 225,480,300);
    	w.stroke();
    	w.restore();

	}

}


function complete(argument) {

	alert("experiment completed");
	document.getElementById("next").disabled = true;
	document.getElementById("note").innerHTML = "use restart button to observe again";
	document.getElementById("stop").disabled = true;



}