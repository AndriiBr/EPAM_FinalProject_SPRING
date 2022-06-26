const form = document.querySelector("form[name='balance_form']"),
    moneyField = form.querySelector(".money"),
    moneyInput = moneyField.querySelector("input[name='money']");

//If user entered any character in login field this event will be triggered
moneyInput.onkeyup = () => {
    checkMoney();
}

form.onsubmit = (e) => {
    //prevent form from default submitting
    e.preventDefault();
    //If money value is empty
    if (moneyInput.value === "") {
        moneyField.classList.add("shake", "error");
    } else {
        //Function to validate money value
        checkMoney();
    }


    //remove shale class after 500ms delay
    setTimeout(() => {
        moneyField.classList.remove("shake");
    }, 500);

    //If error class not contain errors (user entered valid credentials) so we can submit a form
    if (!moneyField.classList.contains("error")) {
        form.submit();
    }
}

