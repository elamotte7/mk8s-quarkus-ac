package org.acme.rest.client

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class HelloResourceTest {

    @Test
    fun testHelloEndpoint() {
        given()
          .`when`().get("/hello/quarkus")
          .then()
             .statusCode(200)
             .body(`is`("Hello Quarkus!"))
    }

}