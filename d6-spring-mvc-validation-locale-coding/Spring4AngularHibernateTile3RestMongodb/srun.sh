#!/bin/sh
mvn clean
mvn install
 
shutdown.sh
read -p "Shutdown tomcat8.0, Enter to continue......."
cd ~/Spring4/Spring4AngularHibernateTile3RestMongodb
cp -rf target/Spring4AngularHibernateTile3RestMongodb.war ~/tomcat8.0/webapps/Spring4AngularHibernateTile3RestMongodb.war

read -p "Enter to continue start tomcat8.0......."
startup.sh
 lsof -i :8080
