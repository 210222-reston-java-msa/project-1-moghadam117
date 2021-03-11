// capture the welcome element and modofy it so that it says welcome + username
let welcome = document.getElementById("welcome");

// capture the userString by accessing the session.....
var userString = sessionStorage.getItem("currentUser");

if (userString === null) {
  window.location = "http://localhost:8080/project-1/";
} else {
  var currentUser = JSON.parse(userString);

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
      id: currentUser.userId,
    };
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
      if (this.readyState === 4 && this.status === 200) {
        var myObj = JSON.parse(this.responseText);
        console.log(myObj);
        populateTable(myObj);
        function populateTable(myObj) {
          var table = document.getElementById("table");

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
            td.innerHTML = obj.submitDate;
            td = document.createElement("td");
            tr.appendChild(td);
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
          });
        }
      }
    };
    xhr.open("POST", "http://localhost:8080/project-1/expense-emp");

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
function submitExpense() {
  let amount = document.getElementById("amount").value;
  let description = document.getElementById("description").value;
  let authorId = currentUser.userId;
  let type = document.getElementById("typeId").value;
 let typeId;
  switch (type) {
    case "lodging":
      typeId = 1;
      break;
    case "travel":
      typeId = 2;
      break;
    case "food":
      typeId = 3;
      break;
    case "other":
      typeId = 4;
      break;
  }

  let submitTemplate = {
    amount: amount,
    description: description,
    authorId: authorId,
    typeId: typeId,
  };
console.log(submitTemplate);
  let xhr = new XMLHttpRequest();

  xhr.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 200) {
      alert("Your Reimbursement Submitted Successfully");
      window.location = "http://localhost:8080/project-1/expense-emp.html";
    }
  };

  xhr.open("POST", "http://localhost:8080/project-1/submit-emp");

  xhr.send(JSON.stringify(submitTemplate));
}
