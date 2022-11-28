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
    <%@include file="/WEB-INF/styles/profile.css" %>
</style>
<% Employee employee = (Employee) request.getAttribute("employee"); %>
<div class="content">
    <div class="profile">
        <div class="profile__avatar">
            <p class="profile__avatar-txt">Фото</p>
        </div>
        <div class="profile__info">
            <p class="profile__name"><% out.println(employee.getSecondName() + " " + employee.getFirstName()
                    + " " + employee.getPatronymic());%></p>
            <ul class="profile__list">
                <li class="profile__item">Адрес: <% out.println(employee.getAddress().getAddress() + ", "
                        + employee.getAddress().getDistrict() + ", " + employee.getAddress().getRegion());%></li>
                <li class="profile__item">Режим работы: <% out.println(employee.getShift().getStart() + " - "
                        + employee.getShift().getEnd());%></li>
            </ul>
        </div>
    </div>
</div>

</body>
</html>
