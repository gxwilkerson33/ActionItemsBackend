#!/usr/bin/env bash

echo $PWD
cd deployment/terraform/ || exit
terraform apply
cd ../../ || exit
aws eks update-kubeconfig --region us-east-1 --name todo-app
kubectl apply -R -f todo-backend-manifests/

echo All Resources applied

kubectl get pods -o wide
kubectl get nodes -o wide
kubectl get svc -o wide