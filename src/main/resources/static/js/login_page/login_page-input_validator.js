const form = document.querySelector("form[name='login_form']"),
    loginField = form.querySelector(".login"),
    loginInput = loginField.querySelector("input[name='username']"),
    passwordField = form.querySelector(".password"),
    passwordInput = passwordField.querySelector("input[name='password']");

//If user entered any character in login field this event will be triggered
loginInput.onkeyup = () => {
    checkLogin();
}

//If user entered any character in password field this event will be triggered
passwordInput.onkeyup = () => {
    checkPassword();
}

form.onsubmit = (e) => {
    //prevent form from default submitting
    e.preventDefault();
    //If login is empty
    if (loginInput.value === "") {
        loginField.classList.add("shake", "error");
    } else {
        //Function to validate login
        checkLogin();
    }

    //If password is empty
    if (passwordInput.value === "") {
        passwordField.classList.add("shake", "error");
    } else {
        //Function to validate password
        checkPassword();
    }

    //remove shale class after 500ms delay
    setTimeout(() => {
        loginField.classList.remove("shake");
        passwordField.classList.remove("shake");
    }, 500);

    //If error class not contain errors (user entered valid credentials) so we can submit a form
    if (!loginField.classList.contains("error") && !passwordField.classList.contains("error")) {
        form.submit();
    }
}
