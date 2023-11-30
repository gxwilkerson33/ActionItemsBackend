#!/usr/bin/env bash


kubectl delete all --all
cd deployment/terraform/ || exit
terraform destroy

echo All Resources deleted

kubectl get pods -o wide
kubectl get nodes -o wide
kubectl get svc -o wide
