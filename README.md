# Hibersafe

This application uses Java 11 and MySQL 5.7.34.

Create your database and update the file `application.properties` with your database connection properties.

The database must be created using the default charset as utf8mb4, in order to store emojis that might appear on the questions.

```
create database hibersafe character set UTF8mb4 collate utf8mb4_bin;
```

