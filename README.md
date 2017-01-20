# java-ee-4-beginners

---

# Day 01
## Введение в git
- Базовые понятия
- Базовые операции

### Lab: клонирование проекта

## JDK
- Понятие Java-приложения
- Понятие classpath
- Пакеты
- Разбиение классов по пакетам

### Lab: сборка простого приложения

## Введение в сборщик maven
- Цикл сборки
- Зависимости

## Внутренности JVM
- Администрирование JVM
- Выделение памяти для объектов и классов
- Алгоритмы gc
- Мониторинг JVM

### Lab: монторинг простого приложения с JVisualVM

## IDE NetBeans
- Работа с NetBeans — основные операции

## Процедурный стиль
- Парадигмы программирования
- Статический контекст
- Декларация метода
- Примитивные типы
- Операторы
- Управляющие конструкции
- Модификатор final
- Ссылочные типы
- Строки
- Примитивные массивы (+двумерные)
- Перечисления (enums)
- Перегрузка методов

### Lab: простое приложение в процедурном стиле

## ООП 
- Классы и объекты
- Наследование
- Полиморфизм
- Видимость
- Конструкторы

---

# Day 02

- Абстракции
- Принципы проектирования
- Параметризуемые классы (generics)

### Lab: простое приложение в объектном стиле

## Java Core

### Ключевые классы системной библиотеки
- System
- Object
- Строки
- Обертки поверх примитивов
- Работа с регулярными выражениями
- Properties

### Ввод-вывод
- Библиотека классов ввода-вывода
- Кодировки
- Буферизация
- Форматирование вывода (коротко)
#### Lab: Пробуем самостоятельно работать с Apache Commons CSV

---

# Day 03

### Исключения
- Концепция обработки исключений
- Блок try-catch-finally
- Типы исключений
- Выброс исключения из метода
- try-with-resources

### Коллекции
- Обзор collection API
- Коллекции
- Множества (set)
- Отображения (map)
- Сравнение различных структур данных на предмет применимости в различных ситуациях
- Механизм сравнения элементов коллекций

### Механизм Reflection
- Introspection
- Annotations

### Обзор JNI/JNA

## JDBC

### Архитектура JDBC
- Схемы взаимодействия с СУБД 
- Структура JDBC-программы
- Типы JDBC-драйверов 

### Взаимодействие с СУБД посредством JDBC API
- SQL-запросы к СУБД
- Обработка данных из БД, выборки
- Выборки с произвольным доступом 
- Модифицируемые выборки
- Обработка ошибок 

### Дополнительные возможности JDBC API
- Понятие транзакции (+возможность декларативного управления транзакциями)
- DataSources и пулы соединений 

### Обзор JDBC Template

### Обзор JPA

---

# Day 04

## JNDI и изменения данных в сервисе

### Введение в JNDI
- Концепции служб каталога. Схемы именования. ()
- Основные пакеты JNDI
- https://docs.oracle.com/javase/tutorial/jndi/index.html
- https://docs.oracle.com/javase/tutorial/jndi/concepts/index.html

### Naming Operations
- Поиск в каталоге 
- Контексты и связывание
- https://docs.oracle.com/javase/tutorial/jndi/ops/index.html
- http://www.java2s.com/Code/Java/JNDI-LDAP/CatalogJNDI-LDAP.htm

### Directory Operations:
- https://docs.oracle.com/javase/tutorial/jndi/ldap/index.html
- Работа с аттрибутами
- http://www.zytrax.com/books/ldap/ape/
- Поиск по каталогу
- https://docs.oracle.com/javase/tutorial/jndi/ops/search.html

### Связываем вместе Naming и Directory Operations:
- String Names и Structured Names (http://docs.oracle.com/javase/jndi/tutorial/beyond/names/string.html)
- Composite Names (http://docs.oracle.com/javase/jndi/tutorial/getStarted/concepts/glossary.html#COMPOSITENAME)
- Compound Names (http://docs.oracle.com/javase/jndi/tutorial/beyond/names/compound.html)
- Name Parsers (http://docs.oracle.com/javase/jndi/tutorial/beyond/names/parse.html)

## Servlet API
- Lifecycle
- Обработка HTTP-запроса, формирование HTTP-ответа
- Параметры, атрибуты, заголовки и cookies (+API)
- Listener и Filter 

### Введение в JSP
- Базовые конструкции
- Custom tags
- JSTL

## WS (REST, SOAP)
- http://www.mkyong.com/tutorials/jax-ws-tutorials/
- http://www.journaldev.com/9123/jax-ws-tutorial
- http://docs.oracle.com/javaee/6/tutorial/doc/bnayn.html

### Что такое веб-сервис 
- Протокол SOAP
- Структура сообщения (https://www.mkyong.com/webservices/jax-ws/jax-ws-hello-world-example-document-style/)
- WSDL + различные варианты биндинга (rpc/encoded, rpc/literal, document/literal) http://www.mkyong.com/webservices/jax-ws/jax-ws-hello-world-example/
- http://www.javatpoint.com/jax-ws-tutorial + http://stackoverflow.com/questions/9062475/what-is-the-difference-between-document-style-and-rpc-style-communication + http://amaloff.blogspot.ru/2015/08/wsdl-wsdl.html

### JAX-WS на примере JAX-WS RI + Metro
- https://coderanch.com/t/625764/certification/difference-wsimport-wsgen-command
- WSDL-to-Java (https://www.mkyong.com/webservices/jax-ws/jax-ws-wsimport-tool-example/)
- Java-to-WSDL (http://www.mkyong.com/webservices/jax-ws/jax-ws-wsgen-tool-example/)
- Client (sync/async/dispatch) http://www.ibm.com/support/knowledgecenter/SSAW57_7.0.0/com.ibm.websphere.nd.iseries.doc/info/iseriesnd/ae/twbs_jaxwsclientasync.html + https://dzone.com/articles/jax-rs-20-asynchronous-server-and-client + https://docs.oracle.com/cd/E14571_01/web.1111/e13734/asynch.htm#WSADV115
- Проблемы совместимости

### JAX-RS
- Сравенение с JAX-WS

---

# Day 05

## XML и сериализация объектов
- Работа с XML 
- Понятие схемы и пример XSD
- Способы разбора документа 
- https://docs.oracle.com/cd/B28359_01/appdev.111/b28394/adx_j_parser.htm#ADXDK19108
- XSLT
- https://docs.oracle.com/javase/tutorial/jaxp/stax/why.html

### JAXP
- DOM https://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
- SAX https://www.mkyong.com/java/how-to-read-xml-file-in-java-sax-parser/
- StAX 

### JAXB
- overview/marshaling/unmarshalling
- https://www.mkyong.com/java/jaxb-hello-world-example/ + http://docs.oracle.com/javaee/5/tutorial/doc/bnazg.html
- Преобразования XML

## Общие принципы разработки JavaEE приложений с помощью  фреймворков JSF

### Что такое web-приложение, для чего используется

### Структура приложения
- WEB-INF/classes, WEB-INF/lib
- web.xml

### Проектирование JSF web приложений
- Структура
- Макет страниц

### Библиотеки компонентов
- Базовые компоненты
- Валидаторы

### Обработка событий в JSF web приложениях

---

# Литература
- http://tinyurl.com/skilltrek-reading-list
- http://agilerussia.ru/practices/version-control-with-multiple-teams/

# Еще тренинги
- http://tinyurl.com/skilltrek-trainings

