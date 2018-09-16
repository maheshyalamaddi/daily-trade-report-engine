##![#c5f015](https://placehold.it/15/c5f015/000000?text=+) daily-trade-report-engine
	A test demo where it will simply process the given instruction(please refer sample data file under resources) 
	in defined format and will outcome an expected data report.
	
	It's web spring boot application - time being all the instruction processing will log on console and it's a command line runner
	
	And the approach taken here as simple MVC as follow : 
	-- Viewer(END User) <---|DTO|--> Controller<---|DTO&HELPERS|--->Model (Repository)
	
	Spring actuator is enabled here for this added spring-starter-web though the actual project Object doesn't required web, 
	And actuator configuration bit easy for web when compared to non-web JMX.Only core and default actuator used here 
	and the security is not enabled hence the end-points are enabled only for INFO.
	
	For reference the site and coverage report attached in site folder.
		* Integration test coverage is not required as we do not have any separate downstream system.
		* Achieved 100% code coverage using Junit/Mockito - TDD

##![#f03c15](https://placehold.it/15/f03c15/000000?text=+) Getting Started
### Built with
	* Java 1.8+
	* Windows
	* Spring Boot 2.0.0.0 +
	* Maven 3.0+

### Get it live
	* Ensure the Java and Maven is already setup in workstation
	* Import this as Maven project in your favourite IDE and run this as "clean install spring-boot:run" OR "clean install"
	* To see the sample data being processed : <a>http://localhost:<random port>/h2-console/</a> and the JDBC URL is "jdbc:h2:mem:testdb"
	* Application status can be viewed on : <a>http://localhost:<random port>/actuator/</a>
	* random port - get it from console log with search criteria "Tomcat started on port(s):"
	* Run the following to see site & test coverage report "site surefire-report:report"
