#!/usr/bin/env bash

GRADLE_HOME=/opt/gradle/gradle-5.2.1
PATH=${GRADLE_HOME}/bin:${PATH}
FLYWAY_URL="jdbc:mysql://${MYSQL_HOST}:3306/healthmonitor"

gradle -Dflyway.url=$FLYWAY_URL -Dflyway.user=$MYSQL_USER -Dflyway.password=$MYSQL_PASSWORD flywayMigrate -i
gradle bootRun
