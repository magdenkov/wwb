Наподобие http://www.dataart.ru/blog/2014/07/dolgaya-doroga-v-java/

чтобы запустить приложение потребуется java 7+ и maven 3.
1) клонируйте репозиторий.(или dowload zip)
2) Потом в коммандной строке где pom.xml выполните mvn tomcat7:run
3) В браузере откройте http://localhost:9966/wwb/

Для простоты запуска я использовал tomcat plugin for maven(админка недоступна), а так же in-memory базу hsqldb, которая поднимается и инициализируется тестовыми данными при старте приложения.

Ещё многое не доделанно так что не судите строго.
В планах сделать авторизацию,2 роли, валидацию, проверку на отриц баланс, юнит тесты

В проекте исползуются Java 7, Spring 4(MVC(+jsp,jstl),core, transactions), Hibernate 4(+HQL), jQuery, maven, hsqldb, tomcat7, git  

