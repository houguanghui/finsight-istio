# Finsight Istio 微服务项目

## 项目简介

Finsight Istio 是一个基于 Istio 服务网格的微服务演示项目，展示了现代微服务架构的最佳实践。

## 技术栈
k8s
istio-ambient
spring-boot
grpc

### 核心框架
- **服务网格**: Istio
- **容器编排**: Kubernetes
- **容器运行时**: Docker

### 微服务组件
- 前端应用 registry.cn-hangzhou.aliyuncs.com/houguanghui/finsight-remix:1.0.0
- 后端 API 服务
  - registry.cn-hangzhou.aliyuncs.com/houguanghui/finsight-istio-fundamental:[1-2].0.0
  - registry.cn-hangzhou.aliyuncs.com/houguanghui/finsight-istio-indicator:1.0.0
  - registry.cn-hangzhou.aliyuncs.com/houguanghui/finsight-istio-stock:1.0.0
- 数据库服务
- 监控和日志系统

## 项目特点

- 🚀 **服务网格驱动**: 基于 Istio 实现服务发现、负载均衡和流量管理
- 🔒 **安全可靠**: 内置 mTLS 加密和认证授权机制
- 📊 **可观测性**: 集成 Prometheus、Grafana、Jaeger 等监控工具
- 🔄 **弹性设计**: 支持熔断、重试、超时等弹性模式
- 🐳 **云原生**: 完全容器化，支持 Kubernetes 部署

## 快速开始

### 环境要求

- Docker 28.3.2+
- Kubernetes v1.32.2+
- Istio 1.27.1+
- kubectl v1.32.2+

### 安装步骤

1. **克隆项目**
   ```bash
   git clone https://gitee.com/leo-blaze/finsight-istio.git
   ```
2. **创建pvc**
   ```bash
   kubectl apply -f platform/kube/istio-finsight-pvc.yaml
   ```
3. **创建services**
   ```bash
   kubectl apply -f platform/kube/istio-finsight-services.yaml
   ```
4. **创建网关**
   ```bash
   kubectl apply -f platform/kube/istio-finsight-gateway.yaml
   ```
5. **创建路由规则**
   ```bash
   kubectl apply -f platform/kube/istio-finsight-routes.yaml
   ```
6. **本地端口转发到网关**
   ```bash
   kubectl port-forward svc/istio-finsight-gateway-istio 80:80
   ```
