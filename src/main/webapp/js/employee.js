const URL = 'http://localhost:8080/hello-webapp-0.1/rest/employees';

appelAjaxJSON('GET', URL, function(err, employees) {
	if(err) { // TODO gérer l'erreur
		console.log("oups erreur est survenue...");
		return;
	}
	var div = document.querySelector("#employees");
	div.innerHTML = buildEmployeesHTMLTable(employees);
	metDesListenersSurLesLiensQuiSontDansLaTable();

});

function buildEmployeesHTMLTable(employees) {
	var html = "";
	html += '<table id="employees">';
	for(var i = 0; i< employees.length; i++) {
		html += buildEmployeeHTMLTableRow(employees[i]);
	}
	html += "</table>";
	return html;
}

function buildEmployeeHTMLTableRow(employee) {
	var html = "<tr>";
	html += '<td><a id="link-'+ employee.id +'" href="">' + employee.id + '</a></td>';
	html += "<td>" + employee.firstName + "</td>";
	html += "<td>" + employee.lastName + "</td>";
	html += "</tr>";
	return html;
}

function metDesListenersSurLesLiensQuiSontDansLaTable() {
	var links = document.querySelectorAll("#employees a");
	for(var i = 0; i < links.length; i++) {
		links[i].addEventListener('click', function(e) {
			e.preventDefault();
			var id = this.id.substring(5);

			// AJAX pour récup employee dont l'ID est id
			console.log(URL+'/'+id);
			appelAjaxJSON('GET', URL+'/'+id, function(err, employee) {
				if(err) { // TODO gérer l'erreur
					console.log("oups erreur est survenue...");
					return;
				}

				console.log('Found employee', employee);
				var detailDiv = document.querySelector("#employee-detail");
				detailDiv.innerHTML='<h2>' + employee.firstName +' : ' + employee.lastName + ' : ' + employee.gender + ' : ' + employee.birthDate + ' : ' + employee.hireDate + '</h2>';

			});
		});
	}
}

function appelAjaxJSON(method, url, callback) {
	var xhr = new XMLHttpRequest();
	xhr.open(method, url);

	xhr.onreadystatechange = function(e) {
		if(xhr.readyState == 4) {
			if(xhr.status == 200) {
				console.log(xhr.responseText);
				callback(null, JSON.parse(xhr.responseText));
			} else {
				callback(new Error(xhr), null);
			}
		}
	};

	xhr.send(null);
}