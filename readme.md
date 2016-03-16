Write a web application that performs the functions of the bank. Let it be a bank in the town in the Wild West, dating back 53 resident and continues to grow.

It is necessary to store data:

Bank clients (ID, name - address, age if desired);
accounts (ID, user ID, amount of money);
transactions - money transfers between accounts or receipts / write-downs.
the minimum application must contain three pages:

a list of the bank's customers, when you click on the name of the selected customer opens an account (plus at the bottom of the form to add a new customer);
a list of specified customer accounts (plus at the bottom of the form to add a new account);
Form to transfer money between accounts (and for the replenishment / debiting: cost of purchase - m b, is some form of your choice..);
page with the list of transactions (on the filter shape to be able to select the period and / or for a given user);
all kinds of add-ons - to your taste, for example, in a customer list, you can sum all accounts of each output, etc...


This was a test task for applying job at DataArt http://www.dataart.ru/blog/2014/07/dolgaya-doroga-v-java/


To run the app You need to 
1)  execute  mvn tomcat7:run  on directory where pom.xml
2) In any browser open  http://localhost:9966/wwb/

It is used Java 7, Spring 4(MVC(+jsp,jstl),core, transactions), Hibernate 4(+HQL), jQuery, maven, hsqldb, tomcat7, git  

