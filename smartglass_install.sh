#!/bin/bash

mvn package -DskipTests

scp -i smtgls.pem target/smart-glass-server-1.0.0.jar ubuntu@13.112.245.139:/tmp

ssh -i smtgls.pem ubuntu@13.112.245.139 "sh /tmp/deploy.sh"
