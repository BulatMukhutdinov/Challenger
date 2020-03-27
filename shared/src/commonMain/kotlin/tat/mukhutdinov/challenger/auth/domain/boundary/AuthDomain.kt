package tat.mukhutdinov.challenger.auth.domain.boundary

interface AuthDomain {

    suspend fun auth(token: String)
}