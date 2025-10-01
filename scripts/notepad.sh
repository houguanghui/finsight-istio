# ./gradlew jib  打包docker image and push
#kubectl apply -f postgres-init-configmap.yaml
#docker build -t registry.cn-hangzhou.aliyuncs.com/houguanghui/finsight-timescaledb:latest-pg15 .
# kubectl apply -f finsight-istio-fundamental.yaml
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