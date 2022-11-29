<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style.css">
    <title>Ошибка на сервере</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/styles/error.css" %>
</style>
<div class="content">
    <h1 class="title">
        500
    </h1>
    <p class="subtitle">
        О нет! На сервере возникла ошибка!
    </p>
    <a href="/app" class="link">На главную</a>
</div>

</body>
</html>

