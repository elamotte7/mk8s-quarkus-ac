package org.acme.beer.repository

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import org.acme.beer.domain.Beer
import org.acme.beer.domain.BeerColor
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class BeerRepository: PanacheRepository<Beer> {
    fun findByName(name: String): List<Beer> =
            list("name = ?1", name)

    fun findByColor(color : BeerColor): List<Beer> = list("color", color)

    fun findByPriceGreaterThan(price: Int): List<Beer> = list("price > ?1", price)
}