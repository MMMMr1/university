Предметная область: учебное заведение

Композиция:

Студент – карточка учета оценок по конкретному предмету (education process). Связь один ко многим

Преподаватель – карточка учета оценок конкретному предмету (education process). Связь один ко многим


Схема приложения (реализована по принципу звезды):

 ![image](https://github.com/MMMMr1/university/assets/95496893/b08fe1db-661a-41e1-95d4-d632aa8d10c1)



Используемые технологии


•	Java 17;

•	Spring Boot (MVC, Data JPA, Validation);

•	Hibernate;

•	PostgreSQL;

•	Lombok;

•	Liquibase

•	Swagger

•	Maven.


Описание зависимостей объекта


spring-boot-starter-data-jpa -  механизм для взаимодействия с сущностями базы данных, организации их в репозитории и работы с ними по спецификации JPA, в качестве ORM выступает Hibernate;
 
postgresql  -  драйвер JDBC для подключения к PostrgeSQL Connection pool - HikariCP;
 
lombok - библиотека для сокращения кода в классах и расширения функциональности языка Java

spring-boot-starter-web – фреймворк для создания RESTful веб-приложений.

spring-boot-starter-validation – механизм, обеспечивающий валидацию входных данных от пользователя и данных, передаваемых от контроллера к сервису, посредством аннотаций

liquibase-core - система управления версиями базы данных

springdoc-openapi-starter-webmvc-ui , springdoc-openapi-ui  - инструмент, который позволяет автоматически описывать API на основе его кода



Endpoints:

<img width="535" alt="image" src="https://github.com/MMMMr1/university/assets/95496893/a045ebb9-dad8-40ad-9ba2-1772e0ecd672">

<img width="538" alt="image" src="https://github.com/MMMMr1/university/assets/95496893/304ad27a-01d8-4a9d-a6b5-625fa2ac4b56">


Порядок сборки проекта


1.	создать папку на рабочем компьютере, где будет размещен проект;
2.	войти в эту папку через командную строку и выполнить команду git clone https://github.com/MMMMr1/university.git; 
3.	установить PostgreSQL ( https://www.postgresql.org/download/ )
4.	установить pgAdmin (https://www.pgadmin.org/download/ )
5.	установить Docker(https://www.docker.com/get-started/)
6.	создать контейнер для БД: папканавашемкомпьютере\Docker>docker run --name university -e POSTGRES_PASSWORD=pass -p 5434:5432 -d postgres:14.1 
7.	установить и настроить систему сборки Maven(https://maven.apache.org/download.cgi ) 
8.	http://localhost:8080/swagger-ui/index.html - API приложения
9.	открыть проект в IDE и запустить его;

