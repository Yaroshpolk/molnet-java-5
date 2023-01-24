let popup = document.getElementById('employeePopup');

let closeBtn = document.getElementById('closePopupBtn');
let openBtn = document.getElementById('openPopupBtn');

let addForm = document.getElementById('addEmployeeForm');

let downloadExcelBtn = document.getElementById("downloadExcelBtn");
let filterForm = document.forms.namedItem("filterForm");

const openPopup = (evt) => {
    evt.preventDefault();
    addForm.reset();
    popup.classList.remove('popup_hidden');
}

const hidePopup = (evt) => {
    evt.preventDefault();
    popup.classList.add('popup_hidden');
    clearFormErrors(addForm)
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
    let invalidFields = form.querySelectorAll(".form__input_invalid");

    invalidFields.forEach(item => {
        item.classList.remove("form__input_invalid");
    })

    let errors = form.querySelectorAll(".form__error");
    errors.forEach(error => error.remove());
}

const checkInputValidity = (item) => {
    let itemValidity = true;

    if (item.required && !item.value) {
        item.parentElement.append(createErrorElem("Это поле обязательно к заполнению"))
        itemValidity = false;
    } else if (item.minLength > item.value.length && item.minLength !== -1) {
        item.parentElement.append(createErrorElem("Минимальная длинна " + item.minLength + " символов"))
        itemValidity = false;
    } else if (item.maxLength < item.value.length && item.maxLength !== -1) {
        item.parentElement.append(createErrorElem("Максимальная длинна " + item.maxLength + " символов"))
        itemValidity = false;
    }

    if (item.required && item.tagName === 'SELECT') {
        if (item.value === "0") {
            item.parentElement.append(createErrorElem("Это поле обязательно к заполнению"))
            itemValidity = false;
        }
    }

    if (itemValidity === false && item.tagName !== 'SELECT') {
        item.classList.add("form__input_invalid")
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

addForm.addEventListener('submit', formValidate);

downloadExcelBtn.addEventListener("click", evt => {
    evt.preventDefault();
    filterForm.action = downloadExcelBtn.href;
    filterForm.submit();
})

