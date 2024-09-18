#!/usr/bin/env bash

echo $PWD

cd todo-backend || exit
#build and push new image
aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 585008070670.dkr.ecr.us-east-1.amazonaws.com
./gradlew bootBuildImage
docker tag action_items_server:latest 585008070670.dkr.ecr.us-east-1.amazonaws.com/action_items_server:latest
docker push 585008070670.dkr.ecr.us-east-1.amazonaws.com/action_items_server:latest

cd ../ || exit
#spin up aws infrastructure
cd deployment/terraform/ || exit
terraform init
terraform apply -auto-approve
cd ../../ || exit

sleep 300

kubectl apply -f https://github.com/kubernetes-sigs/metrics-server/releases/latest/download/components.yaml
kubectl autoscale deployment todo-backend --cpu-percent=75 --min=1 --max=3


