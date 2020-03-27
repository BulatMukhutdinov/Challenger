package tat.mukhutdinov.challenger

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.post
import io.ktor.routing.routing
import tat.mukhutdinov.challenger.auth.AuthRequest
import tat.mukhutdinov.challenger.auth.AuthResponse
import tat.mukhutdinov.challenger.infrastructure.routing.Paths.auth
import tat.mukhutdinov.challenger.infrastructure.structure.Response

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    routing {
        post(auth) {
            val request = call.receive<AuthRequest>()

            println("Token is ${request.idToken}")

            call.respond(Response(result = AuthResponse()))
        }
    }
}
