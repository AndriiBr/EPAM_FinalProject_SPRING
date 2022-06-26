//Length from 4 to 50 symbols
//Start with character
const TITLE_PATTERN_EN = /^[A-Za-z0-9_ -|&?:]{2,50}$/;
const TITLE_PATTERN_UA = /^[a-zA-Zа-яА-ЯіІйЙёЁ0-9 -]{2,50}$/;
//Any symbols but at least 10
const TEXT_PATTERN = /.{10,}/;

//Only positive digits
//Cannot start with 0
const PRICE_PATTERN = /^[1-9][\d]*$/;

const [html] = document.getElementsByTagName("html")
const lang = html.getAttribute("lang");

function checkTitle(input, field) {
    //if user input not match with pattern
    if (!input.value.match(TITLE_PATTERN_EN) && !input.value.match(TITLE_PATTERN_UA)) {
        field.classList.add("error");
        let errorText = field.querySelector(".error-text");
        //if title is not empty then show message to write a valid title.
        //Else show message that title can`t be blank
        if (input.value !== "") {
            if (lang === 'ua') {
                errorText.innerText = "Назва має починатись з символа та містити мінімум 2 символи"
            } else {
                errorText.innerText = "Title must begin with a symbol and contain minimum 2 characters"
            }
        } else {
            if (lang === 'ua') {
                errorText.innerText = "Назва не може бути пустою";
            } else {
                errorText.innerText = "Title can`t be blank";
            }
        }
    } else {
        field.classList.remove("error");
    }
}

function checkText(input, field) {
    //if user input not match with pattern
    if (!input.value.match(TEXT_PATTERN)) {
        field.classList.add("error");
        let errorText = field.querySelector(".error-text");
        //if title is not empty then show message to write a valid title.
        //Else show message that title can`t be blank
        if (input.value !== "") {
            if (lang === 'ua') {
                errorText.innerText = "Напишіть мінімум 10 символів"
            } else {
                errorText.innerText = "Write at least 10 characters"
            }
        } else {
            if (lang === 'ua') {
                errorText.innerText = "Опис не може бути пустим";
            } else {
                errorText.innerText = "Text can`t be blank";
            }
        }
    } else {
        field.classList.remove("error");
    }
}

function checkPrice() {
    //if price is empty
    if (!priceInput.value.match(PRICE_PATTERN)) {
        priceField.classList.add("error");
        let errorText = priceField.querySelector(".error-text");
        (priceInput.value !== "")
            ? errorText.innerText = "Price should be a positive number"
            : errorText.innerText = "Price can`t be blank";
    } else {
        priceField.classList.remove("error");
    }
}

function checkGenre() {
    //if price is empty
    if (genreInput.value === "Genre" || genreInput.value === "Жанр") {
        genreField.classList.add("error");
    } else {
        genreField.classList.remove("error");
    }
}


