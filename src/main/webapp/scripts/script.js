let popup = document.getElementById('employeePopup');
let form = document.getElementById('addEmployeeForm');

let closeBtn = document.getElementById('closePopupBtn');
let openBtn = document.getElementById('openPopupBtn')

closeBtn.addEventListener('click', (evt) => {
    evt.preventDefault();
    popup.classList.add('popup_hidden');
});

openBtn.addEventListener('click', (evt) => {
    evt.preventDefault();
    popup.classList.remove('popup_hidden');
    form.reset();
});