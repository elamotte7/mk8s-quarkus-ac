package org.acme.rest.client

import org.eclipse.microprofile.rest.client.inject.RestClient
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/hello")
class HelloResource {

    @Inject
    @field: RestClient
    private lateinit var helloService: HelloService

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun hello() = helloService.hello()
}