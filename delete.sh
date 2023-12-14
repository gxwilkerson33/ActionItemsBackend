#!/usr/bin/env bash


kubectl delete all --all
kubectl delete hpa --all
cd deployment/terraform/ || exit
terraform destroy -auto-approve

echo All Resources deleted
