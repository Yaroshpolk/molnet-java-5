<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <link rel="stylesheet" href="../../styles/main.css">
</head>
<body>
<div class="content">
    <div class="auth">
        <form action="/registration" class="auth__form form" id="registrationForm" method="post" novalidate>
            <h2 class="form__title">Регистрация</h2>

            <div class="form__fields">
                <label class="form__field">
                    <p class="form__subtitle">Имя</p>
                    <input type="text" class="form__input" placeholder=""
                           name="user_name" maxlength="30" minlength="4" required>
                </label>

                <label class="form__field">
                    <p class="form__subtitle">Логин</p>
                    <input type="text" class="form__input" placeholder=""
                           name="user_login" maxlength="30" minlength="4" required>
                </label>

                <label class="form__field">
                    <p class="form__subtitle">Пароль</p>
                    <input type="password" class="form__input" placeholder=""
                           name="user_password" maxlength="30" minlength="5" required>
                </label>
            </div>

            <div class="error <%= request.getAttribute("error") == null ? "error_hidden" : "" %>">
                <p class="error__message">Ошибка: Пользователь с указанным логином уже существует</p>
            </div>

            <div class="auth__form-footer">
                <input type="submit" value="Зарегистрироваться" class="btn btn_blue">
                <a href="/login" class="link">Авторизация</a>
            </div>
        </form>
    </div>
</div>

<script src="../../scripts/authForms.js"></script>
</body>
</html>
