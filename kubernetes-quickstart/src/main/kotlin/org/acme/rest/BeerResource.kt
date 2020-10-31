package org.acme.rest

import org.acme.beer.domain.Beer
import org.acme.beer.repository.BeerRepository
import javax.inject.Inject
import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/beers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class BeerResource @Inject constructor(
        private val beerRepository: BeerRepository
) {

    @GET
    fun beers(): List<Beer> = beerRepository.findAll().list()

    @GET
    @Path("{name}")
    fun beer(@PathParam("name") name: String): Beer = beerRepository.findByName(name).first()

    @POST
    @Transactional
    fun add(beer: Beer): Response {
        beerRepository.persist(beer)
        return Response.ok(beer).status(Response.Status.CREATED).build()
    }

    @PUT
    @Path("{id}")
    @Transactional
    fun update(@PathParam("id") id: Long, beer: Beer): Response {
        val entity = beerRepository.findById(id)
                ?: throw WebApplicationException("Beer with id of $id does not exist.", 404)
        beerRepository.update("update from BEER_TABLE set BEER_NAME = ?1, " +
                "BEER_COLOR = ?2, BEER_COUNTRY = ?3, BEER_PRODUCER = ?4 where id = ?5",
                beer.name, beer.color, beer.country, beer.producer, id)

        return Response.ok(entity).build()
    }

    @DELETE
    @Path("{id}")
    @Transactional
    fun delete(@PathParam("id") id: Long): Response {
        beerRepository.deleteById(id)
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}