# Тестовое задание в "ИТЦ МОЛНЕТ"
В данный момент выполнены задачи 4-6  
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
  http://localhost:8080/app/
```


Профиль сотрудника с id=1:

```
  http://localhost:8080/app/employee?id=1
```

Страница для выгрузки пользователей в Excel

```
  http://localhost:8080/app/downloadExcel
```
