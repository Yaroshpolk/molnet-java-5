# Тестовое задание в "ИТЦ МОЛНЕТ"
В данный момент выполнены задачи 4-7 
Дамп БД : [ссылка](src/main/resources)

## Стек технологий
* PostgreSQL 15
* Java 8
* JDBC
* Jakarta EE
* GlassFish 6
* Apache POI 5.2.3


## Endpoints
Ссылки указаны для локального сервера, порт 8080

Главная страница приложения:

```
  http://localhost:8080/app/main
```


Профиль сотрудника с id=1:

```
  http://localhost:8080/app/employee?id=1
```

Страница для выгрузки пользователей в Excel

```
  http://localhost:8080/app/downloadExcel
```

Страница c графиками

```
  http://localhost:8080/app/charts
```

Выход из учётной записи

```
  http://localhost:8080/logout
```

Страница авторизации

```
  http://localhost:8080/login
```

Страница регистрации

```
  http://localhost:8080/registration
```