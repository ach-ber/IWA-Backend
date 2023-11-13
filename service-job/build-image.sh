docker-compose down

docker rmi $(docker images -a -q)

docker volume prune

docker build -t jobservice-api ./service-job

docker-compose up -d