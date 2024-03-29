<%@ page import="com.yaroshevich.app.model.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="com.yaroshevich.app.model.District" %>
<%@ page import="com.yaroshevich.app.model.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% List<Employee> employees = (List<Employee>) request.getAttribute("employees"); %>
<% List<District> districts = (List<District>) request.getAttribute("districts"); %>
<% List<District> regions = (List<District>) request.getAttribute("regions"); %>
<% session.setAttribute("employees", employees); %>
<% int rowsCount = (int) session.getAttribute("rowsCount"); %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Тестовое задание № 7</title>
    <link rel="stylesheet" href="../../styles/main.css">
</head>
<body>
<div class="content">

    <div class="sidebar">

        <form class="form" id="filterForm" action="/app/main/filter" name="filterForm">

            <h2 class="sidebar__title">Фильтр записей</h2>

            <div class="form__fields">

                <div class="district_selects">
                    <label class="form__field">
                        <p class="form__subtitle">Округ</p>
                        <select class="form__select form__input s_input" name="filter_district">
                            <option value="0">-----</option>
                            <% for (District district : districts) { %>

                            <option value="<%= district.getId() %>">
                                <%= district.getName() %>
                            </option>

                            <% } %>
                        </select>
                    </label>

                    <label class="form__field">
                        <p class="form__subtitle">Район</p>
                        <select class="form__select form__input r_input" name="filter_region">
                            <option value="0">-----</option>
                        </select>
                    </label>
                </div>

                <label class="form__field">
                    <p class="form__subtitle">Тип сортировки</p>
                    <select class="form__select form__input t_input" name="filter_sortType">
                        <option value="0">-----</option>
                        <option value="1">алфавитный по имени</option>
                        <option value="2">алфавитный по округу</option>
                        <option value="3">алфавитный по району</option>
                        <option value="4">по возрасту</option>
                    </select>
                </label>

                <input type="submit" class="btn btn_blue" value="Поиск">
            </div>
        </form>

        <div class="user">
            <p class="user__name"><%= request.getSession().getAttribute("user") != null ?
                    ((User) request.getSession().getAttribute("user")).getName() : "" %>
            </p>
            <a href="/logout" class="user__link">Выйти из учётной записи</a>
        </div>
    </div>

    <div class="data">
        <div class="search">
            <label class="form__field search__field">
                <input type="text" class="form__input search-input" name="search_field"
                       placeholder="Найти сотрудника по ФИО"
                       form="filterForm">
                <button class="btn btn_blue" type="submit" form="filterForm">Найти</button>
            </label>
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
                            <%= employee.getAddress().getDistrict().getParent().getName() %>
                        </p>
                    </a>
                </li>
                <% } %>

            </ul>
            <div class="pagination">
                <ul class="pagination__list">
                    <% for (int i = 0; i <= Math.floor(rowsCount / 10); i++) { %>
                    <li class="pagination__item">
                        <a class="pagination__btn btn btn_blue<%= (int) request.getAttribute("page") == i ?
                        " pagination__btn_curr" : ""%>"
                                <%
                                    String newUrl = "";
                                    if (request.getQueryString() != null) {
                                        if (request.getQueryString().contains("page")) {
                                            int index = request.getQueryString().indexOf("page");
                                            newUrl = request.getQueryString().substring(0, index);
                                        } else {
                                            newUrl = request.getQueryString();
                                        }
                                    }
                                %>
                           href="/app/main?<%= newUrl +
                           (request.getParameterMap().size() - 1 > 0 ? "&page=" + i : "page=" + i)%>">
                            <%= i + 1 %>
                        </a>
                    </li>
                    <% } %>
                </ul>
            </div>
            <div class="employees__footer">
                <a class="btn btn_blue" href="/app/charts" target="_self">Графики</a>
                <a class="btn btn_blue" href="/app/downloadExcel" target="_blank" id="downloadExcelBtn">Скачать excel файл</a>
            </div>
        </div>
    </div>
</div>

<div class="popup popup_hidden" id="employeePopup">
    <div class="popup__wrapper">

        <div class="popup__header">
            <button class="btn btn_blue" id="closePopupBtn">X</button>
        </div>

        <form class="form" action="/app/employee/add" name="addEmployeeForm" id="addEmployeeForm" method="post"
              novalidate>

            <h2 class="form__title">Добавление нового сотрудника</h2>

            <div class="form__fields">
                <label class="form__field">
                    <p class="form__subtitle">Имя</p>
                    <input type="text" class="form__input chars" placeholder=""
                           name="employee_firstName" maxlength="20" minlength="2" required>
                </label>

                <label class="form__field">
                    <p class="form__subtitle">Фамилия</p>
                    <input type="text" class="form__input chars" placeholder=""
                           name="employee_lastName" maxlength="30" minlength="2" required>
                </label>

                <label class="form__field">
                    <p class="form__subtitle">Отчество</p>
                    <input type="text" class="form__input chars" placeholder=""
                           name="employee_patronymic" maxlength="20" minlength="2" required>
                </label>

                <label class="form__field">
                    <p class="form__subtitle">Возраст</p>
                    <input type="number" class="form__input digits" placeholder=""
                           name="employee_age">
                </label>
            </div>

            <div class="form__fields">
                <label class="form__field">
                    <p class="form__subtitle">Адрес</p>
                    <input type="text" class="form__input" placeholder=""
                           name="employee_address" minlength="5" maxlength="120" required>
                </label>

                <div class="district_selects">
                    <label class="form__field">
                        <p class="form__subtitle">Округ</p>
                        <select class="form__select form__input s_input" name="employee_district" required>
                            <option value="0">-----</option>
                            <% for (District district : districts) { %>

                            <option value="<%= district.getId() %>">
                                <%= district.getName() %>
                            </option>

                            <% } %>
                        </select>
                    </label>

                    <label class="form__field">
                        <p class="form__subtitle">Район</p>
                        <select class="form__select form__input r_input" name="employee_region" required>
                            <option value="0">-----</option>
                        </select>
                    </label>
                </div>
            </div>

            <div class="form__fields shifts">
                <label class="form__field">
                    <p class="form__subtitle">Начало рабочего дня</p>
                    <input type="time" class="form__input" placeholder="00:00:00" name="employee_start" minlength="0"
                           maxlength="8">
                </label>
                <label class="form__field">
                    <p class="form__subtitle">Конец рабочего дня</p>
                    <input type="time" class="form__input" placeholder="23:59:59" name="employee_end" minlength="0"
                           maxlength="8">
                </label>
            </div>
            <div class="popup__footer">
                <button class="btn btn_green" id="addButton">Добавить</button>
            </div>
        </form>
    </div>
</div>

<script src="../../scripts/script.js"></script>
<script src="../../scripts/inputsValidation.js"></script>

<script>
    let selects = document.querySelectorAll(".district_selects");
    let regions = [
        <% for (District region : regions){ %>
        {"id": <%= region.getId() %>, "name": "<%= region.getName() %>", "parentId": <%= region.getParent().getId() %>},
        <% } %>
    ];

    const handleDistrictSelectChange = (evt) => {
        let val = parseInt(evt.target.value);
        let input = evt.target.closest(".district_selects").querySelector(".r_input");
        input.value = 0;

        input.querySelectorAll("option").forEach((item) => item.remove());
        let curRegions = regions.filter(item => item.parentId === val);

        input.add(new Option("-----", "0"));

        curRegions.forEach(item => {
            input.add(new Option(item.name, item.id));
        });

    }

    selects.forEach(item => {
        let selectS = item.getElementsByClassName("s_input")[0];

        selectS.addEventListener("change", handleDistrictSelectChange);
    })

    // Заполнение селектов сортировки после обновления страницы
    <% if (request.getParameter("filter_district") != null) {%>
    let dInput = selects[0].getElementsByClassName("s_input")[0];

    dInput.value = <%= request.getParameter("filter_district") %>;
    dInput.dispatchEvent(new Event("change"));

    <% if (request.getParameter("filter_region") != null) {%>
    selects[0].getElementsByClassName("r_input")[0].value = <%= request.getParameter("filter_region") %>;
    <% } %>
    <% } %>

    <% if (request.getParameter("filter_sortType") != null) {%>
    document.querySelector(".sidebar").querySelector(".t_input").value =
    <%= request.getParameter("filter_sortType") %>;
    <% } %>

    <% if (request.getParameter("search_field") != null) {%>
    document.querySelector(".search").querySelector(".search-input").value =
        "<%= request.getParameter("search_field") %>";
    <% } %>

</script>
</body>
</html>
