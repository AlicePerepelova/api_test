<h1 >Проект по автоматизации тестирования API для сайта 
<a href="https://restful-booker.herokuapp.com/">restful-booker</a></h1>


## :page_with_curl: Содержание

* <a href="#tools">Технологии и инструменты</a>

* <a href="#cases">Реализованные проверки</a>

* <a href="#console">Запуск тестов из терминала</a>

* <a href="#jenkins">Запуск тестов в Jenkins</a>

* <a href="#allure">Allure Report отчеты</a>

* <a href="#allure"> Интеграция с Allure TestOps</a>

* <a href="#jira"> Интеграция с Jira</a>

* <a href="#telegram">Уведомления в Telegram</a>


<a id="tools"></a>
## Технологии и инструменты

| Java                                                                                                    | IntelliJ Idea                                                                                                                | Allure                                                                                                                    | Allure TestOps                                                                                                      | GitHub                                                                                                    | JUnit 5                                                                                                           | Gradle                                                                                                   | REST Assured                                                                                                   |  Jenkins                                                                                                           | Jira                                                                                                                         |
|:--------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------:|------------------------------------------------------------------------------------------------------------------------------|
| <a href="https://www.java.com/"><img src="media/logo/Java.svg" width="50" height="50"  alt="Java"/></a> | <a id ="tech" href="https://www.jetbrains.com/idea/"><img src="media/logo/Idea.svg" width="50" height="50"  alt="IDEA"/></a> | <a href="https://github.com/allure-framework"><img src="media/logo/Allure.svg" width="50" height="50"  alt="Allure"/></a> | <a href="https://qameta.io/"><img src="media/logo/allureTestOps.svg" width="50" height="50"  alt="Allure TestOps"/></a> | <a href="https://github.com/"><img src="media/logo/GitHub.svg" width="50" height="50"  alt="Github"/></a> | <a href="https://junit.org/junit5/"><img src="media/logo/Junit5.svg" width="50" height="50"  alt="JUnit 5"/></a> | <a href="https://gradle.org/"><img src="media/logo/Gradle.svg" width="50" height="50"  alt="Gradle"/></a> | <a href="https://rest-assured.io/"><img src="media/logo/RestAssured.svg" width="50" height="50"  alt="RestAssured"/></a>  |   <a href="https://www.jenkins.io/"><img src="media/logo/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a> | <a href="https://www.atlassian.com/ru/software/jira"><img src="media/logo/Jira.svg" width="50" height="50"  alt="Jira"/></a> |


<a id="cases"></a>
## :heavy_check_mark: Реализованные проверки

-  Успешная авторизация пользователя
-  Создание записи о бронировании
-  Удаление записи о бронировании
-  Получение списка бронирования
-  Поиск бронирования по ID
-  Обновление информации о бронировании


<a id="console"></a>
##  Запуск тестов из терминала
### Локальный запуск тестов

```
gradle clean test  
```

<a id="jenkins"></a>
## <img src="media/logo/Jenkins.svg" width="25" height="25"/></a> Запуск тестов в [Jenkins](https://jenkins.autotests.cloud/job/alice-lilo-032-diplom_api/)

<p align="center">

> Для запуска необходимо нажать "Собрать сейчас".

<a href="https://jenkins.autotests.cloud/job/alice-lilo-032-diplom_api/"><img src="media/screenshots/jenkins.png" alt="Jenkins"/></a>

> При клике на сборку после завершения можно увидеть артефакты запуска и полезные ссылки для более детального изучения прогона.

<a href="https://jenkins.autotests.cloud/job/alice-lilo-032-diplom_api/"><img src="media/screenshots/sborka.png" alt="Jenkins"/></a>
</p>

<a id="allure"></a>
## <img src="media/logo/Allure.svg" width="25" height="25"/></a> [Allure Report](https://jenkins.autotests.cloud/job/alice-lilo-032-diplom_api/10/allure/) отчеты

### Основное окно

<p align="center">
<img title="Allure Dashboard" src="media/screenshots/dashboard.png">
</p>

### Отчеты по тестам

<p align="center">

> В отчете по тестам присутствует развернутая информация по запросам и ответам.

<img title="Allure Tests" src="media/screenshots/testcase.png">
</p>

<a id="allure"></a>
## <img width="30" style="vertical-align:middle" title="Allure TestOps" src="media/logo/allureTestOps.svg"> </a> Интеграция с <a target="_blank" href="https://allure.autotests.cloud/project/4693/dashboards"> Allure TestOps </a>
На *Dashboard* в <code>Allure TestOps</code> видна статистика количества тестов. Новые тесты, а так же результаты прогона приходят по интеграции при каждом запуске сборки.
<p align="center">
<img title="Allure Overview" src="media/screenshots/allureTestOps.png">
</p>

____
<a id="jira"></a>
## <img width="30" style="vertical-align:middle" title="Jira" src="media/logo/Jira.svg"> </a> Интеграция с <a target="_blank" href="https://jira.autotests.cloud/browse/HOMEWORK-1432"> Jira </a>
Реализована интеграция <code>Allure TestOps</code> с <code>Jira</code>, в задаче отображается, какие тест-кейсы были написаны в рамках задачи и результат их прогона.
<p align="center">
<img title="Jira Task" src="media/screenshots/jira.png">
</p>

<p >


<a id="telegram"></a>
## <img src="media/logo/Telegram.svg" width="25" height="25"/> </a> Уведомления в Telegram
> С помощью настроенного бота после завершения прогона в Jenkins поступают уведомления в Telegram.

<img title="telegram bot" src="media/screenshots/tele.png">
</p>
