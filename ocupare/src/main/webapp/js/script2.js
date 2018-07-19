var vaL = null;

function getJson(jsonData) {
	console.log(jsonData);
	console.log(jsonData.length);

	var div = document.getElementById("tabel");

	if (div.hasChildNodes()) {

		div.removeChild(div.childNodes[0]);
	}

	var tbl = document.createElement('table');
	tbl.setAttribute('class', 'table');
	var thead = document.createElement('thead');
	var tr = document.createElement('tr');

	var th1 = document.createElement('th');
	th1.innerHTML = 'Perioada';
	var th2 = document.createElement('th');
	th2.innerHTML = 'Ora';
	var th3 = document.createElement('th');
	th3.innerHTML = 'Sala';
	var th4 = document.createElement('th');
	th4.innerHTML = 'Ziua';

	var tbody = document.createElement('tbody');

	tr.appendChild(th1);
	tr.appendChild(th4);
	tr.appendChild(th2);
	tr.appendChild(th3);

	if (jsonData.length >= 1) {

		for (i = 0; i < jsonData.length; ++i) {
			
			
			var anI = jsonData[i].inceput[2];
			var lunaI = jsonData[i].inceput[1]
			var ziI = jsonData[i].inceput[0];

			var oraI = jsonData[i].inceput[3];
			var minI = jsonData[i].inceput[4];

			var anS = jsonData[i].sfarsit[2];
			var lunaS = jsonData[i].sfarsit[1]
			var ziS = jsonData[i].sfarsit[0];

			var oraS = jsonData[i].sfarsit[3];
			var minS = jsonData[i].sfarsit[4];

			var curr = new Date;
			var first = curr.getDate() - curr.getDay() + 1;
			var last = first + 6;

			var firstday = new Date(curr.setDate(first))
					.toLocaleDateString("en-US");
			var lastday = new Date(curr.setDate(last))
					.toLocaleDateString("en-US");
			
			console.log(firstday);

			var zi1 = firstday.split('/');
			var zi2 = lastday.split('/');
			
			var from = new Date(ziI,parseInt(lunaI)-1,anI);
			var to = new Date(ziS,parseInt(lunaS)-1,anS);
			var check1 = new Date(zi1[2],zi1[0]-1,zi1[1]);
			var check2 = new Date(zi2[2],zi2[0]-1,zi2[1]);
			
			console.log(from + ' ' + to);
			console.log(check1 + ' ' + check2);
						

			var ok = 0;
			if(from>=check1 && from<=check2)
			{
				console.log('da');
				ok=1;
			}

			if (lunaI < 10) {
				lunaI = '0' + lunaI;
			}

			if (anI < 10) {
				anI = '0' + anI;
			}

			if (lunaS < 10) {
				lunaS = '0' + lunaS;
			}

			if (anS < 10) {
				anS = '0' + anS;
			}

			if (oraI < 10) {
				oraI = '0' + oraI;
			}
			if (oraS < 10) {
				oraS = '0' + oraS;
			}
			if (minI < 10) {
				minI = '0' + minI;
			}
			if (minS < 10) {
				minS = '0' + minS;
			}

			if (ok == 1) {

				var tr2 = document.createElement('tr');
				var td = document.createElement('td');

				td.innerHTML = check1.toLocaleDateString("ro-RO") + '-' + check2.toLocaleDateString("ro-RO");
				var td4 = document.createElement('td');
				td4.innerHTML =  anI + '.' + lunaI + '.' + ziI;
				var td2 = document.createElement('td');
				td2.innerHTML = oraI + '.' + minI + '-' + oraS + '.' + minS;
				var td3 = document.createElement('td');
				td3.innerHTML = vaL;

				tr2.appendChild(td);
				tr2.appendChild(td4);
				tr2.appendChild(td2);
				tr2.appendChild(td3);
				
				
				tbody.appendChild(tr2);

				thead.appendChild(tr);

				tbl.appendChild(thead);
				tbl.appendChild(tbody);

				div.appendChild(tbl);
			}
		}
	
	} 
	else 
	{
		var tr2 = document.createElement('tr');
		var td = document.createElement('td');
		td.innerHTML = '-';
		var td4 = document.createElement('td');
		td4.innerHTML = '-';
		var td2 = document.createElement('td');
		td2.innerHTML = '-';
		var td3 = document.createElement('td');
		td3.innerHTML = vaL;

		tr2.appendChild(td);
		tr2.appendChild(td4);
		tr2.appendChild(td2);
		tr2.appendChild(td3);
		tbody.appendChild(tr2);

		thead.appendChild(tr);

		tbl.appendChild(thead);
		tbl.appendChild(tbody);

		div.appendChild(tbl);
	}
	
	thead.appendChild(tr);

	tbl.appendChild(thead);
	tbl.appendChild(tbody);

	div.appendChild(tbl);

}

function functie(element) {

	var value = parseInt(element.id.substring(3, element.id.length)) + 1;
	console.log(value);
	console.log('http://localhost:8080/ocupare/webapi/evenimente/query?'
				+ 'salaID=' + value);

	$.ajax({
		type : 'GET',
		url : 'http://localhost:8080/ocupare/webapi/evenimente/query?'
				+ 'salaID=' + value,
		dataType : 'json',
		contentType : 'application/json',
		success : getJson
	});

	vaL = element.value;
}

