package tat.mukhutdinov.challenger.auth.gateway

import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import tat.mukhutdinov.challenger.auth.AuthRequest
import tat.mukhutdinov.challenger.auth.domain.boundary.AuthGateway
import tat.mukhutdinov.challenger.infrastructure.routing.Paths
import tat.mukhutdinov.challenger.infrastructure.structure.Response

class AuthRemoteGateway(private val httpClient: HttpClient) : AuthGateway {

    override suspend fun auth(token: String) {
        val response: Response = httpClient.post {
            url(path = Paths.auth)
            contentType(ContentType.Application.Json)
            body = AuthRequest(token)
        }

        if (!response.isSuccessful) {
            throw RuntimeException(response.error?.message)
        }
    }
}