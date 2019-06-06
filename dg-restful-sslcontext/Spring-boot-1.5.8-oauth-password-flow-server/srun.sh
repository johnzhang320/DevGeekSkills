#!/bin/sh
mvn clean
mvn install
cd ~/tomcat8.0/bin/
./shutdown.sh
cd ~/Spring4/Spring4AngularHibernateTile3RestCassandra
cp -rf target/Spring4AngularHibernateTile3RestCassandra.war ~/tomcat8.0/webapps/Spring4AngularHibernateTile3RestCassandra.war
rm -rf Spring4AngularHibernateTile3RestCassandra
cd ~/tomcat8.0/bin/
./startup.sh
lsof -i :8080 
