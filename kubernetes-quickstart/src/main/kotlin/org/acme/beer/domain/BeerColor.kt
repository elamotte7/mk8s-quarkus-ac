package org.acme.beer.domain

import com.fasterxml.jackson.annotation.JsonValue

enum class BeerColor(
        val color : String
) {
    YELLOW("yellow"),
    AMBER("amber"),
    BROWN ("brown"),
    BLACK ("black")
}