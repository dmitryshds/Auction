This project is web application like  https://auction.violity.com/    It uses:

- Spring MVC framework as the root of application;
- Spring Security framework;
- relational database management system MySQL for data storage;
- object relational mapping framework Hibernate;
- AJAX;
- Java Server pages (JSP), Bootstrap framework and JQuery for viewing and validation of data in web pages;
- Log4J,TestNG, Mockito, JUnit for logging and testing;
- This application is launched on Tomcat 8.0.41;
- Data base dump is located inside progect; 

Features:

- main page uses AJAX request (JSON) for show items, and has access for all; 
- on items page full functionality only role USER or ADMIN;
- admin page has access only role ADMIN or DBADMIN; 
- application is used remember-me authentication;
- uploaded images are stored in folder specified in the application.properties;
- after registration you will recive confirm message on your e-mail;
- used Spring task sheduler for check the end date item;
- used Spring async for run asynchronous method in particular for mass mailings e-mails;
