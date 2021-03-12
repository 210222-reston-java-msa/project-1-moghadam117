
let welcome = document.getElementById("welcome");


let userString = sessionStorage.getItem("currentUser");

if (userString === null) {
  window.location = "http://localhost:8080/project-1/";
} else {
  let currentUser = JSON.parse(userString);

  console.log(sessionStorage.getItem("currentUser"));
  if (currentUser != null) {
    welcome.innerHTML =
      "Welcome " + currentUser.firstName + " " + currentUser.lastName + " ID NUMBER " + currentUser.userId;
  }
}

function logout() {
  let xhr = new XMLHttpRequest();
  xhr.open("POST", "http://localhost:8080/project-1/logout");
  xhr.send();

  sessionStorage.removeItem("currentUser");
  window.location = "http://localhost:8080/project-1/";
}
function profile(){
window.location = "profile-emp.html";
}
function expense() {
  window.location = "expense-emp.html";
}
