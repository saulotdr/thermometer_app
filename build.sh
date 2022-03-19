#!/bin/bash
mvn clean install -DskipTests
sudo cp target/thermometer-0.0.1-SNAPSHOT.jar env/