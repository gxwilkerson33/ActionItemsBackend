#!/bin/bash

eksctl create cluster --name=$1 --region=us-east-1 --zones=us-east-1a,us-east-1b --without-nodegroup
eksctl utils associate-iam-oidc-provider --region us-east-1 --cluster $1 --approve
eksctl create nodegroup --cluster=$1 \
                       --region=us-east-1 \
                       --name=todo-app-ng-public1 \
                       --node-type=$2 \
                       --nodes=2 \
                       --nodes-min=2 \
                       --nodes-max=4 \
                       --node-volume-size=20 \
                       --ssh-access \
                       --ssh-public-key=kube-todo-app \
                       --managed \
                       --asg-access \
                       --external-dns-access \
                       --full-ecr-access \
                       --appmesh-access \
                       --alb-ingress-access

kubectl apply -R -f .
