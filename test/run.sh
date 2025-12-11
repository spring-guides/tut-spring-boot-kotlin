#!/bin/bash
set -e

cd $(dirname $0)
cd ..

./mvnw clean package
rm -rf target

./gradlew build
rm -rf build
