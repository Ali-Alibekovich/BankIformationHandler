<h1>Задание:</h1>

Написать на Spring FrameWork приложение, которое РАЗ в СУТКИ качает xlsx файл по ссылке <br>
http://www.cbr.ru/banking_sector/credit/FullCoList/<br>
Парсит его результат, и кладет в реляционную СУБД.<br>
Адекватно учитывать, что данные об организации могут измениться, может добавиться новая или удалиться.<br>
Не нужно делать дубликатов или чистить всю таблицу и создавать новую.<br>

Стек: Spring Boot, Spring Data Jpa, СУБД Postgres. <br>

Все надстройки тут:
[Тут](https://github.com/Ali-Alibekovich/BankIformationHandler/blob/main/src/main/resources/application.properties)
