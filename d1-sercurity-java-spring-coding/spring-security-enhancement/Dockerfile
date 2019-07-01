#FROM: 
# The keyword FROM, tells Docker to use a given image with its tag as build-base. If this image 
# is not in the local library, an online-search on DockerHub, or on any other configured remote-registry, 
# is performed
# Using remote tomcat 8.5 , create /usr/local/tomcat/ within Docker container
FROM tomcat:8.5-alpine
#FROM openjdk:8
#FROM tomcat:8.0.20-jre8
# RUN: 
# With the RUN command, we’re executing a shell command-line within the target system

 

 
 
VOLUME /tmp
 

COPY target/spring-security-enhancement  /usr/local/tomcat/webapps/spring-security-enhancement

 
RUN sh -c 'touch /usr/local/tomcat/webapps/spring-security-enhancement' 
 

CMD  ["java", "-cp", "/usr/local/tomcat/webapps/spring-security-enhancement/WEB-INF/classes:/usr/local/tomcat/webapps/spring-security-enhancement/WEB-INF/lib/*:/usr/local/tomcat/webapps/spring-security-enhancement/classes/application.properties:/usr/local/tomcat/webapps/spring-security-enhancement/classes/log4j.properties", "com.boot.security.auth.SpringSecurityApplication"]

EXPOSE 8080
EXPOSE 8091