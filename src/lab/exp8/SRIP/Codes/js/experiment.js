//script for 2d canavas  to represent simulation 
  
function experiment() {



    document.getElementById("shapes").style.display = "inline-block";
    document.getElementById("start").style.display = "none";
    document.getElementById("stop").style.display = "inline-block";

    document.getElementById("note").style.display = "inline-block";
    document.getElementById("note").innerHTML = "hydralic bench";


    var elem = document.getElementById("water");   
    elem.style.display="none";

    var c = document.getElementById("myCanvas");

    const context = c.getContext('2d');   
    context.clearRect(0, 0, c.width, c.height);
    context.beginPath();
    
    var ctx = c.getContext("2d");

    //var vernier =c.getContext("2d");
    ctx.beginPath();


//border of glass
    ctx.save();
    ctx.moveTo(68,70);
    ctx.lineTo(430,85);
    ctx.stroke();
    ctx.restore();



//suport stands for hydralic bench
    ctx.save();
    ctx.beginPath();
    ctx.fillStyle="brown";
    ctx.fillRect(20, 180, 10, 100);
    ctx.fillRect(70, 185, 10, 100);
    ctx.fillRect(200, 190, 10, 110);
    ctx.fillRect(220, 190, 10, 110);
    ctx.fillRect(350, 200, 10, 110);
    ctx.fillRect(420, 195, 10, 110);
    ctx.restore();

    var rctx = c.getContext("2d");

    rctx.save();
    rctx.beginPath();
    rctx.rect(68, 70, 15, 90);//side
    rctx.fillStyle = "#cae2e7";
    rctx.fill();
    rctx.restore();

    rctx.save();
    rctx.rotate(4 * Math.PI / 180);
    rctx.beginPath();
    rctx.rect(70, 130, 355, 30);//back base
    rctx.fillStyle = "#ff5500";
    rctx.fill();
    rctx.restore();

    rctx.save();
    rctx.beginPath();
    rctx.rect(20, 69, 50, 90);//BACKSIDE
    rctx.fillStyle = "#4e9daf";
    rctx.fill();
    rctx.restore();


//base support
    rctx.save();
    rctx.rotate(4* Math.PI / 180);
    rctx.beginPath();
    rctx.fillStyle="brown";
    rctx.rect(28, 150, 340, 30);
    rctx.fill();
    rctx.restore();

    //front view
    var front_rect=c.getContext("2d");

    front_rect.save();
    front_rect.rotate(-6* Math.PI / 180);
    front_rect.beginPath();
    front_rect.rect(330,210,80,30);
    front_rect.fillStyle ="brown";
    front_rect.fill();
    front_rect.restore();

    front_rect.save();
    front_rect.beginPath();
    front_rect.rect(350,90,20,90);
    front_rect.fillStyle ="brown";
    front_rect.fill();
    front_rect.restore();


    front_rect.save();
    front_rect.beginPath();
    front_rect.rect(410,85,20,90);//support for weir
    front_rect.fillStyle ="brown";
    front_rect.fill();
    front_rect.restore();


//out let water container sump
    var cyl =c.getContext("2d");
    cyl.save();
    drawCylinder(cyl,440,210,100,100);//sump to stote
    cyl.restore();

    // outer border of glass
    ctx.save();
    ctx.moveTo(20,70);
    ctx.lineTo(350,90);
    ctx.stroke();
    ctx.restore();

}


function vernierWithScale(argument) {

    document.getElementById("note").innerHTML = "vernier scale placed on hydralic bench";

    var c = document.getElementById("myCanvas");
    var ctx = c.getContext("2d");
    //var vernier =c.getContext("2d");

//line which touches water flow hook
    ctx.save();
    ctx.moveTo(187,76);
    ctx.lineTo(187,120);
    ctx.stroke();
    ctx.restore();


    //support for adjuster
    ctx.save();
    ctx.moveTo(185,30);
    ctx.lineTo(195,30);
    ctx.stroke();
    ctx.restore();

    
    //scale
    ctx.save();
    ctx.rotate(0* Math.PI / 180);
    ctx.beginPath();
    ctx.rect(170,50, 5, 35);
    ctx.fillStyle = "#2d50b9";//scale color
    ctx.fill();
    ctx.restore();


    //adjuster
    ctx.save();
    ctx.rotate(0* Math.PI / 180);
    ctx.beginPath();
    ctx.rect(195,27, 5, 5);
    ctx.fillStyle = "#b35900";
    ctx.fill();
    ctx.restore();

    //support for vernier with wood
    ctx.save();
    ctx.rotate(0* Math.PI / 180);
    ctx.beginPath();
    ctx.rect(160,83, 50, 5);
    ctx.fillStyle = "#ff9933";
    ctx.fill();
    ctx.restore();


    //vernier
    ctx.save();
    ctx.rotate(0* Math.PI / 180);
    ctx.beginPath();
    ctx.rect(185,20, 5,65); //part of vernier
    ctx.fillStyle = "#b35900"; 
    ctx.fill();
    ctx.restore();


    //connection between scale and support
    ctx.save();
    //ctx.rotate(0* Math.PI / 180);
    ctx.beginPath();
    ctx.rect(165,50, 25,5);
    ctx.fillStyle = "#b35900"; 
    ctx.fill();


}


//function to draw cylinder
function drawCylinder(cy, x, y, w, h) {
    'use strict';
    var i, xPos, yPos, pi = Math.PI, twoPi = 2 * pi;

    cy.beginPath();

    for (i = 0; i < twoPi; i += 0.001) {
        xPos = (x + w / 2) - (w / 2 * Math.cos(i));
        yPos = (y + h / 8) + (h / 8 * Math.sin(i));

        if (i === 0) {
            cy.moveTo(xPos, yPos);
        } else {
            cy.lineTo(xPos, yPos);
        }
    }
    cy.moveTo(x, y + h / 8);
    cy.lineTo(x, y + h - h / 8);

    for (i = 0; i < pi; i += 0.001) {
        xPos = (x + w / 2) - (w / 2 * Math.cos(i));
        yPos = (y + h - h / 8) + (h / 8 * Math.sin(i));

        if (i === 0) {
            cy.moveTo(xPos, yPos);
        } else {
            cy.lineTo(xPos, yPos);
        }
    }

    cy.moveTo(x + w, y + h / 8);
    cy.lineTo(x + w, y + h - h / 8);

    cy.stroke();

}

function drawBaffel(argument) {

    document.getElementById("note").innerHTML = "Place baffel on hydralic bench which allows laminar flow of water";

    var c = document.getElementById("myCanvas");
    var ctx = c.getContext("2d");

    ctx.save();
    //ctx.rotate(4* Math.PI / 180);
    ctx.beginPath();
    ctx.rect(100,100, 60, 58);
    ctx.fillStyle = "#CDC5BB";//baffel
    ctx.fill();
    ctx.restore();

}