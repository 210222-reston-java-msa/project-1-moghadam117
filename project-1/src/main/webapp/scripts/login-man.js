function sendLogin() {
  let uName = document.getElementById("uName").value;

  let pWord = document.getElementById("pWord").value;

  console.log(`Username: ${uName}`);
  console.log(`Password: ${pWord}`);

  let loginTemplate = {
    username: uName,
    password: pWord,
  };

  let xhr = new XMLHttpRequest();

  xhr.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 200) {
      sessionStorage.setItem("currentUser", this.responseText);

      window.location = "http://localhost:8080/project-1/home-man.html";
    }

    if (this.readyState === 4 && this.status === 204) {
      let childDiv = document.getElementById("warningText");
      childDiv.textContent =
        "Failed to login!  Username or Password is incorrect";
    }
  };

  xhr.open("POST", "http://localhost:8080/project-1/login-man");

  xhr.send(JSON.stringify(loginTemplate));
}
