#!/bin/sh
mvn clean package && docker build -t com.mycompany/PortafolioV1_Maven .
docker rm -f PortafolioV1_Maven || true && docker run -d -p 9080:9080 -p 9443:9443 --name PortafolioV1_Maven com.mycompany/PortafolioV1_Maven