var canvas = document.getElementById("myCanvas");
		var ctx = canvas.getContext("2d");
		var x_water = 220;  // Initial Position of water from left
		var y_water = 300; //Initial Position of water from right
		var y_water_2 = 310;



			function animations()	// Animation function to perform desired Animation
			{
		
		id=window.requestAnimationFrame(animations);

		var i = 0
		if(x_water > 459 && x_water <480)	
		{
			
			ctx.fillStyle = "#4679D5";
			ctx.fillRect(x_water, 356, 10, 23 + i);
			i = i + 5;
			x_water = x_water + 1.5;
		}
		else if(x_water<800)
		{
			if(x_water> 420 && x_water < 810 && y_water>215)
			{
				ctx.fillStyle = "#4679D5";
				ctx.fillRect(420, y_water,6, -2);
				y_water = y_water - 0.2;
			}
			if(x_water>520 && x_water < 810 && y_water_2 > 250)
			{
				ctx.fillStyle = "#4679D5";
				ctx.fillRect(520, y_water_2 -10 ,6, -1);
				y_water_2 = y_water_2 - 0.1;
			ctx.fillStyle = 'red';
			ctx.fillRect(485, 355, 10 , 1);
			ctx.font = "5px";
			ctx.fillStyle = 'red';
			ctx.fillStyle = 'red';
			ctx.fillRect(485, 382, 10 , 1);
			ctx.font = "5px";
			ctx.fillRect(490, 355, 1 , 27);
			ctx.font = "5px";
			ctx.fillStyle = "red";
			ctx.fillText("D b", 500, 370);


			}
			ctx.fillStyle = "#4679D5";
			ctx.fillRect(x_water, 301, 10, 124);
			
			for(var i = 220; i<470; i = i + 20)
			for(var j = 301; j<425; j = j + 20)
			{
				ctx.fillStyle = "white";
				ctx.fillRect(i, j, 5,1);	// For white lines in Water
			}
			if(x_water > 460)
			{

			for(var i = 480; i<810; i = i + 20)
			for(var j = 301; j<422; j = j + 20)
			{
				ctx.beginPath();

				ctx.arc(i, j + 3, 2, -0.5* Math.PI, -1.5* Math.PI);
				ctx.strokeStyle = "white";					// For arcs in water
				ctx.stroke();
			}

			}
			
			x_water = x_water + 1;

		}
		else {
			cancelAnimationFrame(id);	// Ends the Animation
		}


	}