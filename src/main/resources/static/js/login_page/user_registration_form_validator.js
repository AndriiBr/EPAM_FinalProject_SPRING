const form = document.querySelector("form[name='registration_form']"),
    loginField = form.querySelector(".login"),
    loginInput = loginField.querySelector("input[name='username']"),

    emailField = form.querySelector(".email"),
    emailInput = emailField.querySelector("input[name='email']"),

    passwordField = form.querySelector(".password"),
    passwordInput = passwordField.querySelector("input[name='password']"),

    confirmPasswordField = form.querySelector(".password_confirm"),
    confirmPasswordInput = confirmPasswordField.querySelector("input[name='password_confirm']");



//If user entered any character in login field this event will be triggered
loginInput.onkeyup = () => {
    checkLogin();
}

//If user entered any character in email field this event will be triggered
emailInput.onkeyup = () => {
    checkEmail();
}

//If user entered any character in password field this event will be triggered
passwordInput.onkeyup = () => {
    checkPassword();
}

//If user entered any character in confirm password field this event will be triggered
confirmPasswordInput.onkeyup = () => {
    checkConfirmPassword();
}

form.onsubmit = (e) => {
    //prevent form from default submitting
    e.preventDefault();

    // If login is empty
    if (loginInput.value === "") {
        loginField.classList.add("shake", "error");
    } else {
        //Function to validate login
        checkLogin();
    }

    //If email is empty
    if (emailInput.value === "") {
        emailField.classList.add("shake", "error");
    } else {
        //Function to validate email
        checkEmail();
    }

    //If password is empty
    if (passwordInput.value === "") {
        passwordField.classList.add("shake", "error");
    } else {
        //Function to validate password
        checkPassword();
    }

    //If confirmed password is empty
    if (confirmPasswordInput.value === "") {
        confirmPasswordField.classList.add("shake", "error");
    } else {
        //Function to validate password
        checkConfirmPassword();
    }

    //remove shale class after 500ms delay
    setTimeout(() => {
        loginField.classList.remove("shake");
        emailField.classList.remove("shake");
        passwordField.classList.remove("shake");
        confirmPasswordField.classList.remove("shake");
    }, 500);


    //If error class not contain errors (user entered valid credentials) so we can submit a form
    if (!loginField.classList.contains("error")
        && !emailField.classList.contains("error")
        && !passwordField.classList.contains("error")
        && !confirmPasswordField.classList.contains("error")) {
        form.submit();
    }
}