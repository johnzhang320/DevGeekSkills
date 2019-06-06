#!/bin/sh
# Copy certRenewMgr to folder where Dockerfile resides
# COPY certRenewMgr /app/etc/certRenewMgr

cd ~/DevWorkspace/CertificateMaintenanceEngine 

cp -rf ~/certRenewMgr/ ./certRenewMgr

mvn clean

mvn install
 
docker rmi -f springboot-tiles-1.0
 
docker run --rm -p 8080:8080 springboot-tiles-1.0

 
 