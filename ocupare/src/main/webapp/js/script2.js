function functie(element) {
	console.log(element.id);
	var div = document.getElementById("tabel");
	console.log(div);
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

	var tbody = document.createElement('tbody');
	var tr2 = document.createElement('tr');

	var td = document.createElement('td');
	td.innerHTML = '28.01.2012 - 29.01.2012';
	var td2 = document.createElement('td');
	td2.innerHTML = '10.00-12.00';
	var td3 = document.createElement('td');
	td3.innerHTML = element.value;

	tr.appendChild(th1);
	tr.appendChild(th2);
	tr.appendChild(th3);

	tr2.appendChild(td);
	tr2.appendChild(td2);
	tr2.appendChild(td3);

	thead.appendChild(tr);
	tbody.appendChild(tr2);

	tbl.appendChild(thead);
	tbl.appendChild(tbody);

	div.appendChild(tbl);
}