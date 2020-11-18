WIP

# GraalVM

[![GraalVM: Run Programs Faster Anywhere ](https://www.graalvm.org/resources/img/graalvm.png)](https://www.graalvm.org/)

## Configuring

Follow the intructions [here](https://quarkus.io/guides/building-native-image#configuring-graalvm)

# Microk8s

[![Microk8s: The smallest, fastest Kubernetes](https://repository-images.githubusercontent.com/132732601/e3882d80-e367-11e9-8177-a6d5ec3eaff3)](https://microk8s.io/)

## microk8s quickstart

Install microk8s on mac OS : 

```shell script
$  brew install ubuntu/microk8s/microk8s
```

Run the installer : 

```shell script
$  microk8s install
```

Say yes to install multipass, and see multipass below to configure it properly

Start microk8s : 

```shell script
$ microk8s start
```

Enable Ingress, Registry, storage, dashboard, dns and Istio :

```shell script
$ microk8s enable ingress storage dashboard dns istio
```

Launch the k8s dashboard : 

```shell script
$ microk8s dashboard-proxy
```

output : 

```shell script
Checking if Dashboard is running.
Dashboard will be available at https://192.168.64.2:10443
Use the following token to login:
eyJhbGciOiJSUzI1NiIsImtpZCI6IkpCT1EwcUhNajcwNGl6RnVWMlo0dXFjbE83N205M25tR2k0dUpKcG5CcVEifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJrdWJlLXN5c3RlbSIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJkZWZhdWx0LXRva2VuLXh3dGI5Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQubmFtZSI6ImRlZmF1bHQiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC51aWQiOiJkYmRkYzFjMS1kNzgzLTQzZGUtYmNjZi1iYjk0MjlkNGU0YzciLCJzdWIiOiJzeXN0ZW06c2VydmljZWFjY291bnQ6a3ViZS1zeXN0ZW06ZGVmYXVsdCJ9.mwVXowyf3k_UMQ7dol5YqoM5UeYoulBZHISa1t_in2HEXah0Ul4YWWxeMP_CjISi1TGVutURKM4p13PG88fv42-6c9SiGM_O_lw-aSU_n9oLCa-uzr8Hc_6DEA4SooJHdvZ_U82BH3CzwvC0A4-wxvNqqTpNxhQMi2Tva5aq3DtPYxeqTMQ8OX_6xdNpnF5nzsH3hO8FxbHDvo0zfWbbJSnI23rQgHzL_mUBFN_rFLu5C1nQ9EFZ5BHa4XpavlMwscIrZboKIR1zlz-rLX75liCZxlT15giNufujTymJU5-NJtUfQmLhQ5JGFjydYkCX_4oTw7Z7IBK0py3P5FWPdQ
Forwarding from 0.0.0.0:10443 -> 8443
Handling connection for 10443
```


Get the token to authenticate in the dashboard : 

```shell script
$ token=$(microk8s kubectl -n kube-system get secret | grep default-token | cut -d " " -f1)
$ microk8s kubectl -n kube-system describe secret $token
```

To use native k8s command, add microk8s config to kubeconfig : 

```shell script
$ sudo microk8s kubectl config view --raw > $HOME/.kube/config
```

Stop microk8s : 

```shell script
$ microk8s stop
```

## namespace

```shell script
$ microk8s kubectl apply -f /microk8s/k8s/namespace/apero-code-ns.yaml
```

## configmap

```shell script
$ microk8s kubectl create -f /microk8s/k8s/config-map/grpc-client-configmap.yaml
```

```shell script
$ microk8s kubectl create -f /microk8s/k8s/config-map/grpc-server-configmap.yaml
```

## Ingress

```shell script
$ microk8s kubectl apply -f /microk8s/k8s/ingress/k8s-quickstart-ingress.yaml
```

## Secret

TODO

## PostreSQL

```shell script
$ microk8s kubectl apply -f /microk8s/db/postgresql/postgresql-deployment.yaml
```

## k8s-quickstart

[![Quarkus](https://design.jboss.org/quarkus/logo/final/PNG/quarkus_logo_horizontal_rgb_1280px_default.png)](https://quarkus.io/)

To deploy the kubernetes quickstart native image to microk8s

```shell script
$ cd kubernetes-quickstart
$ ./mvnw package -Dquarkus.profile=microk8s -Pnative -Dquarkus.kubernetes.deploy=true
```

To deploy the kubernetes quickstart JVM image to microk8s

```shell script
$ cd kubernetes-quickstart
$ ./mvnw package -Dquarkus.profile=microk8s -Dquarkus.kubernetes.deploy=true
```

## grpc-client-k8s

To deploy the grpc client to microk8s

```shell script
$ cd grpc-client-k8s
$ ./mvnw package -Dquarkus.profile=microk8s -Pnative -Dquarkus.kubernetes.deploy=true
```

or

```shell script
$ cd grpc-client-k8s
$ ./mvnw package -Dquarkus.profile=microk8s -Dquarkus.kubernetes.deploy=true
```

## grpc-server-k8s

To deploy the grpc client to microk8s

first deploy the config map

```shell script
$ microk8s kubectl apply -f /microk8s/k8s/config-map/grpc-client-k8s.yaml
```

then

```shell script
$ cd grpc-server-k8s
$ ./mvnw package -Dquarkus.profile=microk8s -Pnative -Dquarkus.kubernetes.deploy=true
```

or

```shell script
$ cd grpc-server-k8s
$ ./mvnw package -Dquarkus.profile=microk8s -Dquarkus.kubernetes.deploy=true
```

# Multipass

[![Multipass: lightweight VM manager for Linux, Windows and macOS ](https://assets.ubuntu.com/v1/c87ec87a-Multipass-header-option_crop.png)](https://multipass.run/)

Multipass provides a command line interface to launch, manage and generally fiddle about with instances of Linux.
Our microk8s is deployed in a ubuntu vm, so we may need to access the vm.

List the vm : 

```shell script
$ multipass list
```

output :
 
```shell script
 Name                    State             IPv4             Image
 microk8s-vm             Running           192.168.64.2     Ubuntu 18.04 LTS
```

The multipass shell command will open a shell prompt on an instance.

```shell script
$ multipass shell microk8s-vm
```

output : 

```shell script
Welcome to Ubuntu 18.04.5 LTS (GNU/Linux 4.15.0-118-generic x86_64)

 * Documentation:  https://help.ubuntu.com
 * Management:     https://landscape.canonical.com
 * Support:        https://ubuntu.com/advantage

  System information as of Sat Oct 31 18:24:18 CET 2020

  System load:  0.81               Processes:             176
  Usage of /:   13.5% of 48.29GB   Users logged in:       1
  Memory usage: 37%                IP address for enp0s2: 192.168.64.2
  Swap usage:   0%

 * Introducing self-healing high availability clustering for MicroK8s!
   Super simple, hardened and opinionated Kubernetes for production.

     https://microk8s.io/high-availability

12 packages can be updated.
0 updates are security updates.

New release '20.04.1 LTS' available.
Run 'do-release-upgrade' to upgrade to it.


Last login: Sat Oct 31 18:08:21 2020 from 192.168.64.1
ubuntu@microk8s-vm:~$ 
```

## Mount volume for multipass (because we want to work with local files)

The recommended way to share data between your host and the instance is the mount command:

```shell script
$  multipass mount ~/dev/git/sedona/apero-code/mk8s-quarkus-ac/microk8s/ microk8s-vm:/microk8s
```

To unmount the volume

```shell script
$ multipass unmount microk8s-vm
```

# Istio

[![Istio: Connect, secure, control, and observe services. ](https://avatars2.githubusercontent.com/u/23534644?s=280&v=4)](https://istio.io/)

```shell script
$ microk8s kubectl label namespace quarkus-apero-code istio-injection=enabled
```

```shell script
$ export INGRESS_PORT=$(microk8s kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="http2")].nodePort}')
$ export SECURE_INGRESS_PORT=$(microk8s kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="https")].nodePort}')
$ export TCP_INGRESS_PORT=$(microk8s kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="tcp")].nodePort}')
$ export INGRESS_HOST=$(microk8s kubectl get po -l istio=ingressgateway -n istio-system -o jsonpath='{.items[0].status.hostIP}')
```

## Gateway

### No tls

```shell script
$ microk8s kubectl apply -f /microk8s/istio/gateway/k8s-quickstart-gateway.yaml
```

### TLS ingress gateway for a single host

First of all generate client and server secret

Follow the instructions [here](https://istio.io/latest/docs/tasks/traffic-management/ingress/secure-ingress/)

```shell script
$ openssl req -x509 -sha256 -nodes -days 365 -newkey rsa:2048 -subj '/O=Sedona/CN=sedona.fr' -keyout sedona.fr.key -out sedona.fr.crt
```

```shell script
$ openssl req -out k8s-quickstart.sedona.fr.csr -newkey rsa:2048 -nodes -keyout k8s-quickstart.sedona.fr.key -subj "/CN=k8s-quickstart.sedona.fr/O=Sedona solution"
$ openssl x509 -req -days 365 -CA sedona.fr.crt -CAkey sedona.fr.key -set_serial 0 -in k8s-quickstart.sedona.fr.csr -out k8s-quickstart.sedona.fr.crt
```

#### Create secret for certificates

```shell script
$ microk8s kubectl create -n istio-system secret tls istio-ingressgateway-k8s-quickstart-certs --key /microk8s/istio/certs/k8s-quickstart.sedona.fr.key --cert /microk8s/istio/certs/k8s-quickstart.sedona.fr.crt
```

```shell script
$ cat > gateway-patch.json <<EOF
[{
  "op": "add",
  "path": "/spec/template/spec/containers/0/volumeMounts/0",
  "value": {
    "mountPath": "/etc/istio/istio-ingressgateway-k8s-quickstart-certs",
    "name": "istio-ingressgateway-k8s-quickstart-certs",
    "readOnly": true
  }
},
{
  "op": "add",
  "path": "/spec/template/spec/volumes/0",
  "value": {
  "name": "istio-ingressgateway-k8s-quickstart-certs",
    "secret": {
      "secretName": "istio-ingressgateway-k8s-quickstart-certs",
      "optional": true
    }
  }
}]
EOF
```

```shell script
$ microk8s kubectl -n istio-system patch --type=json deploy istio-ingressgateway -p "$(cat gateway-patch.json)"
```

#### Deploy the ingress gateway

```shell script
$ microk8s kubectl apply -f /microk8s/istio/gateway/k8s-quickstart-gateway-tls.yaml
```

Test it 

```shell script
curl -v -HHost:k8s-quickstart.sedona.fr --resolve "k8s-quickstart.sedona.fr:$SECURE_INGRESS_PORT:$INGRESS_HOST" --cacert istio/certs/sedona.fr.crt "https://k8s-quickstart.sedona.fr:$SECURE_INGRESS_PORT/hello"
```

## Mutual TLS


## Routing










