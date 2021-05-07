package org.acme.beer.domain

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Cacheable
import javax.persistence.Column
import javax.persistence.Entity
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity(name = "BEER_TABLE")
@Cacheable
data class Beer(
        @field:NotBlank(message="The name must not be empty")
        @Column(name = "BEER_NAME", length = 40)
        var name: String = "",
        @Column(name = "BEER_PRODUCER", length = 40)
        var producer: String = "",
        @Column(name = "BEER_COLOR", length = 40)
        var color: String = BeerColor.YELLOW.color,
        @Column(name = "BEER_COUNTRY", length = 40)
        var country: String = "",
        @Column(name = "BEER_PRICE")
        var price: Int = 0
) : PanacheEntity()