# <img src="media/images/rest_assured.png" width="20" height="20"> Автотесты с библиотекой REST assured

Проект содержит автотесты для проверки REST API эндпоинта Selenoid. Тесты разработаны с использованием 
REST assured - популярной Java-библиотеки для тестирования REST API

### Request URL
```
https://selenoid.autotests.cloud/wd/hub/status
```
### Response Body
```
{
    "value": {
        "message": "Selenoid 1.11.3 built at 2024-05-25_12:34:40PM",
        "ready": true
    }
}
```

### Основные позитивные и негативные проверки 

- Валидация HTTP статус-кода (200 OK)
- Проверка структуры JSON-ответа
- Проверка строкового значения в параметре message
- Проверка логического поля в JSON
- Проверки авторизации с некорректными учетными данными
## Пример проверки в тестах
```
given()
    .baseUri("https://selenoid.autotests.cloud")
    .log().uri()
.when()
    .get("/wd/hub/status")
.then()
    .log().all()
    .statusCode(200)
    .body("value.message", containsString("Selenoid 1.11.3 built at 2024-05-25_12:34:40PM"));
```
Источники: 
- [Официальная документация REST Assured](https://github.com/rest-assured/rest-assured/wiki/Usage)
- [Лекции QA.GURU](https://qa.guru/about)
