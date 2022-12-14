<%@ page import="com.yaroshevich.app.model.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="com.yaroshevich.app.model.Address" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% List<Employee> employees = (List<Employee>) request.getAttribute("employees"); %>
<%--<% List<Address> addresses = (List<Address>) request.getAttribute("addresses"); %>--%>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Тестовое задание № 7</title>
    <link rel="stylesheet" href="../styles/main.css">
</head>
<body>
<div class="content">

    <div class="sidebar">
        <button class="btn btn_blue switcher"> < </button>

        <h2 class="sidebar__title">Фильтр записей</h2>

        <form class="form" id="filterForm">
            <div class="form__fields">

                <label class="form__field">
                    <p class="form__subtitle">Округ</p>
                    <select class="form__select form__input" name="filter_district">
                        <option>-----</option>
<%--                        <% for (Address address : addresses) { %>--%>

<%--                        <option value="<%= address.getDistrict() %>">--%>
<%--                            <%= address.getDistrict() %>--%>
<%--                        </option>--%>

<%--                        <% } %>--%>
                    </select>
                </label>

                <label class="form__field">
                    <p class="form__subtitle">Район</p>
                    <select class="form__select form__input" name="filter_region">
                        <option>-----</option>
<%--                        <% for (Address address : addresses) { %>--%>

<%--                        <option value="<%= address.getRegion() %>">--%>
<%--                            <%= address.getRegion() %>--%>
<%--                        </option>--%>

<%--                        <% } %>--%>
                    </select>
                </label>

                <label class="form__field">
                    <p class="form__subtitle">Тип сортировки</p>
                    <select class="form__select form__input" name="filter_sortType">
                        <option>-----</option>
                        <option value="1">алфавитный по имени</option>
                        <option value="2">алфавитный по округу</option>
                        <option value="3">алфавитный по району</option>
                        <option value="4">по возрасту</option>
                    </select>
                </label>

                <input type="submit" class="btn btn_blue" value="Поиск">
            </div>
        </form>
    </div>

    <div class="data">
        <div class="search">
            <form class="form" id="searchForm">
                <label class="form__field search__field">
                    <input type="text" class="form__input" name="search-field" placeholder="Найти сотрудника по ФИО">
                    <button class="btn btn_blue" type="submit">Найти</button>
                </label>
            </form>
        </div>

        <div class="employees">
            <div class="employees__header">
                <p class="employees__title">Список сотрудников</p>
                <a class="btn_green btn" target="_blank" id="openPopupBtn">
                    Добавить
                </a>
            </div>
            <ul class="employees__list">

                <li class="employees__item employees__item_titles">
                    <p class="employees__field">ФИО</p>
                    <p class="employees__field field__age">Возраст</p>
                    <p class="employees__field">Район</p>
                    <p class="employees__field">Округ</p>
                </li>

                <% for (Employee employee : employees) { %>
                <li class='employees__item'>
                    <a href=<%= "/app/employee?id=" + employee.getId() %> class='employees__link'>
                        <p class="employees__field">
                            <%= employee.getSecondName() %>
                            <%= employee.getFirstName() %>
                            <%= employee.getPatronymic() %>
                        </p>
                        <p class="employees__field field__age">
                            <%= employee.getAge() %>
                        </p>
                        <p class="employees__field">
                            <%= employee.getAddress().getDistrict().getName() %>
                        </p>
                        <p class="employees__field">
                            <%= employee.getAddress().getDistrict().getParentName() %>
                        </p>
                    </a>
                </li>
                <% } %>

            </ul>
            <div class="employees__footer">
                <a class="btn btn_blue" href="/app/charts" target="_self">Графики</a>
                <a class="btn btn_blue" href="/app/downloadExcel" target="_blank">Скачать excel файл</a>
            </div>
        </div>
    </div>
</div>

<div class="popup popup_hidden" id="employeePopup">
    <div class="popup__wrapper">

        <div class="popup__header">
            <button class="btn btn_blue" id="closePopupBtn">X</button>
        </div>

        <form class="form" name="addEmployeeForm" id="addEmployeeForm">

            <h2 class="form__title">Добавление нового пользователя</h2>

            <div class="form__fields">
                <label class="form__field">
                    <p class="form__subtitle">Имя</p>
                    <input type="text" class="form__input" placeholder=""
                           name="employee_firstName" required>
                </label>

                <label class="form__field">
                    <p class="form__subtitle">Фамилия</p>
                    <input type="text" class="form__input" placeholder=""
                           name="employee_lastName" required>
                </label>

                <label class="form__field">
                    <p class="form__subtitle">Отчество</p>
                    <input type="text" class="form__input" placeholder=""
                           name="employee_patronymic" required>
                </label>

                <label class="form__field">
                    <p class="form__subtitle">Возраст</p>
                    <input type="text" class="form__input" placeholder=""
                           name="employee_age">
                </label>
            </div>

            <div class="form__fields">
                <label class="form__field">
                    <p class="form__subtitle">Адрес</p>
                    <input type="text" class="form__input" placeholder=""
                           name="employee_address" required>
                </label>

                <label class="form__field">
                    <p class="form__subtitle">Округ</p>
                    <select class="form__select form__input" name="employee_district">
                        <option>-----</option>
<%--                        <% for (Address address : addresses) { %>--%>

<%--                        <option value="<%= address.getDistrict() %>">--%>
<%--                            <%= address.getDistrict() %>--%>
<%--                        </option>--%>

<%--                        <% } %>--%>
                    </select>
                </label>

                <label class="form__field">
                    <p class="form__subtitle">Район</p>
                    <select class="form__select form__input" name="employee_region">
                        <option>-----</option>
<%--                        <% for (Address address : addresses) { %>--%>

<%--                        <option value="<%= address.getRegion() %>">--%>
<%--                            <%= address.getRegion() %>--%>
<%--                        </option>--%>

<%--                        <% } %>--%>
                    </select>
                </label>
            </div>

            <div class="form__fields shifts">
                <label class="form__field">
                    <p class="form__subtitle">Начало рабочего дня</p>
                    <input type="text" class="form__input" placeholder="00:00:00" name="employee_address" required>
                </label>
                <label class="form__field">
                    <p class="form__subtitle">Конец рабочего дня</p>
                    <input type="text" class="form__input" placeholder="23:59:59" name="employee_district" required>
                </label>
            </div>
        </form>

        <div class="popup__footer">
            <button class="btn btn_green">Добавить</button>
        </div>
    </div>
</div>

<script src="../scripts/script.js"></script>
</body>
</html>
