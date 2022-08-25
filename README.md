Урок №1

За основу взять код, написанный на уроке преподавателем. Разделить функционал из класса RequestHandler на несколько классов по типу функциолнала 
(парсинг запроса, работа с файловой системой, формирование ответа и др)

Урок №2

1. Разобраться с кодом, написанным за урок
2. Реализовать классы RequestParser и ResponseSerializer. Использовать их для работы с запросом и ответом в классе RequestHandler
3. Разработать систему классов для работы с параметрами сервера (порт и директория с файлами)

Урок №3

1. Реализовать паттерн фабрика для нескольких сервисов нижнего уровня из нашего веб-сервера. 
   Помимо создания фабрики для каждого из этих сервисов выделить интерфейс
2. Написать builder для классов HTTP-запроса и ответа на запрос

Урок №4

1. Переписать на Java примеры структурных паттернов отсюда https://github.com/kamranahmedse/design-patterns-for-humans
2. Попытаться использовать патерн декорато для обработки различных типов запросов (GET, POST, PUT) в нашем веб сервере

Урок №5

1. Написать реализацию обработчика различных HTTP методов, который будет находить классы обработчиков по аннотации Handle.
2. Доработать версию веб-сервера на основе RxJava
