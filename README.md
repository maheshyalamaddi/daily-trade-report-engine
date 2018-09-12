## spring-boot-sample-test
A test demo where it will simply process the given instruction in defined format and will outcome an expected data report.
It's web spring boot application - time being all the instruction processing will log on console and it's a command line runner 

### environment and the tech stack used
- Java 1.8+
- Windows
- Spring Boot 2.0.0.0 +
- Maven 3.0+
--

### get it working by using below instructions
- Ensure the Java and Maven is already setup in workstation
- Import this as Maven project in your favourate IDE and run this as "clean install spring-boot:run"
- OR
- Run the TestMain.java as a Java program
- To see the sample data being processed : <a>http://localhost:<random port>/h2-console/</a> and the JDBC URL is "jdbc:h2:mem:testdb"
- Application status can be viewed on : <a>http://localhost:<random port>/actuator/</a>