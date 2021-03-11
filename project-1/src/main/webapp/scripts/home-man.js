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
function reimbursement() {
  window.location = "reimbursement-man.html";
}
function assigned() {
  window.location = "assigned-man.html";
}
