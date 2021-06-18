# VISUAL backend code

This is a repo for the backend code of the prototype of VISUAL. It is still under development, but can be run in a local Java IDE (preferably IntellJ).
## Installation

Clone the repo in the local IDE, then run the main application.

```java
@SpringBootApplication
public class SpringQuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringQuizApplication.class, args);
	}

}
```

## Prerequisites
MySQL server should be already locally running, and it should be configured in the applications.properties file under resources. In place of springquiz in the snippet below, it should be your own database.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/springquiz?autoReconnect=true&useSSL=false
spring.datasource.username=root
spring.datasource.password=*******
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.properties.hibernate.format_sql=true
```

## Database Schema
Database has been created using MySQL and Spring Hibernate. The database schema is given below.
```
Player {
  Long id;
  String name;
  String email;
}

Question {
  Long id;
  String questionBody;
  String correctAnswer;
  String incorrectOne;
  String incorrectTwo;
  String incorrectThree;
}

Quiz {
  Long id;
  String title;
}

Pair {
  Long id;
  Long questionId; references -> Question.id;
  Long quizId; references -> Quiz.id;
}

Leaderboard {
  Long id;
  Long quizId; references -> Quiz.id;
  Long playerId; references -> Player.id;
  String name; references -> Player.name
}
```

## REST API routes and requests
These will be updated as soon as they are finalized.




## License
All rights reserved by KEEP (https://keep.edu.hk/)
