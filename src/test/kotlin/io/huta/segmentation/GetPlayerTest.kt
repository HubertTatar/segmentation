package io.huta.segmentation

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import java.nio.charset.StandardCharsets.UTF_8

class GetPlayerTest {

    @Test
    fun getPlayer() {
        withTestApplication(Application::testApp) {
            with(handleRequest(HttpMethod.Get, "/test/players")) {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals(ContentType.Application.Json.withCharset(UTF_8), response.contentType())
                assertEquals("""[{"id":1,"firstName":"john","lastName":"doe"}]""", response.content)
            }
        }
    }

}