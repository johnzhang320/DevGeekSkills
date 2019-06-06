#FROM openjdk:8

#ADD /target/springboot-tiles-1.0.jar springboot-tiles-1.0.jar
#RUN bash -c 'touch /springboot-tiles-1.0.jar'
#EXPOSE 8080

#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar","/springboot-tiles-1.0.jar"]  

FROM tomcat:8.5-alpine

VOLUME /tmp
ENV ETC_DIR="/app/etc/certRenewMgr"

RUN \
  mkdir -pv $ETC_DIR && \
  mkdir -pv $ETC_DIR/commonlog && \
  mkdir -pv $ETC_DIR/conf && \
  mkdir -pv $ETC_DIR/conf/certmanager && \
  mkdir -pv $ETC_DIR/conf/gpgrepos && \
  mkdir -pv $ETC_DIR/conf/mapdictionary && \
  mkdir -pv $ETC_DIR/conf/splunk && \
  mkdir -pv $ETC_DIR/data/certmanager && \
  mkdir -pv $ETC_DIR/data/gpgarchive && \
  mkdir -pv $ETC_DIR/data/gpgrepos && \
  mkdir -pv $ETC_DIR/data/mapdictionary && \
  mkdir -pv $ETC_DIR/data/newCerts && \
  mkdir -pv $ETC_DIR/data/renewbuild && \  
  mkdir -pv $ETC_DIR/data/splunk/results && \  
  mkdir -pv $ETC_DIR/docs && \  
  mkdir -pv $ETC_DIR/ssl/CertManagerDev && \  
  mkdir -pv $ETC_DIR/ssl/CertManagerDev/ShellScript && \  
  mkdir -pv $ETC_DIR/tests && \  
  mkdir -pv $ETC_DIR/tests/gpgretrieve && \  
  mkdir -pv $ETC_DIR/tests/splunkretrieve && \
  mkdir -pv $ETC_DIR/tests/ssl    
  
COPY target/ROOT  /usr/local/tomcat/webapps/ROOT
 
RUN sh -c 'touch /usr/local/tomcat/webapps/ROOT' 
 
CMD     ["java", "-cp", "/usr/local/tomcat/webapps/ROOT/WEB-INF/classes:/usr/local/tomcat/webapps/ROOT/WEB-INF/lib/*", "com.devglan.tiles.Application"]

EXPOSE 8080