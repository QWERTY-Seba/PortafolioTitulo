@echo off
call mvn clean package
call docker build -t com.mycompany/PortafolioV1_Maven .
call docker rm -f PortafolioV1_Maven
call docker run -d -p 9080:9080 -p 9443:9443 --name PortafolioV1_Maven com.mycompany/PortafolioV1_Maven