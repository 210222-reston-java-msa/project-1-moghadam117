
let welcome = document.getElementById("welcome");


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

    table.innerHTML = "";

    populateTable(currentUser);
    function populateTable(currentUser) {
      let table = document.getElementById("table");

      let tr = document.createElement("tr");
      table.appendChild(tr);

      let td = document.createElement("td");
      tr.appendChild(td);
      td.innerHTML = currentUser.userId;
      td = document.createElement("td");
      tr.appendChild(td);
      td.innerHTML = currentUser.username;
      td = document.createElement("td");
      tr.appendChild(td);
      td.innerHTML = currentUser.password;
      td = document.createElement("td");
      tr.appendChild(td);
      td.innerHTML = currentUser.firstName;
      td = document.createElement("td");
      tr.appendChild(td);
      td.innerHTML = currentUser.lastName;
      td = document.createElement("td");
      tr.appendChild(td);
      td.innerHTML = currentUser.email;
      td = document.createElement("td");
      tr.appendChild(td);
      td.innerHTML = currentUser.role.roleId;
      td.style = "display:none";
      td = document.createElement("td");
      tr.appendChild(td);
      td.innerHTML = currentUser.role.roleName;
    }
  }
}
function logout() {
  let xhr = new XMLHttpRequest();
  xhr.open("POST", "http://localhost:8080/project-1/logout");
  xhr.send();

  sessionStorage.removeItem("currentUser");
  window.location = "http://localhost:8080/project-1/";
}
