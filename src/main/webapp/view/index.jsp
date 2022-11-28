<%@ page import="java.util.ArrayList" %>
<%@ page import="com.yaroshevich.app.model.Employee" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Тестовое задание № 5</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/styles/main.css" %>
</style>
<div class="content">
    <div class="employees">
        <p class="employees__title">Список сотрудников</p>
        <ul class="employees__list">
            <%
                for (Employee employee : (ArrayList<Employee>) request.getAttribute("employees")) {
                    out.println(
                            "<li class='employees__item'>"
                                    + "<a href='/employee?id=" + employee.getId() + "' class='employees__link'>"
                                    + employee.getSecondName() + " "
                                    + employee.getFirstName() + " "
                                    + employee.getPatronymic() + "</a>" +
                                    "</li>");
                }
            %>
        </ul>
    </div>
</div>

</body>
</html>
