# spring-boot-k8s-demo
SpringBoot Kubernetes MySQL Demo

Prerequisites:
* Docker [Installation](https://docs.docker.com/install/)
* Minikube [Installation](https://kubernetes.io/docs/tasks/tools/install-minikube/)

## Build and publish docker image on DockerHub

* Login with [DockerHub](https://hub.docker.com/) credentials

```> docker login```

* Build docker image

```> docker build -t <your-username>/spring-boot-k8s-demo .```

* Publish to DockerHub

```> docker push <your-username>/spring-boot-k8s-demo```

## Deploy application on Kubernetes/minikube

* Start minikube

```> minikube start --cpus 2 --memory 4096```

* Deploy application

```
> kubectl create -f k8s/config.yml
> kubectl create -f k8s/mysql.yml
> kubectl create -f k8s/app.yml
```

* Verify services

```> kubectl get services```

Sample output:

```
NAME          TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)          AGE
k8sdemo-svc   NodePort    10.102.84.238   <none>        8080:30080/TCP   1h
kubernetes    ClusterIP   10.96.0.1       <none>        443/TCP          28d
mysql         ClusterIP   None            <none>        3306/TCP         1h
```

* Get external URL of k8sdemo-svc

```> minikube service k8sdemo-svc --url```

Output: ttp://192.168.99.100:30080

* Open k8sdemo-svc in browser

```> minikube service k8sdemo-svc```

* View Minikube dashboard

```> minikube dashboard```

* Verify pods

```> kubectl get pods```

Sample output:

```
NAME                                       READY     STATUS    RESTARTS   AGE
k8sdemo-deployment-56855b6548-sgk8z        1/1       Running   0          1h
k8sdemo-mysql-deployment-79c8d5c47-m8466   1/1       Running   0          1h
```

* Check application logs

```> kubectl logs <pod-id>```

Ex: ```> kubectl logs k8sdemo-deployment-598d55cd58-4pzk8 ```

* Login into MySQL pod

```> kubectl run -it --rm --image=mysql:5.6 --restart=Never mysql-client -- mysql -h <mysql-host/service-name> -p<password>```

Ex: ```> kubectl run -it --rm --image=mysql:5.6 --restart=Never mysql-client -- mysql -h mysql -padmin```

## Scale application

* get deployment id of k8sdemo deployment

```> kubectl get deployments```

```
NAME                       DESIRED   CURRENT   UP-TO-DATE   AVAILABLE   AGE
k8sdemo-deployment         1         1         1            1           2h
k8sdemo-mysql-deployment   1         1         1            1           2h
```

* Scale k8sdemo-deployment to 3 pods

```> kubectl scale deployment k8sdemo-deployment --replicas=3```

## Undeploy application

```
> kubectl delete -f k8s/app.yml
> kubectl delete -f k8s/mysql.yml
> kubectl delete -f k8s/config.yml
```

# Enjoy :-)