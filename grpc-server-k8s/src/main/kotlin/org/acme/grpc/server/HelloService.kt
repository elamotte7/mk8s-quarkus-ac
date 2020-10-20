package org.acme.grpc.server

import io.smallrye.mutiny.Uni
import javax.inject.Singleton

@Singleton
class HelloService : MutinyGreeterGrpc.GreeterImplBase() {

    override fun sayHello(request: HelloRequest): Uni<HelloReply>? {
        return Uni.createFrom().item {
            HelloReply.newBuilder().setMessage("Hello " + request.name).build()
        }
    }
}