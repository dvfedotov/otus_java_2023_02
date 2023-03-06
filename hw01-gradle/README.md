# Примеры для курса OTUS ["Java Developer. Professional"](https://otus.ru/lessons/java-professional/?utm_source=github&utm_medium=free&utm_campaign=otus)

## Домашнее задание

### Проект gradle с модульной структурой
-[ ] Цель: научиться создавать проект Gradle, подготовиться к выполнению домашних заданий.

#### Описание/Пошаговая инструкция выполнения домашнего задания:

* Создайте аккаунт на github.com (если еще нет)
* Создайте репозиторий для домашних работ 
* Сделайте checkout репозитория на свой компьютер
* Создайте локальный бранч hw01-gradle
* Создать проект gradle
* В проект добавьте последнюю версию зависимости
```
com.google.guava
guava
```
* Создайте модуль hw01-gradle
* В модуле сделайте класс HelloOtus
* В этом классе сделайте вызов какого-нибудь метода из guava
* Создайте "толстый-jar"
* Убедитесь, что "толстый-jar" запускается.
* Сделайте pull-request в gitHub
* Ссылку на PR отправьте на проверку (личный кабинет, чат с преподавателем).


-[ ] To start the application:
```
./gradlew clean build
java -jar ./hw01-gradle/build/libs/gradleHelloWorld-0.1.jar one two two
<p>
```
-[ ] To unzip the jar:
```
unzip -l hw01-gradle-0.0.0-4.eb2d2ac0.hw01_gradle.dirty-SNAPSHOT.jar
unzip -l gradleHelloWorld-0.1.jar
```