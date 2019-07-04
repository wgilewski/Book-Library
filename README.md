Book library application allows user to look through two sets with books - one set is provided 
from json file, and other from Books API. 

Application contains endpoints which allows you to :

-Changing books set / http://localhost:8080/books/selectDataset/  {plese select "api" or "json". You can change dataset in every moment}

-Getting all books /  http://localhost:8080/books

-Checking book details /  http://localhost:8080/books/  {book isbn number}

-Getting all books by category /  http://localhost:8080/books/category/  {category}

-Creating books rating / http://localhost:8080/books/rating

Framework - Spring Boot
 I used spring boot framework because it has a built-in server. 
 In addition, in the springboot the configuration takes place 
 automatically, which in my opinion is the perfect solution for such a small project.


Design patterns : DTO 
 The design pattern I chose is DTO. it allows you to transfer the object without having to show all the fields.


Testing frameworks : JUnit 5, Mockito


Building :
 To build the project use following command: 'mvn clean package'
 
Running :
 After building the application run following command to start it: 'java -jar target/booklibrary-0.0.1-SNAPSHOT.jar'
 Now you can test all endpoint.

