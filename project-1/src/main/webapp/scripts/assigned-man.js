// capture the welcome element and modofy it so that it says welcome + username
let welcome = document.getElementById("welcome");

// capture the userString by accessing the session.....
let userString = sessionStorage.getItem("currentUser");

if (userString === null) {
	window.location = "http://localhost:8080/project-1/";
} else {
	let currentUser = JSON.parse(userString);

	console.log(sessionStorage.getItem("currentUser"));
	if (currentUser != null) {
		welcome.innerHTML =
			"Welcome " +
			currentUser.firstName +
			" " +
			currentUser.lastName +
			" ID NUMBER " +
			currentUser.userId;

		let idTemplate = {
			id: 1,
		};
		let xhr = new XMLHttpRequest();

		xhr.onreadystatechange = function() {
			if (this.readyState === 4 && this.status === 200) {
				let myObj = JSON.parse(this.responseText);
				console.log(myObj);
				table.innerHTML = "";
				if (myObj != null) {
					populateTable(myObj);
					function populateTable(myObj) {
						let table = document.getElementById("table");

						myObj.forEach((obj) => {
							let tr = document.createElement("tr");
							table.appendChild(tr);

							let td = document.createElement("td");
							tr.appendChild(td);
							td.innerHTML = obj.reimbId;
							td = document.createElement("td");
							tr.appendChild(td);
							td.innerHTML = obj.amount;
							td = document.createElement("td");
							tr.appendChild(td);
							td.innerHTML = obj.description;
							td = document.createElement("td");
							tr.appendChild(td);
							td.innerHTML = obj.submitDate.slice(0, 19);
							td = document.createElement("td");
							tr.appendChild(td);
							if (obj.resolveDate != null)
								td.innerHTML = obj.resolveDate.slice(0, 19);
							else
								td.innerHTML = obj.resolveDate;
							td = document.createElement("td");
							tr.appendChild(td);
							td.innerHTML = obj.authorId;
							td = document.createElement("td");
							tr.appendChild(td);
							if (obj.resolverId != 0) td.innerHTML = obj.resolverId;
							else td.innerHTML = " ";
							td = document.createElement("td");
							tr.appendChild(td);
							td.innerHTML = obj.status.statusName;
							td = document.createElement("td");
							tr.appendChild(td);
							td.innerHTML = obj.type.typeName;
							let a = document.createElement("a");
							a.id = obj.reimbId;
							a.className = "btn btn-outline-success btn-sm ";
							a.style = "margin-top:12px";
							a.innerHTML = "Approve";
							a.addEventListener("click", function() {
								console.log(a.id);
								let resolveTemplate = {
									reimbId: a.id,
									statusId: 2,
								};
								console.log(resolveTemplate);
								let xhr = new XMLHttpRequest();
								xhr.onreadystatechange = function() {
									if (this.readyState === 4 && this.status === 200) {
										alert("Reimbursement has been approved successfully");
										window.location =
											"http://localhost:8080/project-1/assigned-man.html";
									}
								};

								xhr.open("POST", "http://localhost:8080/project-1/resolve-man");

								xhr.send(JSON.stringify(resolveTemplate));
							});
							tr.appendChild(a);
							a = document.createElement("a");
							a.id = obj.reimbId;
							a.innerHTML = "Deny";
							a.style = "margin-top:12px";
							a.className = "btn btn-outline-danger btn-sm ";
							a.addEventListener("click", function() {
								console.log(a.id);
								let resolveTemplate = {
									reimbId: a.id,
									statusId: 3,
								};
								console.log(resolveTemplate);
								let xhr = new XMLHttpRequest();
								xhr.onreadystatechange = function() {
									if (this.readyState === 4 && this.status === 200) {
										alert("Reimbursement has been denied successfully");
										window.location =
											"http://localhost:8080/project-1/assigned-man.html";
									}
								};

								xhr.open("POST", "http://localhost:8080/project-1/resolve-man");

								xhr.send(JSON.stringify(resolveTemplate));
							});
							tr.appendChild(a);
						});
					}
				}
			}
		};
		xhr.open("POST", "http://localhost:8080/project-1/reimb-status");

		xhr.send(JSON.stringify(idTemplate));
	}
}

function logout() {
	let xhr = new XMLHttpRequest();
	xhr.open("POST", "http://localhost:8080/project-1/logout");
	xhr.send();

	sessionStorage.removeItem("currentUser");
	window.location = "http://localhost:8080/project-1/";
}
