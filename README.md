# VISUAL backend code

This is a repo for the backend code of the prototype of VISUAL. It is still under development, bur can be run in a local Java IDE (preferably IntellJ).
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

## REST API routes and requests
These will be updated as soon as they are finalized.
## License
All rights reserved by KEEP (https://keep.edu.hk/)
