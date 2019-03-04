<html>
<body>
<h2>Sudoku Master </h2>
<h2>Please click <a href="input.jsp">here</a> to start the game!</h2>
<h3>
<textarea rows="70" cols="160" hidden>
This project build with maven3 + tomcat7~9.

Run this project:
	start tomcat
	right click on this project, and select "run as" --> "maven build ..." -->"tomcat:redeploy"
	input "http://127.0.0.1:8080/sudokuMaster/input.jsp" in browser bar.
	input your matrix and click "start"
	
Build preparation:
(1) import project into IDE
	Intellij:
		import project into Intellij as maven project
		File -> Settings -> Type Plugin -> Make sure Tomcat and TomEE Integration is checked.
		Run -> Edit Configuration...
		Click + icon, select Tomcat Server -> Local
		Click on "Deployment" tab, + icon to selelct "sudokuMaster:war exploded" to deploy, check context root to sudokuMaster
		Click on "Server" tab, select a Tomcat server, and update the on 'Update' action to "update classes and resources" -- for hotswap
		Now you can see an "Application Servers" view displayed at the bottom panel.
		(reference to : https://www.mkyong.com/intellij/intellij-idea-run-debug-web-application-on-tomcat/)

	Eclipse:
		import project into eclipse as a maven project.
		make sure m2eclipse and m2e-wtp plugin are installed.
		config tomcat7 path into Window --> preferences --> server --> runtime environment
		copy <tomcat_home>\webapps\ROOT into <workspace>\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\ROOT, override the existing ROOT folder.
			copy <tomcat_home>\webapps\* into <workspace>\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\webapps.
	    	This is fix a bug of eclipse.
	
(2) update &lt;tomcat_home&gt;/conf/tomcat-users.xml, add below lines
	<role rolename="tomcat"/>
	<role rolename="role1"/>
	<role rolename="manager-gui"/>
	<role rolename="manager-script"/>
	<role rolename="manager-jmx"/>
	<role rolename="manager-status"/>
	<role rolename="manager"/>
	<user username="tomcat" password="tomcat" roles="tomcat"/>
	<user username="both" password="tomcat" roles="tomcat,role1"/>
	<user username="role1" password="tomcat" roles="role1"/>
	<user username="admin" password="admin"
          roles="manager,manager-gui,manager-jmx,manager-script,manager-status,tomcat"/>

(3)update <maven_home>\conf\settings.xml, add below lines into servers section
    <server>
      <id>tomcat7</id>
      <username>admin</username>
      <password>admin</password>
    </server>

(4) edit pom.xml of this project. below section is for tomcat7. tomcat6 use different url
  	<build>
		<finalName>sudokuMaster</finalName>
		<plugins>
			<plugin>
				<groupId>org.codehaus.mojo</groupId>
				<artifactId>tomcat-maven-plugin</artifactId>
				<configuration>
					<server>tomcat7</server>
					<url>http://localhost:8080/manager/text</url>
				</configuration>
			</plugin>
		</plugins>
	</build>

Pending issue:
(1) j2ee-api.jar will lead to jsf class not found issue.

	</textarea>


</h3>
</body>
</html>
