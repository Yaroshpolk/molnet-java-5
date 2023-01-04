let forms = document.querySelectorAll(".form");

forms.forEach(form => {
    let charInputs = form.querySelectorAll(".chars");
    let digitInputs = form.querySelectorAll(".digits");
    let digitCharInputs = form.querySelectorAll(".digitsAndChars");

    charInputs.forEach(input => {
        input.addEventListener('input', function () {
            this.value = this.value.replace(/[^a-zA-Zа-яА-Я\-]/g, '');
        });
    });

    digitInputs.forEach(input => {
        input.addEventListener('input', function () {
            this.value = this.value.replace(/[^0-9]/g, '');
        });
    });

    digitCharInputs.forEach(input => {
        input.addEventListener('input', function () {
            this.value = this.value.replace(/[^0-9a-zA-Zа-яА-Я]/g, '');
        });
    });

});