cd todo-backend || exit
#build and push new image
aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 585008070670.dkr.ecr.us-east-1.amazonaws.com
./gradlew bootBuildImage
docker tag todo-server:latest 585008070670.dkr.ecr.us-east-1.amazonaws.com/todo-server:latest
docker push 585008070670.dkr.ecr.us-east-1.amazonaws.com/todo-server:latest

cd .. || exit
kubectl delete deployment todo-backend
kubectl apply -R -f .