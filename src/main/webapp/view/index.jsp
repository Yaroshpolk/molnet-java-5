<%@ page import="com.yaroshevich.app.model.Employee" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Тестовое задание № 5</title>
    <link rel="stylesheet" href="../styles/main.css">
</head>
<body>
<div class="content">
    <div class="employees">
        <div class="employees__header">
            <p class="employees__title">Список сотрудников</p>
            <button class="employees__btn" id="excelBtn">Скачать Excel файл</button>
        </div>
        <ul class="employees__list">

            <% for (Employee employee : (List<Employee>) request.getAttribute("employees")) { %>
            <li class='employees__item'>
                <a href=<%= "/app/employee?id=" + employee.getId() %> class='employees__link'>
                    <%= employee.getSecondName() %>
                    <%= employee.getFirstName() %>
                    <%= employee.getPatronymic() %>
                </a>
            </li>
            <% } %>

        </ul>
    </div>
</div>
<script src="../scripts/script.js"></script>
</body>
</html>
