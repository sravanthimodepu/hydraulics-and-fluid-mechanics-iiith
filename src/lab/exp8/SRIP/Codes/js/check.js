//script file for storing input and output parameters ,calculating values


window.onload = window.localStorage.clear();





//input parameters for global access
function Parameters(l, h, cd , a) {
  this.len= l;
  this.hei = h;
  this.cof= cd;
  this.are = a;


}
//output parameters for global access
function outputParameters(q,H) {
  this.qv=q;
  this.H=H;
}

//initilization
var inputValues = new Parameters(50, 0.1, 0.5, 50);
var outputValues =new outputParameters(0,0);

//an array for displaying the resultand observations
  var x = 0;
  var arr = Array(3);
  for (var i = 0; i < arr.length; i++) { 
      arr[i] = new Array(2).fill(0); 

      //alert(arr[i][0]);
  }


localStorage["array"] = JSON.stringify(arr);
/*
array_values(arr);
function array_values() {
  var points=Array(3);
  for (i=0; i<params.length; i++) {
   alert("ad");
  }
}
var myObj, i;
myObj = {
   "cars": [
    {"name":"Ford", "models":["Fiesta", "Focus", "Mustang"]},
    {"name":"BMW", "models":["320", "X3", "X5"]},
    {"name":"Fiat", "models":["500", "Panda"] }
  ]
}*/
function checkbox() {
  
	//alert("js");
  var checkBox = document.getElementById("inputParameter");
  var text = document.getElementById("parameters");
  if (checkBox.checked == true){
      text.style.display = "block";


//length
      var length = document.getElementById("Length");
      var length_output= document.getElementById("Length_output");
      length_output.innerHTML = length.value;

      length.oninput = function() {
        //alert("js");
          length_output.innerHTML = this.value;
          var length_value=this.value;
          inputValues.len = length_value;
             //alert(length_value);
      }

  //height
      var height = document.getElementById("Height");
      var height_output= document.getElementById("Height_output");
      height_output.innerHTML = height.value;

      height.oninput = function() {
         
          height_output.innerHTML = this.value;
          var height_value=this.value;
          inputValues.hei=height_value;
          //alert(length_value);
      }


    //cofficient of discharge
      var cd = document.getElementById("cd");
      var cd_output= document.getElementById("cd_output");
      cd_output.innerHTML = cd.value;

      cd.oninput = function() {
          
          cd_output.innerHTML = this.value;
          cd_value=this.value;
          inputValues.cof=this.value;
      }
    //area
      var area  = document.getElementById("Area");
      var area_output= document.getElementById("Area_output");
      area_output.innerHTML = area.value;

      area.oninput = function() {
          
          area_output.innerHTML = this.value;
          var area_value=this.value;
          inputValues.are=this.value;


      }
  }//if closing for input parameters 

  
//if checkbox  deselected
  else {

     text.style.display = "none";
  }


}//ckeckbox()



//to display input values and calculated values
function show(){
    
    if(x==3){
      alert("only 3 observation acceptable")
    }
       //document.getElementById("sh").style.display=block;
       document.getElementById("sh").innerHTML = "length is  " + inputValues.len+"<br>height is" + 
       inputValues.hei+"<br>Cofficient of discharge is " +
       inputValues.cof+"<br>Area is " + inputValues.are;
      
       //caverting string to integer
       var l=parseInt(inputValues.len);
       var h=parseFloat(inputValues.hei);
       var c=parseFloat(inputValues.cof);
       var a=parseInt(inputValues.are);

        //"RESULT without velocity is ";
       //q=2/3 * c * l * sqrt (2g) * h ^ 2/3 
       //q=(1.71*cd*l*(h^(3/2))

       //v=q/a
       //hv=v^2/2*g
       //g is  9.8
       //h1=h+hv
       //qv=1.71*cd*l(h1^(3/2)-hv^(3/2))

       //var q =(2/3)* parseInt(inputValues.cof)*parseInt(inputValues.length)*Math.sqrt(2*9.8)*parseInt(inputValues.hei);
       //alert(2/3*c*l*Math.sqrt(2*9.8)*Math.pow(h,(3/2)));
       //alert(q);2/3*c*l*Math.sqrt(2*9.8)*Math.pow(h,(3/2))
       var q=parseFloat(1.71*c*l*Math.pow(h,(3/2))).toFixed(2);
       
       var V =parseFloat(q/a).toFixed(2);
       var hv= parseFloat(Math.pow(V,2)/(2*9.8)).toFixed(2);
       outputValues.H=parseFloat(h+hv).toFixed(2);
       //qv is flow of water with velocity for total height
       outputValues.qv= parseFloat(1.71*c*l*(Math.pow(outputValues.H,3/2)-Math.pow(hv,3/2))).toFixed(2);
       document.getElementById("q_value").innerHTML = "<hr>q value without velocity "+q+"<br> q value with velocity is  "+ outputValues.qv+"<br>height  value  "+outputValues.H+"<hr>";

       add_element_to_array();
       display_array();

}


function add_element_to_array()
  {
    arr[x][0] = outputValues.H;
    arr[x][1] = outputValues.qv;
    //myObj.cars[x].models[0]=arr[x][0];
    //alert(myObj.cars[x].models[0]);
    //myObj.p[x].point[1]=arr[x][1];
    alert("Elements: " + arr[x][0]+" "+arr[x][1]+" Added at index " + x);
    x++;
    outputValues.H=0;
    outputValues.qv=0;

    localStorage["array"] = JSON.stringify(arr);

  
   // array_values(arr);

  }

function display_array()
{

      var e = "OBSERVATION TABLE";   
      e+="<table><tr> <th>OBSERVATION No</th> <th>Height</th> <th>qv</th> </tr>"
     for (var y=0; y<x; y++){

        e += "<tr> <td>"+ y+ "</td>  <td>"+ arr[y][0] +"</td> <td>"+arr[y][1]+"</td></tr>";
      }
      e+="</table></centre>"
      document.getElementById("Result").innerHTML =e;
}