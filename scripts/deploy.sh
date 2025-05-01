git pull origin main

chmod +x ./gradlew

./gradlew clean build

docker build . -t dawa/dawa-server:latest

docker stop dawa-server
docker rm dawa-server

docker run -d -p 8080:8080 --name dawa-server --env-file ./.env dawa/dawa-server:latest