let popup = document.getElementById('employeePopup');

let closeBtn = document.getElementById('closePopupBtn');
let openBtn = document.getElementById('openPopupBtn');

let addForm = document.getElementById('addEmployeeForm');
let addButton = document.getElementById('addButton');

closeBtn.addEventListener('click', (evt) => {
    evt.preventDefault();
    popup.classList.add('popup_hidden');
});

openBtn.addEventListener('click', (evt) => {
    evt.preventDefault();
    popup.classList.remove('popup_hidden');
    addForm.reset();
});

const formValidate = (form) => {

    let inputs = Array.from(form.getElementsByTagName('input'));

    console.log(inputs)
}