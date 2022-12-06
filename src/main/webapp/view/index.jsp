<%@ page import="com.yaroshevich.app.model.Employee" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% List<Employee> employees = (List<Employee>) request.getAttribute("employees"); %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Тестовое задание № 6</title>
    <link rel="stylesheet" href="../styles/main.css">
</head>
<body>
<div class="content">
    <div class="employees">
        <div class="employees__header">
            <p class="employees__title">Список сотрудников</p>
            <a class="employees__btn" href="/app/downloadExcel" target="_blank">Скачать excel файл</a>
        </div>
        <ul class="employees__list">

            <% for (Employee employee : employees) { %>
            <li class='employees__item'>
                <a href=<%= "/app/employee?id=" + employee.getId() %> class='employees__link'>
                    <%= employee.getSecondName() %>
                    <%= employee.getFirstName() %>
                    <%= employee.getPatronymic() %>
                </a>
            </li>
            <% } %>

        </ul>
        <div class="employees__footer">
            <a class="employees__btn" href="/app/charts" target="_self">Графики</a>
        </div>
    </div>
</div>
</body>
</html>
