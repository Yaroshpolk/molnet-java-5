<%@ page import="com.yaroshevich.app.model.Employee" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Профиль сотрудника</title>
    <link rel="stylesheet" href="../styles/main.css">
</head>
<body>
<%
    Employee employee = (Employee) request.getAttribute("employee");

    String name = employee.getSecondName() + " " + employee.getFirstName() + " " + employee.getPatronymic();
    String address = employee.getAddress().getAddress() + ", "
            + employee.getAddress().getDistrict().getName() + ", " + employee.getAddress().getDistrict().getParent().getName();
    String shift = employee.getShift().getStart() == null ? "Не указан" :
            employee.getShift().getStart() + " - " + employee.getShift().getEnd();
%>
<div class="content prof">
    <div class="profile">
        <div class="controls">
            <a class="btn btn_blue back-btn" href="" target="_self">Назад</a>
        </div>
        <div class="profile__wrapper">
            <div class="profile__avatar">
                <p class="profile__avatar-txt">Фото</p>
            </div>
            <div class="profile__info">
                <p class="profile__name"><%= name %>
                </p>
                <ul class="profile__list">
                    <li class="profile__item">Адрес: <%= address %>
                    </li>
                    <li class="profile__item">График работы: <%= shift %>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<script>
    let backBtn = document.querySelector(".back-btn");

    backBtn.addEventListener("click", evt => {
        evt.preventDefault();
        history.back();
    })
</script>
</body>
</html>
