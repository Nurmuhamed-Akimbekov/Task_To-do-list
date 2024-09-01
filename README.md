# Task_To-do-list

## Описание

Это приложение для управления задачами в списке дел. Оно позволяет добавлять, редактировать, удалять задачи и помечать их как выполненные.

## Развертывание и запуск

1. **Клонируйте репозиторий:**
    ```bash
    git clone https://github.com/Nurmuhamed-Akimbekov/Task_To-do-list.git
    ```
2. **Перейдите в каталог проекта:**
    ```bash
    cd Task_To-do-list
    ```

3. **Настройте базу данных:**

    Убедитесь, что у вас установлен PostgreSQL и запущен. Создайте базу данных с именем `database_task`, если она еще не создана. Например, используя psql:
    ```bash
    psql -U postgres -c "CREATE DATABASE database_task;"
    ```

4. **Настройте файл конфигурации:**

    Убедитесь, что в файле `src/main/resources/application.properties` указаны правильные параметры подключения к базе данных:
    ```properties
    spring.application.name=Test_Task
    server.port=9080

    spring.datasource.url=jdbc:postgresql://localhost:5432/database
    spring.datasource.username=username
    spring.datasource.password=userpassword
    ```

5. **Запустите приложение:**

    Убедитесь, что у вас установлен JDK (Java Development Kit). Минимальная версия JDK: 11.

    Для запуска приложения используйте Maven. Выполните следующую команду:
    ```bash
    ./mvnw spring-boot:run
    ```
    Или, если у вас установлен Maven глобально:
    ```bash
    mvn spring-boot:run
    ```

6. **Доступ к приложению:**

    После запуска приложения откройте браузер и перейдите по адресу [http://localhost:9080](http://localhost:9080), чтобы получить доступ к приложению.

## Тестирование

Для запуска тестов используйте следующую команду:
```bash
./mvnw test
