# ./gradlew jib  打包docker image and push
#kind get clusters
#kind get nodes --name tutorial-cluster
#kubectl config get-contexts
#kubectl config use-context kind-istio-testing
#kubectl get nodes
#kind create cluster --config kind-cluster.yaml
#kubectl apply -f postgres-init-configmap.yaml
#docker build -t registry.cn-hangzhou.aliyuncs.com/houguanghui/finsight-timescaledb:latest-pg15 .
# kubectl apply -f istio-finsight-services.yaml
#kubectl get pods -n istio-system
#kubectl get pods -n kube-system
# kubectl get svc
#kubectl port-forward svc/istio-finsight-gateway-istio 80:80
#kubectl get gateway -n istio-system
#kubectl get httproute -n istio-system
# kubectl logs fundamental-v1-7d97794ffd-84f86

# kubectl get endpoints fundamental
# curl -H "Host: finsight.com" http://localhost:80/actuator/health
#curl http://finsight.local/actuator/health
#$  curl http://finsight.local/grpcHello

#> istioctl waypoint apply --namespace default

#docker exec timescaledb-finance pg_dump -U finance_user -d finance_db \
#  --data-only \
#  --format=custom \
#  --no-owner \
#  --no-privileges \
#  --verbose > finance_data_only.dump
#$ kubectl describe pod postgres-85764fcf8f-98f4c
kubectl exec postgres-c64cd7f7f-27q7l -- pg_restore -U finance_user -d finance_db \
  --data-only \
  --format=custom \
  --no-owner \
  --no-privileges /tmp/finance_data_only.dump
#   /var/lib/postgresql/data/postgresql.conf
#$ kubectl exec -it postgres-c64cd7f7f-2kdps  -- bash
#kubectl get deployments
#kubectl cp ./postgres/ postgres-c64cd7f7f-2kdps:/tmp/
#$ kubectl exec postgres-c64cd7f7f-8956s -- sh -c " pg_restore -U finance_user -d finance_db   --data-only   --format=custom   --no-owner   --no-privileges /tmp/finance_data_only.dump"
#kubectl exec postgres-c64cd7f7f-2kdps -- psql -U finance_user -d finance_db -c "SHOW max_connections;"
#kubectl exec postgres-c64cd7f7f-2kdps -- psql -U finance_user -d finance_db -c "SELECT count(*) FROM pg_stat_activity;"
