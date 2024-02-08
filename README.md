# BD2-PWR-AdministrativeDivisionOfPoland
Projekt realizowany w ramach kursu Bazy Danych 2 - Backend.

## Temat
Baza danych aktualnego podziału administracyjnego Polski.

## Uruchomienie
W celu wygenerowania pliku wykonywalnego należy uruchomić skrypt ```bootJar``` przy wykorzystaniu narzędzia budowania gradle. Można wykonać tę akcję z poziomu IDE bądź przy użyciu komendy:
<br/>
```
$./gradlew bootJar
```
Plik wykonywalny wygenerowany zostanie w ścieżce ./API/libs w postaci pliku .jar.
W celu uruchomienia należy skorzystać z komendy:
```
$java -jar administrative-division-of-poland-backend.jar
```
<br/>

W celu zmiany konfiguracji połączenia z bazą danych należy zmienić dane w pliku konfiguracyjnym ```/API/resources/application.yml``` w poniższej sekcji, podając kolejno URL do bazy danych, nazwę użytkownika oraz hasło do bazy danych.

```yml
spring:
  datasource:
    url: jdbc:postgresql://0.0.0.0:5432/database_name
    username: postgres
    password: YOUR_PASSWORD
```
Dokumentacja SWAGGER dostępna pod ścieżką: ```http://localhost:8085/swagger-ui/index.html#/```
