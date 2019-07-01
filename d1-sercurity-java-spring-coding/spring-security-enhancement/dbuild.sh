#!/bin/sh
mvn clean
mvn install
docker rmi -f spring-security-enhancement-1.0
docker build -t spring-security-enhancement-1.0 .
docker run -p 8091:8091 spring-security-enhancement-1.0
