

function Results() {
	var h,  d1, t, d2, Q_th, Q_a, Cd;
		var a=0.36;// Area of the tank cm^3
		var g=981;//Accleration due to gravity cm/s^2



		
	h = Number(document.Calculations.Height.value); // Height Difference between upstream tap and downstream tap
	//od = Number(document.Calculations.Orrifice_dia.value); // Orrifice Diameter
	d1 = Number(document.Calculations.D1.value);// Diameter of the Tube
	t= Number(document.Calculations.Time.value);// Time to collect water for a height difference of h cm, measured in seconds.
	d2 = Number(document.Calculations.D2.value);//Diameter of the orrifice
		if(h == null || d1 == null || d2 == null || t == null)
		{
			alert("Please make sure that the Input form in filled properly!!");
		}
				if(h == 0 || d1 == 0 || d2 == 0 || t == 0)
		{
			alert("Input cannot be Zero!! or null");
		}
       if(h<0 || d1<0 || d2<0 || t<0)
              {
                  alert("Error!!.. Input must not be Negative!!");
              }
        if(h>10000 ||  d1>10000 || d2>10000 || t>1000)
              {
                  alert("Error!!.. Input must not be very large!!");
              }

	if(h>0 && h<=10000 && d1>0 && d1<=10000 && d2>0 && d2<=10000 && t>0 && t<=10000)
	{
			var A1 = Math.PI * 0.25 * d1 * d1; 
	var A2 = Math.PI * 0.25 * d2 * d2;
	var H = h;
	Q_th  = (A1 * A2* Math.sqrt(2* g * H))/Math.sqrt(A1*A1 - A2*A2); // Theoretical discharge (Q_th) in cm^3/sec
	Q_a = (a * h)/t; // Actual discharge (Q_a) in cm^3/sec
	Cd = Q_a/Q_th; // Coefficient of discharge (Cd)
	Q_th = Q_th.toFixed(4);
	Q_a = Q_a.toFixed(4);
	Cd = Cd.toFixed(10);

	document.Calculations.q_th.value = Q_th;
	document.Calculations.q_a.value = Q_a;
	document.Calculations.c_d.value = Cd;
	}
	


}

