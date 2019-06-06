#!/bin/sh
mvn clean
mvn install
 
shutdown.sh
read -p "Shutdown tomcat8.0, Enter to continue......."
cd ~/Spring4/Spring4AngularHibernateTile3RestCassandra
cp -rf target/Spring4AngularHibernateTile3RestCassandra.war ~/tomcat8.0/webapps/Spring4AngularHibernateTile3RestCassandra.war

read -p "Enter to continue start tomcat8.0......."
startup.sh
 lsof -i :8080
