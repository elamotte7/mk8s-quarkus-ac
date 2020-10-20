# microk8s quickstart

Install microk8s on mac OS : 
- brew install ubuntu/microk8s/microk8s
- microk8s start
- microk8s stop
- microk8s enable ingress registry storage dashboard dns istio
- token=$(microk8s kubectl -n kube-system get secret | grep default-token | cut -d " " -f1)
- microk8s kubectl -n kube-system describe secret $token
- sudo microk8s kubectl config view --raw > $HOME/.kube/config
