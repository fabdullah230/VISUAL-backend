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
```java
Player {
  Long id; (unique)
  String name;
  String email; (unique)
  Long keepId; work in progress
}

Question {
  Long id; (unique)
  int timelimit; work in progress
  String questionBody;
  String explaination; work in progress
  String correctAnswer;
  String incorrectOne;
  String incorrectTwo;
  String incorrectThree;
  DateTime createDateTime; work in progress
  DateTime updateDateTime; work in progress
  Long creatorId; work in progress
}

Quiz {
  Long id; (unique)
  String title;
  DateTime createDateTime; work in progress
  DateTime updateDateTime; work in progress
  Long creatorId; work in progress 
}

Pair {
  Long id; (unique)
  Long questionId; references -> Question.id;
  Long quizId; references -> Quiz.id;
}

Leaderboard {
  Long id; (unique)
  int score;
  Long quizId; references -> Quiz.id;
  Long playerId; references -> Player.id;
  String name; references -> Player.name
}
```

## REST API routes and requests
```java
Player

GET (/player/{playerId}) -> details of player with id = playerId (JSON)
GET (/player/allplayers) -> details of all players (List of JSON)
GET (/player/{playerId}/scores) -> details of all the scores of the player (List of JSON)

POST (/player) -> create new player (JSON with {name, email})

PUT (/player/{playerId}) -> update the name of player id = playerId (raw String)

DELETE (/player/{playerId}) -> delete player with id = playerId


Quiz

GET (/quiz/all) -> details of all quizzes (List of JSON)
GET (/quiz/{quizId}) -> details of quiz with id = quizId (JSON)
GET (/quiz/{quizId}/all) -> details of all question associated with quiz with id = quizId (List of JSON)
GET (/quiz/{quizId}/leaderboards) -> details of all scores associated with quiz with id = quizId (List of JSON)
GET (/quiz/{quizId}/leaderboards/{playerId}) -> details of the score of player with id = playerId in quiz with id = quizId (JSON)

POST (/quiz) -> create new quiz (JSON with {title})
POST (/quiz/newpair) -> create new quiz question pair (JSON with {quizId, questionId}) 
POST (/quiz/{quizId}/quickadd) -> for all questionId in the post request, create a new pair with quiz with id = quizId (JSON list of questionId, example -> [1, 2, 3])

PUT (/quiz/{quizId}) -> update title (raw String)
PUT (/quiz/{quizId}/leaderboards/{playerId}) -> update score of player with id = playerId in quiz with id = quizId

DELETE (/quiz/{quizId}) -> delete quiz with id = quizId
DELETE (/quiz/{quizId}/{questionId}) -> delete quiz-question pair with quizId and questionId


Question

GET (/question/{questionId}) -> details of question with id = questionId (JSON)
GET (/question/all) -> details of all questions (List of JSON)

POST (/question) -> create new question (JSON with {questionBody, correctAnswer, incorrectOne, incorrectTwo, incorrectThree)

PUT (/question/{questionId}) -> change questionBody of question with id = questionId (raw String)
PUT (/question/{questionId}/corans) -> change the correctAnswer of question with id = questionId (form data with key correctAnswer)
PUT (/question/{questionId}/incans) -> change the incorrect answers of the question with id = questionId (form data with keys incorrectOne, incorrectTwo and incorrectThree)


Leaderboards

GET (/leaderboards) -> home page
GET (/leaderboards/all) -> details of all scores (List of JSON)

POST (/leaderboards) -> create new score (JSON with {quizId, playerId, score, name}


Pair

GET (/pair) -> details of all quiz question pairs (List of JSON)
GET (/pair/question/{questionId}) -> details of all the pairs containing questionId (List of JSON)
GET (/pair/quiz/{quizId}) -> details of all the pairs containing quizId (List of JSON)

```

## SQL queries to manually populate database
```sql
//id, quiz
insert into quiz values(10, "title of the quiz")

//id, email, name
insert into player values(1, "email@example.com", "StudentName")

//id, correct_answer, incorrect_one, incorrect_three, incorrect_two, question_body
insert into question values(101, "correct answer", "incorrect answer", "incorrect answer", "incorrect answer", "this is the body of the question")

//id, name, player_id, quiz_id, score
insert into leaderboard values (999, "StudentName", 1, 10, 100)

//id, question_id, quiz_id
insert into pair values (1, 101, 10)
```














## License
All rights reserved by KEEP (https://keep.edu.hk/)
