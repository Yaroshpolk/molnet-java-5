let popup = document.getElementById('employeePopup');

let closeBtn = document.getElementById('closePopupBtn');
let openBtn = document.getElementById('openPopupBtn');

let addForm = document.getElementById('addEmployeeForm');
let addButton = document.getElementById('addButton');

const openPopup = (evt) => {
    evt.preventDefault();
    popup.classList.remove('popup_hidden');
    addForm.reset();
}

const hidePopup = (evt) => {
    evt.preventDefault();
    popup.classList.add('popup_hidden');
}

closeBtn.addEventListener('click', hidePopup);
openBtn.addEventListener('click', openPopup);

const createErrorElem = (txt) => {
    let elem = document.createElement("span");
    elem.className = "form__error";
    elem.textContent = txt;

    return elem;
}

const clearFormErrors = (form) => {
    let errors = form.querySelectorAll(".form__error");
    errors.forEach(error => error.remove());
}

const checkInputValidity = (item) => {
    if (item.required && !item.value) {
        item.parentElement.append(createErrorElem("Это поле обязательно к заполнению"))
    } else if (item.minLength > item.value.length && item.minLength !== -1) {
        item.parentElement.append(createErrorElem("Минимальная длинна текста " + item.minLength + " символа"))
    } else if (item.maxLength < item.value.length && item.maxLength !== -1) {
        item.parentElement.append(createErrorElem("Максимальная длинна текста " + item.maxLength + " символа"))
    }

    if (item.required && item.tagName === 'SELECT') {
        if (item.value === "0") {
            item.parentElement.append(createErrorElem("Это поле обязательно к заполнению"))
        }
    }
}

const formValidate = (evt) => {
    evt.preventDefault();

    let form = evt.target;
    let inputs = form.querySelectorAll(".form__input");

    clearFormErrors(form);

    inputs.forEach(item => {
        checkInputValidity(item);
    })

    if (form.querySelectorAll(".form__error").length === 0) {
        form.submit();
        popup.classList.add('popup_hidden');
    }
}

addForm.addEventListener('submit', formValidate)

