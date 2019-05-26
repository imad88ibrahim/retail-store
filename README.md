# retail-store
Spring Boot app for The Retail Store Discounts

# This app uses H2 in-memory database for test purpose
# You can run test cases from src/test/java folder
# You can use sonarQube for reading test coverage reports
1.install sonarQube server on your machine and run it
2.add this to .m2 settings.xml
<settings>
    <pluginGroups>
        <pluginGroup>org.sonarsource.scanner.maven</pluginGroup>
    </pluginGroups>
    <profiles>
        <profile>
            <id>sonar</id>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
            <properties>
                <sonar.host.url>
                 http://localhost:9000
                </sonar.host.url>
            </properties>
        </profile>
     </profiles>
</settings>
3.run this command to generate test coverage reports
mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install -Dmaven.test.failure.ignore=false
4.run this command to add your test coverage reports to sonarQube server
mvn clean install sonar:sonar
# For API Description go to http://localhost:8080/v2/api-docs
