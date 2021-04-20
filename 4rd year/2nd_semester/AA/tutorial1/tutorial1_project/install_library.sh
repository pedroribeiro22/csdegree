#!/bin/bash

mvn install:install-file -Dfile=lib/orm.jar \ 
	-DgroupId=org.orm -DartifactId=orm  \
	-Dversion=1.0.0 -Dpackaging=jar
