package io.huta.segmentation

import io.ktor.application.Application
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.withCharset
import io.ktor.server.testing.contentType
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
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
