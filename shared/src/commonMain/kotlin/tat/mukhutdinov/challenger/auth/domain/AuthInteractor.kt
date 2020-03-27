package tat.mukhutdinov.challenger.auth.domain

import tat.mukhutdinov.challenger.auth.domain.boundary.AuthDomain
import tat.mukhutdinov.challenger.auth.domain.boundary.AuthGateway

class AuthInteractor(private val gateway: AuthGateway) : AuthDomain {

    override suspend fun auth(token: String) {
        gateway.auth(token)
    }
}