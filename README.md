# PP 3.1.2 — Spring Boot Security

Учебное приложение на Spring Boot с авторизацией и разграничением прав пользователей.

## Используемые технологии

Java 17, Spring Boot, Spring Security, Spring Data JPA, Hibernate, Thymeleaf, MySQL.

## Функциональность

- Авторизация пользователей из базы данных.
- Роли `ROLE_USER` и `ROLE_ADMIN`. У пользователя может быть несколько ролей.
- Страница `/user` с информацией о текущем пользователе.
- Страница `/admin` с добавлением, редактированием и удалением пользователей.
- Перенаправление после входа в зависимости от роли.
- Выход из системы.

## Запуск

Создать базу данных MySQL:

```sql
CREATE DATABASE spring_security;
```

Указать в конфигурации запуска IntelliJ IDEA переменные окружения `KATA_DB_PASSWORD`, `KATA_ADMIN_PASSWORD` и `KATA_USER_PASSWORD`.

Запустить класс `SpringBootSecurityDemoApplication`.

Для проверки используются учётные записи `admin@example.com` и `user@example.com`. Пароли задаются через соответствующие переменные окружения.

Приложение доступно по адресу http://localhost:8080.
