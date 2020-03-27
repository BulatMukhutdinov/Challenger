package tat.mukhutdinov.challenger.auth.domain.boundary

interface AuthGateway {

    suspend fun auth(token: String)
}