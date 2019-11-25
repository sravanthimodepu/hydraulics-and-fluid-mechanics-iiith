
		var canvas = document.getElementById("myCanvas");
		var ctx = canvas.getContext("2d");
        ctx.font = "20px Bold"
        ctx.fillStyle = "Blue";
        var text = ctx.measureText("Orifice");
        ctx.fillText("Orifice", 100,30);
        ctx.strokeStyle = "#000000";
        ctx.strokeRect(100, 33, text.width,0.3);
        canvas.style.left = "170px";
        canvas.style.position = "absolute";

        function fill_text(font_size, color, text, left, top) // To fill Text in the Canvas (Labeling Part)
        {
        	ctx.font = font_size;
			ctx.fillStyle = color;
			ctx.fillText(text, left, top);
        }
        

        fill_text("10px bold", 'red', "Db", 495, 372);
        fill_text("20px Bold", 'blue', "Upstream Pressure Tap", 200, 200);
        fill_text("20px Bold", 'blue',"(Pa)", 240, 230);
		fill_text("20px bold", 'blue', "Downstream Pressure Tap", 540, 200);
		fill_text("20px Bold", 'blue',"(Pb)", 600, 230);
		fill_text("18px bold", 'blue', "Flow direction", 40, 350);
		fill_text("14px bold", 'blue', "Da", 180, 370);

		

    	function triangle(x, y, x1, y1, x2, y2)		// To add triangles
    	{
    		   	ctx.beginPath();
    			ctx.moveTo(x + 10, y);
    			ctx.lineTo(x1 + 10, y1);
    			ctx.lineTo(x2 + 10, y2);
    			ctx.fill();
		    	ctx.closePath();
		    	ctx.stroke();

    	}
    	triangle(410, 230, 400, 220, 400, 240);
    	triangle(160, 360, 150, 350, 150, 370);
    	triangle(520, 271, 530, 261, 530, 281);
    	triangle(180, 302, 175, 310, 185, 310);
    	triangle(180, 420, 175, 410, 185, 410);

    	 
function draw_line(startX, startY, newX, newY){	// To draw Lines
	ctx.beginPath();
ctx.moveTo(startX + 10, startY);
ctx.lineTo(newX + 10, newY);
ctx.stroke();
}

//Co-ordinates of various lines
	draw_line(195, 300, 410, 300);
	draw_line(410, 300, 410, 200);
	draw_line(416, 200, 416, 300);
	draw_line(416, 300, 510, 300);
	draw_line(510, 300, 510, 200);
	draw_line(516, 200, 516, 300);
	draw_line(516, 300, 810, 300);
	draw_line(195, 425, 810, 425);
	draw_line(460, 300, 460, 355);
	draw_line(470, 300, 470, 355);
	draw_line(460, 355, 470, 355);
	draw_line(460, 425, 460, 380);
	draw_line(470, 425, 470, 380);
	draw_line(460, 380, 470, 380);

	function lines(startX, startY, newX, newY) // Draws Lines
	{
			ctx.beginPath();
			ctx.strokeStyle = "black"
ctx.moveTo(startX + 10, startY);
ctx.lineTo(newX + 10, newY);
ctx.stroke();
	}

	lines(170, 300, 190, 300);
	lines(170, 420, 190, 420);


function line_filling(color, iX , iY, nX, nY) // Create , position and fill lines with given color
{
			ctx.fillStyle = color;
			ctx.fillRect(iX, iY, nX , nY);

}

			line_filling("#B4B4C6", 470, 300, 10, 55);
			line_filling("#B4B4C6", 470, 380, 10, 45);
			line_filling("blue", 310, 200, 2, 30);
			line_filling("blue", 310, 230,100, 2);
			line_filling("blue", 60, 360, 100, 2);
			line_filling("blue", 580, 202,2, 70);
			line_filling("blue", 530, 271,52, 2);
			line_filling("blue", 189, 308, 2, 50);
			line_filling("blue", 189, 373, 2, 45);
			line_filling('red', 485, 355, 10 , 1);
			line_filling('red', 485, 382, 10 , 1);
			line_filling('red', 490, 355, 1 , 27);

			var x_water = 200;