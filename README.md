# Небольшой прототип для отрисовывания несложных графиков

### Используемые библиотеки

* [Official Spring Boot documentation](https://spring.io/projects/spring-boot)
* [JavaFX](https://openjfx.io)

### Использование
Настройте следующие параметры из application.properties

* spring.application.ui.title=My Chart          #Наименование графика
* chart.formulae=#x ^ 2 - 12 * #x ^ 3 / 4 + 10   #Используемая формула на SPEL (https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/expressions.html)
* chart.margin.left=-20                         #Левая граница графика
* chart.margin.right=20                         #Правая граница графика

В основе кодовой базы лежит учебное видео от JetBrains https://www.youtube.com/watch?v=OMuqIykUh5w