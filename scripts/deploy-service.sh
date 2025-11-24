kind create cluster --config kind-cluster.yaml
istioctl install --set profile=ambient --skip-confirmation
#kubectl get crd gateways.gateway.networking.k8s.io &> /dev/null || \
#  kubectl apply -f https://github.com/kubernetes-sigs/gateway-api/releases/download/v1.3.0/standard-install.yaml
kubectl get crd gateways.gateway.networking.k8s.io &> /dev/null || \
  kubectl apply -f standard-install.yaml
kubectl apply -f istio-finsight-pvc.yaml
kubectl apply -f istio-finsight-services.yaml
kubectl apply -f istio-finsight-gateway.yaml
kubectl apply -f istio-finsight-routes.yam
kubectl port-forward service/istio-finsight-gateway-istio 80:80