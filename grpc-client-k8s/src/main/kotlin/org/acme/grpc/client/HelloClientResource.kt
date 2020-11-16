package org.acme.grpc.client

import io.quarkus.grpc.runtime.annotations.GrpcService
import io.smallrye.mutiny.Uni
import org.acme.grpc.server.HelloRequest
import org.acme.grpc.server.MutinyGreeterGrpc
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/hello")
class HelloClientResource {
    @Inject
    @field: GrpcService("hello-service")
    lateinit var client: MutinyGreeterGrpc.MutinyGreeterStub

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun hello() = "hello"

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{name}")
    fun hello(@PathParam("name") name: String?): Uni<String> {
        return client.sayHello(HelloRequest.newBuilder()
                .setName(name)
                .build()).onItem().transform {
            it.message
        }
    }
}