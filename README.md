# PP 3.1.2 — Spring Boot Security

Практическое задание Kata Academy. Spring Boot 2.6.2, Java 17, Spring Security, Hibernate/JPA, MySQL и Thymeleaf.

## Что реализовано
- Сущности User (UserDetails) и Role (GrantedAuthority), многие-ко-многим через users_roles.
- Вход по email и паролю через UserDetailsService с данными из MySQL.
- Пароли хранятся в виде BCrypt-хешей, а не открытым текстом.
- /admin и весь CRUD доступны только ROLE_ADMIN.
- /user доступен ROLE_USER и ROLE_ADMIN, показывает данные текущего пользователя.
- После входа ROLE_ADMIN ведёт на /admin (при двух ролях приоритет ADMIN), ROLE_USER на /user.
- POST /logout из Thymeleaf-форм, с защитой CSRF.

## Запуск
1. Установить **JDK 17** и MySQL 8.
2. Создать отдельную базу (чтобы не затронуть прежний CRUD):
   `CREATE DATABASE spring_security CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;`
3. Установить переменные окружения в PowerShell **перед запуском IDEA/приложения**:
   `$env:KATA_DB_PASSWORD = "ваш_пароль_mysql"`
   `$env:KATA_ADMIN_PASSWORD = "придумайте_сильный_пароль"`
   `$env:KATA_USER_PASSWORD = "другой_сильный_пароль"`
   Переменные должны быть доступны Java-процессу. Для запуска из IDEA добавьте их в конфигурацию запуска.
4. Запустить `SpringBootSecurityDemoApplication` (или `./mvnw spring-boot:run`, если Maven Wrapper доступен). Адрес: http://localhost:8080.
5. Демонстрационный администратор: `admin@example.com`, пароль из `KATA_ADMIN_PASSWORD` (роли USER и ADMIN).
   Обычный пользователь: `user@example.com`, пароль из `KATA_USER_PASSWORD` (роль USER).
   Пользователи создаются только при первом запуске, если переменные заданы. Роли создаются автоматически.

**Примечание:** демонстрационные пароли не хранятся в Git. Не указывайте реальные пароли в application.properties.
При изменении профиля пароль можно оставить пустым. Администратор может создавать пользователей и назначать им одну или обе роли.

## Структура
- `model`: JPA-сущности и Spring Security интерфейсы
- `dao`: EntityManager CRUD
- `repository`: репозиторий ролей
- `service`: бизнес-логика и UserDetailsService
- `configs`: конфигурация безопасности, логика входа и первичное заполнение БД
- `controller`: страницы для пользователей и администратора

Ветка подготовлена отдельно от предыдущей работы PP_3_1_1; основная ветка предыдущего задания не изменена.

