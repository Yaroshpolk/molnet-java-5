<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Авторизация</title>
    <link rel="stylesheet" href="../../styles/main.css">
</head>
<body>
  <div class="content">
      <div class="auth">
          <form action="/login" class="auth__form form" id="loginForm" method="post" novalidate>
              <h2 class="form__title">Авторизация</h2>

              <div class="form__fields">
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
                  <p class="error__message">Ошибка: Неверное имя пользователя или пароль</p>
              </div>

              <div class="auth__form-footer">
                  <input type="submit" value="Войти" class="btn btn_blue">
                  <a href="/registration" class="link">Зарегистрироваться</a>
              </div>
          </form>
      </div>
  </div>
  <script src="../../scripts/authForms.js"></script>
</body>
</html>
