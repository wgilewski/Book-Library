# Book Library

Book library application allows user to look through two sets with books - one set is provided 
from json file, and other from Books API. 

Application contains endpoints which allows you to :

- [x] Changing books set / http://localhost:8080/books/selectDataset/  {plese select "api" or "json". You can change dataset in every moment}

- [x] Showing all books /  http://localhost:8080/books

- [x] Checking book details /  http://localhost:8080/books/  {book isbn number}

- [x] Showing all books by category /  http://localhost:8080/books/category/  {category}

- [x] Creating books rating / http://localhost:8080/books/rating

# Framework - Spring Boot
 I used spring boot framework because it has a built-in server. 
 In addition, in the springboot the configuration takes place 
 automatically, which in my opinion is the perfect solution for such a small project.


# Design patterns : DTO 
 The design pattern I chose is DTO. it allows you to transfer the object without having to show all the fields.


# Testing frameworks : JUnit 5, Mockito



# Building :
 To build the project use following command:
 ```sh
$ mvn clean package
```

# Running :
 After building the application run following command to start it: 
 ```sh
$ java -jar target/booklibrary-0.0.1-SNAPSHOT.jar'
```

