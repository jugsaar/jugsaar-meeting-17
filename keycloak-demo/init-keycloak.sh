#!/bin/bash

KEYCLOAK_HOME=/home/tom/dev/repos/gh/thomasdarimont/keycloak-dev/keycloak

mvn exec:java  -f $KEYCLOAK_HOME/testsuite/integration/pom.xml \
               -Pkeycloak-server \
               -Dkeycloak.migration.action=export \
			   -Dkeycloak.migration.provider=singleFile \
			   -Dkeycloak.migration.file=../configuration/demo-realm.json