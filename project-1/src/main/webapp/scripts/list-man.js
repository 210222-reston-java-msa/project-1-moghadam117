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

    let xhr = new XMLHttpRequest();

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
              td.innerHTML = obj.userId;
              td = document.createElement("td");
              tr.appendChild(td);
              td.innerHTML = obj.username;
              td = document.createElement("td");
              tr.appendChild(td);
              td.innerHTML = obj.password;
              td = document.createElement("td");
              tr.appendChild(td);
              td.innerHTML = obj.firstName;
              td = document.createElement("td");
              tr.appendChild(td);
              td.innerHTML = obj.lastName;
              td = document.createElement("td");
              tr.appendChild(td);
              td.innerHTML = obj.email;
              td = document.createElement("td");
              tr.appendChild(td);
              td.innerHTML = obj.role.roleId;
              td.style = "display:none";
              td = document.createElement("td");
              tr.appendChild(td);
              td.innerHTML = obj.role.roleName;
            });
          }
        }
      }
    };

    xhr.open("GET", "http://localhost:8080/project-1/emp-all");

    xhr.send();
  }
}
function logout() {
  let xhr = new XMLHttpRequest();
  xhr.open("POST", "http://localhost:8080/project-1/logout");
  xhr.send();

  sessionStorage.removeItem("currentUser");
  window.location = "http://localhost:8080/project-1/";
}
