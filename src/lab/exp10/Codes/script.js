	var canvas = document.querySelector("canvas");
	var c = canvas.getContext("2d");
	var selection;
	var x=900;
	var xCounter=920;
	var flag=10;
	var xLeftWater=392;
	var yLeftWater=291;
	var xRightWater=536;
	var xLine=599;
	var yLine=387;
	var end=0;
	var id=null;
	var lineId=null;
	var i,j;
	var btn;
	canvas.width = window.innerWidth; //Setting the canvas to full width of the window
	canvas.height = window.innerHeight; //Setting the canvas to full height of the window
	
	
	//-----------Drawing outline boxes-------------------

	function drawMain()
	{
		c.beginPath();
		c.lineWidth="10";
		c.strokeStyle="#006599";
		c.rect(150,50,1000,600);
		c.closePath();
		c.stroke();
		function draw(lw,ss,x1,y1,x2,y2) //function for generalized drawing
			{
				c.beginPath();
			c.lineWidth=lw;
			c.StrokeStyle=ss;
			c.moveTo(x1,y1);
			c.lineTo(x2,y2);
			c.closePath();
			c.stroke();
			}
		draw("3","#006599",350,50,350,650);
		draw("1","#006599",535,39,775,39);
		c.font="25px Arial";
		c.fillStyle="#006599";
		c.fillText("Reynolds Experiment",535,35);
		c.stroke();
		draw("3","#767676",390,240,390,450);
		c.beginPath();
		c.lineWidth="3";
		c.strokeStyle="#767676";
		c.moveTo(390,240);
		c.lineTo(390,450);
		c.lineTo(620,450);
		c.lineTo(620,405);
		c.lineTo(985,405);
		c.lineTo(985,360);
		c.lineTo(620,360);
		c.lineTo(620,240);
		c.stroke();
		c.closePath();
		c.fillStyle="#0099FF";
		c.fillRect(391,291,228,158);
		c.fillRect(615,360,295,44);
		var k=0;
		var m=0;
		for(j=0;298+m<445;j++)
			{ 
				k=0;
				for(i=0;408+k<620;i++)
				{    
					c.beginPath();
					c.strokeStyle="white";
					c.lineWidth="2";
					c.moveTo(398+k,298+m);
					c.lineTo(408+k,298+m);
					c.stroke();
					c.closePath();
					k=k+20;
				}
				m=m+15;
			}
			m=0;
			for(j=0;373+m<403;j++)
				{ 
					k=0;
					for(i=0;408+k<926;i++)
						{    
							c.beginPath();
							c.strokeStyle="white";
							c.lineWidth="2";
							c.moveTo(398+k,373+m);
							c.lineTo(408+k,373+m);
							c.stroke();
							c.closePath();
							k=k+20;
  
						}
					m=m+15;
				}
	c.beginPath();
	c.strokeStyle="black";
	c.lineWidth="1";
	c.moveTo(465,240);
	c.lineTo(465,324);
	c.lineTo(490,349);
	c.lineTo(490,394);
	c.lineTo(580,394);
	c.lineTo(599,387);
	c.lineTo(580,380);
	c.lineTo(510,380);
	c.lineTo(510,349);
	c.lineTo(535,324);
	c.lineTo(535,270);
	c.lineTo(465,270);
	c.fillStyle="#333333";
	c.fill();
	c.lineTo(535,270);
	c.lineTo(535,240);
	c.stroke();
	c.closePath();
	c.beginPath();
	c.moveTo(900,404);
	c.lineTo(910,381.5);
	c.lineTo(900,359);
	c.lineTo(920,359);
	c.lineTo(910,381.5);
	c.lineTo(920,404);
	c.lineTo(900,404);
	c.lineTo(900,404);
	c.fill();
	c.closePath();
	c.stroke();
	c.strokeStyle="#0099FF";
	c.fillStyle="#0099FF";
	m=0;
}
	drawMain();
	function heading(pid)  //function for replacing the heading as per user selection
			{
				var x=pid.name;
				var h=document.getElementById("heading");
				h.innerHTML=x;
				selection=x;
				cancelAnimationFrame(lineId);
				cancelAnimationFrame(id);
				x=900;
				btn=document.getElementById("b");
				btn.disabled=false;
				c.clearRect(380,200,650,400);
				drawMain();
				xCounter=920;
				flag=10;
				xLeftWater=392;
				yLeftWater=291;
				xRightWater=536;
				xLine=599;
				yLine=387;
				end=0;
				id=null;
				lineId=null;
				
			}
	function disappear()
	{
		c.beginPath();
		c.strokeStyle="white";
		c.moveTo(900,404);
		c.lineTo(920,404);
		c.stroke();
		c.closePath();
		c.beginPath();
		c.strokeStyle="white";
		c.moveTo(900,359);
		c.lineTo(920,359);
		c.stroke();
		c.closePath();
		c.beginPath();
		c.strokeStyle="#767676";
		c.moveTo(900,404);
		c.lineTo(920,404);
		c.stroke();
		c.closePath();
		c.beginPath();
		c.strokeStyle="#767676";
		c.moveTo(900,359);
		c.lineTo(920,359);
		c.stroke();
		c.closePath();
		c.fillRect(x,360,20,44);
		x+=20;
		c.beginPath();
		c.strokeStyle="white";
		c.lineWidth="2";
		c.moveTo(898,373);
		c.lineTo(912,373);
		c.stroke();
		c.closePath();
		c.beginPath();
		c.strokeStyle="white";
		c.lineWidth="2";
		c.moveTo(898,388);
		c.lineTo(912,388);
		c.stroke();
		c.closePath();
	}
	//----Code for Laminar Animation-----
	function laminarLine()
	{
		lineId=requestAnimationFrame(laminarLine);
		c.beginPath();
		c.strokeStyle="red";
		c.lineWidth="2";
		c.moveTo(xLine,yLine);
		c.lineTo(xLine+2,yLine);
		c.stroke();
		c.closePath();
		if(xLine<982)
		{
			xLine+=2.1;
		}
		else
		{   
            cancelAnimationFrame(lineId);
			x=900;
			btn=document.getElementById("b");
			btn.disabled=false;
			c.clearRect(380,200,650,400);
			drawMain();
			xCounter=920;
			flag=10;
			xLeftWater=392;
			yLeftWater=291;
			xRightWater=536;
			xLine=599;
			yLine=387;
			end=0;
			id=null;
			lineId=null;
		}


	}
	function laminarAnimate()
	{ 	
		id=requestAnimationFrame(laminarAnimate);
		c.clearRect(xLeftWater,yLeftWater,72,2);
		c.clearRect(xRightWater,yLeftWater,82.2,2);
		if(yLeftWater<321)
		{
			yLeftWater+=0.17;
		}
	
		c.fillRect(x,360,11,44);
		c.stroke();
		if(flag===100)
		{       
				c.beginPath();
				c.strokeStyle="white";
				c.lineWidth="2";
				c.moveTo(xCounter,373);
				c.lineTo(xCounter+10,373);
				c.stroke();
				c.beginPath();
				c.strokeStyle="white";
				c.lineWidth="2";
				c.moveTo(xCounter,388);
				c.lineTo(xCounter+10,388);
				c.stroke();
				c.closePath();
				c.closePath();
				xCounter+=20;
				flag=10;
		}    
		if(x<974)
		{
			x=x+0.3;
			flag++;
		}
		else
		{
			end=1;
		}
			c.beginPath();
			c.strokeStyle="white";
			c.lineWidth="2";
			c.moveTo(xCounter,373);
			c.lineTo(xCounter+10,373);
			c.stroke();
			c.closePath();
			c.beginPath();
			c.strokeStyle="white";
			c.lineWidth="2";
			c.moveTo(xCounter,388);
			c.lineTo(xCounter+10,388);
			c.stroke();
			c.closePath();
		if(end)
		{
			cancelAnimationFrame(id);
			x=900;
		}
		
	}
	function laminarDisappear()
	{
        
		disappear();
		laminarAnimate();
		laminarLine();
	}
	
	//----Code for Transitional Animation-----
	function transitionalAnimate()
	{
		
		id=requestAnimationFrame(transitionalAnimate);
		c.clearRect(xLeftWater,yLeftWater,227,2);
		if(yLeftWater<335)
		{
			yLeftWater+=0.33;
		}
		c.beginPath();
		c.strokeStyle="black";
		c.lineWidth="1";
		c.moveTo(465,240);
		c.lineTo(465,324);
		c.lineTo(490,349);
		c.lineTo(490,394);
		c.lineTo(580,394);
		c.lineTo(599,387);
		c.lineTo(580,380);
		c.lineTo(510,380);
		c.lineTo(510,349);
		c.lineTo(535,324);
		c.lineTo(535,270);
		c.lineTo(465,270);
		c.fillStyle="#333333";
		c.fill();
		c.lineTo(535,270);
		c.lineTo(535,240);
		c.stroke();
		c.fillStyle="#0099FF";
		c.fillRect(x,360,11,44);
		c.stroke();
		if(flag===75)
		{       
				c.beginPath();
				c.strokeStyle="white";
				c.lineWidth="2";
				c.moveTo(xCounter,373);
				c.lineTo(xCounter+10,373);
				c.stroke();
				c.beginPath();
				c.strokeStyle="white";
				c.lineWidth="2";
				c.moveTo(xCounter,388);
				c.lineTo(xCounter+10,388);
				c.stroke();
				c.closePath();
				c.closePath();
				xCounter+=20;
				flag=10;
		}
		if(x<974)
		{
			x=x+0.4;
			flag++;
		}
		else
		{
			end=1;
		}
			c.beginPath();
			c.strokeStyle="white";
			c.lineWidth="2";
			c.moveTo(xCounter,373);
			c.lineTo(xCounter+10,373);
			c.stroke();
			c.closePath();
			c.beginPath();
			c.strokeStyle="white";
			c.lineWidth="2";
			c.moveTo(xCounter,388);
			c.lineTo(xCounter+10,388);
			c.stroke();
			c.closePath();
				
		if(end)
		{
			cancelAnimationFrame(id);
			x=900;
		}
		
	}
	var s=1;
	function transitionalLine()
	{
		lineId=requestAnimationFrame(transitionalLine);
		if(xLine<790)
		{
			c.beginPath();
		c.strokeStyle="red";
		c.lineWidth="2.2";
		c.moveTo(xLine,yLine);
		c.lineTo(xLine+2,yLine);
		c.stroke();
		c.closePath();
		xLine+=2.2;
		}
		else if(xLine<982)
		{
			var yRandom=Math.random()*s*4;
			s=s*-1;
			c.beginPath();
			c.strokeStyle="red";
			c.lineWidth="3.7";
			if(yLine+yRandom>365 && yLine+yRandom<400)
			{
			c.moveTo(xLine-1,yLine);
			c.lineTo(xLine-1,yLine+yRandom);
			c.stroke();
			c.closePath();
			yLine=yLine+yRandom;
			xLine+=3.7;
			}
			
		}
		
			
		else
		{   
			cancelAnimationFrame(lineId);
			x=900;
			btn=document.getElementById("b");
			btn.disabled=false;
			c.clearRect(380,200,650,400);
			drawMain();
			xCounter=920;
			flag=10;
			xLeftWater=392;
			yLeftWater=291;
			xRightWater=536;
			xLine=599;
			yLine=387;
			end=0;
			id=null;
			lineId=null;
		}

	}
	function transitionalDisappear()
	{
		
		disappear();
		transitionalAnimate();
		transitionalLine();
	}
	
	//----Code for Turbulent Animation-----
	
	function turbulentAnimate()
	{
		
		id=requestAnimationFrame(turbulentAnimate);
		c.clearRect(xLeftWater,yLeftWater,227,2);
		if(yLeftWater<345)
		{
			yLeftWater+=0.5;
		}
		c.beginPath();
		c.strokeStyle="black";
		c.lineWidth="1";
		c.moveTo(465,240);
		c.lineTo(465,324);
		c.lineTo(490,349);
		c.lineTo(490,394);
		c.lineTo(580,394);
		c.lineTo(599,387);
		c.lineTo(580,380);
		c.lineTo(510,380);
		c.lineTo(510,349);
		c.lineTo(535,324);
		c.lineTo(535,270);
		c.lineTo(465,270);
		c.fillStyle="#333333";
		c.fill();
		c.lineTo(535,270);
		c.lineTo(535,240);
		c.stroke();
		c.fillStyle="#0099FF";
		c.fillRect(x,360,11,44);
		c.stroke();
		if(flag===60)
		{       
				c.beginPath();
				c.strokeStyle="white";
				c.lineWidth="2";
				c.moveTo(xCounter,373);
				c.lineTo(xCounter+10,373);
				c.stroke();
				c.beginPath();
				c.strokeStyle="white";
				c.lineWidth="2";
				c.moveTo(xCounter,388);
				c.lineTo(xCounter+10,388);
				c.stroke();
				c.closePath();
				c.closePath();
				xCounter+=20;
				flag=10;
		}
		if(x<974)
		{
			x=x+0.5;
			flag++;
		}
		else
		{
			end=1;
		}
				c.beginPath();
				c.strokeStyle="white";
				c.lineWidth="2";
				c.moveTo(xCounter,373);
				c.lineTo(xCounter+10,373);
				c.stroke();
				c.closePath();
				c.beginPath();
				c.strokeStyle="white";
				c.lineWidth="2";
				c.moveTo(xCounter,388);
				c.lineTo(xCounter+10,388);
				c.stroke();
				c.closePath();
				
		if(end)
		{
			cancelAnimationFrame(id);
			x=900;
		}
		
	}
	function turbulentLine()
	{
		
		lineId=requestAnimationFrame(turbulentLine);
		if(xLine<982)
		{
			var yRandom=Math.random()*s*7;
			s=s*-1;
			c.beginPath();
			c.strokeStyle="red";
			c.lineWidth="3.5";
			if(yLine+yRandom>365 && yLine+yRandom<400)
			{
			c.moveTo(xLine,yLine);
			c.lineTo(xLine,yLine+yRandom);
			c.stroke();
			c.closePath();
			yLine=yLine+yRandom;
			}
			
		}
		if(xLine<982)
		{
			xLine+=3.5;
		}
		else
		{   
			cancelAnimationFrame(lineId);
			x=900;
			btn=document.getElementById("b");
			btn.disabled=false;
			c.clearRect(380,200,650,400);
			drawMain();
			xCounter=920;
			flag=10;
			xLeftWater=392;
			yLeftWater=291;
			xRightWater=536;
			xLine=599;
			yLine=387;
			end=0;
			id=null;
			lineId=null;
		}
	}
	function turbulentDisappear()
	{
		disappear();
		turbulentAnimate();
		turbulentLine();
	}
	
	function main()
	{
		
		if(selection==="Transitional")
		{   
			x=900;
			btn=document.getElementById("b");
			btn.disabled=true;
			transitionalDisappear();
		}	
		else if(selection==="Turbulent")
		{   
			x=900;
			btn=document.getElementById("b");
			btn.disabled=true;
			turbulentDisappear();
		}
		else
		{
			x=900;
			btn=document.getElementById("b");
			btn.disabled=true;
			laminarDisappear();
			
		}
	}
	
	
	
	
		var pattern=/\D/g;
		var len,time,dia,first,last;
		var charFlag,flagControl;
		//Extracting the inputs from user
		function getData()
		{
		charFlag=false;
		flagControl=false;
		len=document.getElementById("myRange3").value;
		var len1=len.match(pattern);
		
		time=document.getElementById("myRange4").value;
		var time1=time.match(pattern);
		
		dia=document.getElementById("myRange2").value;
		var dia1=dia.match(pattern);
			first=document.getElementById("myRange1").value;
		var first1=first.match(pattern);
			last=document.getElementById("myRange5").value;
		var last1=last.match(pattern);
		
		
		if(len1!=null||time1!=null||dia1!=null||first1!=null||last1!=null)
		{   
			charFlag=true;
			flagControl=true;
			alert("Please enter only digits,that too should be positive!");
		}
		if(len<=0||time<=0||dia<=0)
		{
			if(!flagControl)
			{
				charFlag=true;
				alert("Plese enter only positive numbers!");
			}
		}
		
		}
		//Function to determine flow of regime
		function regime(reynum)
		
		{   

			var reg=document.getElementById("flow");
			if(reynum<2000)
			{   
				reg.innerHTML="Laminar Flow";
			}
			else if(reynum>2000 && reynum<4000)
			{
				reg.innerHTML="Transitional Flow";
			}
			else
			{
				reg.innerHTML="Turbulent Flow";
			}
		}
		//Function to calculate the reynolds number
		function rno()
		{   getData();
			if(!charFlag)
			{
				var num=((len/time)*dia)/0.55;
				var reynum=document.getElementById("Number");
				reynum.innerHTML=num;
				regime(num);
			}
		}
		
		
		
		function reset()
		{
			document.getElementById("myRange1").value=null;
			document.getElementById("myRange2").value=null;
			document.getElementById("myRange3").value=null;
			document.getElementById("myRange4").value=null;
			document.getElementById("myRange5").value=null;
		}
		