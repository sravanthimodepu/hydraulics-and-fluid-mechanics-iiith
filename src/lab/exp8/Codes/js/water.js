//script file for waterflow part of simulation

function drawcylwater(argument) {
  var c = document.getElementById("myCanvas");
  var w =c.getContext("2d");
  var cyl =c.getContext("2d");
  
  //Configuration
  var myRect = {"x":445,"y":245,"w":90,"h":58}  //rect definition
  timeInterval= 100;          //time between 2 draw
  thick = 3;             //thickness of a line (pixel)

  var cpt = 0;

  //loop will process fast but we make a delay on each draw with setTimeout which grow at each iteration
  for(var ind = thick; ind < myRect.h+thick ; ind += thick){
      setTimeout(function(ind){
          drawLittleRect(ind)
      },
      timeInterval*cpt, ind);
      cpt++
  }

  function drawLittleRect(ind){
      var tempY = myRect.y + myRect.h - ind;

      //Limit top of rect in order to get desired size
      if(tempY < myRect.y){
        tempY = myRect.y
      }
      w.save();
      w.fillStyle = " #1ca3ec"; //color of the rect
      w.fillRect(myRect.x, tempY,  myRect.w, thick);
      w.restore();   
     }
}


function drawFlowWater(argument) {
  var c = document.getElementById("myCanvas");
  var l =c.getContext("2d");
  animatePathDrawing(l,392, 120, 480, 200, 470,300,2000);
  setTimeout(x,500);
  function x(argument) {
    animatePathDrawing(l,414,140, 500, 225,480,300,1500);
  }
}


function drawFlowWaterE(argument) {
  var c = document.getElementById("myCanvas");
  var l =c.getContext("2d");
  animatePathDrawing(l,400, 120, 480, 200, 470,300,2000);
  setTimeout(x,500);
  function x(argument) {
    animatePathDrawing(l,418,140, 500, 225,480,300,1500);
  }
}


function animatePathDrawing(ctx, x0, y0, x1, y1, x2, y2, duration) {
  var start = null;
  var step = function animatePathDrawingStep(timestamp) {
    if (start === null)
      start = timestamp;
    var delta = timestamp - start,
            progress = Math.min(delta / duration, 1);
        
        // Clear canvas
        //ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);
        
        // Draw curve
        drawBezierSplit(ctx, x0, y0, x1, y1, x2, y2, 0, progress);
        
        if (progress < 1) {
            window.requestAnimationFrame(step);
        }
    };
    
    window.requestAnimationFrame(step);
}


/**
 * Draws a splitted bezier-curve
 * 
 * @param ctx       The canvas context to draw to
 * @param x0        The x-coord of the start point
 * @param y0        The y-coord of the start point
 * @param x1        The x-coord of the control point
 * @param y1        The y-coord of the control point
 * @param x2        The x-coord of the end point
 * @param y2        The y-coord of the end point
 * @param t0        The start ratio of the splitted bezier from 0.0 to 1.0
 * @param t1        The start ratio of the splitted bezier from 0.0 to 1.0
 */
function drawBezierSplit(ctx, x0, y0, x1, y1, x2, y2, t0, t1) {

      ctx.save();
      ctx.beginPath();
      ctx.strokeStyle = "#1ca3ec";
      ctx.lineWidth = 15;
      //i=i+1;
      if( 0.0 == t0 && t1 == 1.0 ) {
            ctx.moveTo( x0, y0 );
            ctx.quadraticCurveTo( x1, y1, x2, y2 );

      }
      else if( t0 != t1 ) {
        var t00 = t0 * t0,
            t01 = 1.0 - t0,
            t02 = t01 * t01,
            t03 = 2.0 * t0 * t01;
        
        var nx0 = t02 * x0 + t03 * x1 + t00 * x2,
            ny0 = t02 * y0 + t03 * y1 + t00 * y2;
        
        t00 = t1 * t1;
        t01 = 1.0 - t1;
        t02 = t01 * t01;
        t03 = 2.0 * t1 * t01;
        
        var nx2 = t02 * x0 + t03 * x1 + t00 * x2,
            ny2 = t02 * y0 + t03 * y1 + t00 * y2;
        
        var nx1 = lerp ( lerp ( x0 , x1 , t0 ) , lerp ( x1 , x2 , t0 ) , t1 ),
            ny1 = lerp ( lerp ( y0 , y1 , t0 ) , lerp ( y1 , y2 , t0 ) , t1 );
        
        ctx.moveTo( nx0, ny0 );
        ctx.quadraticCurveTo( nx1, ny1, nx2, ny2 );
      
      }
    
    ctx.stroke();
    ctx.closePath();
    ctx.restore();
}

/**
 * Linearly interpolates between two numbers
 */
function lerp(v0, v1, t) {
    return ( 1.0 - t ) * v0 + t * v1;
}

function drawFlow(argument) {

  document.getElementById("note").innerHTML = "Supply water through pump";

  var elem = document.getElementById("water");  
  elem.style.height="0px";
  elem.style.top="165px";
  elem.style.display="block";
  var t = 165;
  var h=0;
  var id = setInterval(frame,70);
  function frame() {
    if (t == 105 || h==60) {
      clearInterval(id);
    }
    else {
      t--; 
      h++; 
      elem.style.top = t + 'px';
      elem.style.height = h + 'px'; 
      
    }
  }
}