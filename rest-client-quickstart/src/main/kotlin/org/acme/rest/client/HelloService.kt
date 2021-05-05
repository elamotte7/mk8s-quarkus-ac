package org.acme.rest.client

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/hello")
@RegisterRestClient
interface HelloService {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun hello(): String?
}