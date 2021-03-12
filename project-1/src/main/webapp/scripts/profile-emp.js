
let welcome = document.getElementById("welcome");


var userString = sessionStorage.getItem("currentUser");

if (userString === null) {
  window.location = "http://localhost:8080/project-1/";
} else {
  var currentUser = JSON.parse(userString);

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
function update() {
  let fname = document.getElementById("fname").value;
  let lname = document.getElementById("lname").value;
  let mail = document.getElementById("mail").value;
  let uName = document.getElementById("uName").value;

  let pWord = document.getElementById("pWord").value;

  let updateTemplate = {
    username: uName,
    password: pWord,
    firstname: fname,
    lastname: lname,
    email: mail,
  };

  let xhr = new XMLHttpRequest();

  xhr.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 200) {
      

      fname.innerHTML = currentUser.firstname;
      lname.innerHTML = currentUser.lastname;
      uName.innerHTML = currentUser.username;
      pWord.innerHTML = currentUser.password;
      mail.innerHTML = currentUser.email;
      let childDiv1 = document.getElementById("approvedText");
      childDiv1.textContent = "Profile Updated";
      sessionStorage.setItem('currentUser', this.responseText);
      alert("Your Profile Has been Updated");
      window.location = "http://localhost:8080/project-1/login-emp.html";
    }

    if (this.readyState === 4 && this.status === 204) {
      let childDiv = document.getElementById("warningText");
      childDiv.textContent = "Invalid fields";
    }
  };

  xhr.open("POST", "http://localhost:8080/project-1/profile-emp");

  xhr.send(JSON.stringify(updateTemplate));
}
