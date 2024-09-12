#!/usr/bin/env bash

echo $PWD

cd todo-backend || exit
#build and push new image
aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 585008070670.dkr.ecr.us-east-1.amazonaws.com
./gradlew bootBuildImage
docker tag todo-server:latest 585008070670.dkr.ecr.us-east-1.amazonaws.com/todo-server:latest
docker push 585008070670.dkr.ecr.us-east-1.amazonaws.com/todo-server:latest

cd ../ || exit
#spin up aws infrastructure
cd deployment/terraform/ || exit
terraform init
terraform apply -auto-approve
cd ../../ || exit

#configure and apply kubernetes resources
aws eks update-kubeconfig --region us-east-1 --name todo-app
kubectl apply -R -f deployment/k8s/

echo All Resources applied

kubectl get pods -o wide
kubectl get nodes -o wide
kubectl get svc -o wide

sleep 300

kubectl apply -f https://github.com/kubernetes-sigs/metrics-server/releases/latest/download/components.yaml
kubectl autoscale deployment todo-backend --cpu-percent=75 --min=1 --max=3


