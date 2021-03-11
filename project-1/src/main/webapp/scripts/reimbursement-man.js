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
      "Welcome " + currentUser.firstName + " " + currentUser.lastName+ " ID NUMBER " + currentUser.userId;
  }
}

function logout() {
  let xhr = new XMLHttpRequest();
  xhr.open("POST", "http://localhost:8080/project-1/logout");
  xhr.send();

  sessionStorage.removeItem("currentUser");
  window.location = "http://localhost:8080/project-1/";
}
function reimbById() {
  let id = document.getElementById("id").value;
  let idTemplate = {
    id: id,
  };
  console.log(idTemplate);
 
  let xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 200) {
      let myObj = JSON.parse(this.responseText);
      console.log(myObj);
      table.innerHTML="";
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
    }
  };
  xhr.open("POST", "http://localhost:8080/project-1/reimb-man");

  xhr.send(JSON.stringify(idTemplate));
}
function reimbByStatus(sel) {
  let status = sel.options[sel.selectedIndex].index;
  let idTemplate = {
    id: status,
  };

  console.log(status);

  console.log(idTemplate);

  let xhr = new XMLHttpRequest();
  if (status < 4) {
    xhr.onreadystatechange = function () {
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
      }
    };
    xhr.open("POST", "http://localhost:8080/project-1/reimb-status");

    xhr.send(JSON.stringify(idTemplate));
  } else if (status == 4) {
    xhr.onreadystatechange = function () {
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
      }
    };
    xhr.open("GET", "http://localhost:8080/project-1/reimb-all");

    xhr.send();
  }
}


