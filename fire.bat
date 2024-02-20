mvn clean install -DskipTests
mvn spring-boot:run -DskipTests

@echo off
set logfile=Log-%DATE:~10,4%%DATE:~4,2%%DATE:~7,2%-%TIME:~0,2%%TIME:~3,2%%TIME:~6,2%.txt
mvn spring-boot:run -DskipTests > %logfile% 2>&1
echo Log file saved as %logfile%
