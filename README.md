# Hibersafe

## About

Hibersafe is an application that mines StackOverflow using the StackExchange API to search for questions and answers related to Hibernate/JPA annotations. It's goal is to create a database to be used to alert developers that the use of some annotations may cause exceptional behaviours not detailed on the Hibernate's documentation.

## Requirements to Run

This application uses Java 11 and MySQL 5.7.34.

Create your database and update the file `hibernate.cfg.xml` with your database connection properties.

The database must be created using the default charset as utf8mb4, in order to store emojis that might appear on the questions.

```xml
<!-- Database connection settings -->
<property name="connection.driver_class">com.mysql.cj.jdbc.Driver</property>
<property name="connection.url">jdbc:mysql://localhost:3306/${YOUR_DATABASE_NAME}?characterEncoding=utf-8&amp;useUnicode=true</property>
<property name="connection.username">${YOUR_DATABASE_USERNAME}</property>
<property name="connection.password">${YOUR_DATABASE_PASSWORD}</property>
```
