<%@ page import="com.yaroshevich.app.model.Employee" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Профиль сотрудника</title>
</head>
<body>
<style>
    <%@include file="/styles/main.css" %>
</style>
<%
    Employee employee = (Employee) request.getAttribute("employee");

    String name = employee.getSecondName() + " " + employee.getFirstName() + " " + employee.getPatronymic();
    String address = employee.getAddress().getAddress() + ", "
            + employee.getAddress().getDistrict() + ", " + employee.getAddress().getRegion();
    String shift = employee.getShift().getStart() + " - " + employee.getShift().getEnd();
%>
<div class="content">
    <div class="profile">
        <div class="profile__avatar">
            <p class="profile__avatar-txt">Фото</p>
        </div>
        <div class="profile__info">
            <p class="profile__name"><%= name %>
            </p>
            <ul class="profile__list">
                <li class="profile__item">Адрес: <%= address %>
                </li>
                <li class="profile__item">Режим работы: <%= shift %>
                </li>
            </ul>
        </div>
    </div>
</div>

</body>
</html>
