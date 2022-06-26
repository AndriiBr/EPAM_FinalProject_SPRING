//Length from 4 to 19 symbols
//Start with character
const LOGIN_PATTERN = /^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\d._-]{4,19}$/;
//Minimum 8 symbols
//Minimum 1 uppercase and 1 lowercase letter
//Minimum 1 digit
const PASSWORD_PATTERN = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d\w\W]{8,}$/;
const EMAIL_PATTERN = /^[^ ]+@[^ ]+\.[a-z]{2,4}$/;

const [html] = document.getElementsByTagName("html")
const lang = html.getAttribute("lang");

function checkLogin() {
    //if user input not match with pattern
    if (!loginInput.value.match(LOGIN_PATTERN)) {
        loginField.classList.add("error");
        let errorText = loginField.querySelector(".error-text");

        //if login is not empty then show message to write a valid login.
        //Else show message that login can`t be blank
        if (loginInput.value !== "") {
            if (lang === 'ua') {
                errorText.innerText = "Ім'я користувача має починатись з літери та мати мінімум 5 символів"
            } else {
                errorText.innerText = "Login must begin with a symbol and contain minimum 5 characters"
            }

        } else {
            if (lang === 'ua') {
                errorText.innerText = "Ім'я користувача не може бути пустим";
            } else {
                errorText.innerText = "Login can`t be blank";
            }
        }
    } else {
        loginField.classList.remove("error");
    }
}

function checkEmail() {
    //if user input not match with pattern
    if (!emailInput.value.match(EMAIL_PATTERN)) {
        emailField.classList.add("error");
        let errorText = emailField.querySelector(".error-text");
        //if email is not empty then show message to write a valid email.
        //Else show message that email can`t be blank
        // (emailInput.value !== "")
        //     ? errorText.innerText = "Please write correct email address"
        //     : errorText.innerText = "Email can`t be blank";
        if (emailInput.value !== "") {
            if (lang === 'ua') {
                errorText.innerText = "Будь ласка, введіть коректну пошту"
            } else {
                errorText.innerText = "Please write correct email address"
            }
        } else {
            if (lang === 'ua') {
                errorText.innerText = "Пошта не може бути пустою";
            } else {
                errorText.innerText = "Email can`t be blank";
            }
        }
    } else {
        emailField.classList.remove("error");
    }
}

function checkPassword() {
    //if password is empty
    if (!passwordInput.value.match(PASSWORD_PATTERN)) {
        passwordField.classList.add("error");
        let errorText = passwordField.querySelector(".error-text");

        if (passwordInput.value !== "") {
            if (lang === 'ua') {
                errorText.innerText = "Пароль має містити мінімум 8 символів, мінім одну велику та маленьку літери, мінімум одне число"
            } else {
                errorText.innerText = "Password must contain minimum 8 symbols, minimum one uppercase and lowercase letter, minimum one number"
            }
        } else {
            if (lang === 'ua') {
                errorText.innerText = "Пароль не може бути пустим";
            } else {
                errorText.innerText = "Password can`t be blank";
            }
        }
    } else {
        passwordField.classList.remove("error");
    }
}

function checkConfirmPassword() {
    //if confirmed password not equal to password
    if (confirmPasswordInput.value !== passwordInput.value) {
        confirmPasswordField.classList.add("error");
        let errorText = confirmPasswordField.querySelector(".error-text");

        if (confirmPasswordInput.value !== "") {
            if (lang === 'ua') {
                errorText.innerText = "Паролі повинні співпадати"
            } else {
                errorText.innerText = "Passwords must be the same"
            }
        } else {
            if (lang === 'ua') {
                errorText.innerText = "Пароль не може бути пустим";
            } else {
                errorText.innerText = "Password can`t be blank";
            }
        }
    } else {
        confirmPasswordField.classList.remove("error");
    }
}

