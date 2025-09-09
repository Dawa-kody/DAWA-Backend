#!/bin/bash
set -e

cd ~/DAWA-Backend

git reset --hard
git pull origin main

chmod +x ./gradlew
./gradlew clean build

docker build -t dawa/dawa-server:latest .

docker stop dawa-server || true
docker rm dawa-server || true

docker run -d -p 8080:8080 --name dawa-server --env-file ./.env dawa/dawa-server:latest
