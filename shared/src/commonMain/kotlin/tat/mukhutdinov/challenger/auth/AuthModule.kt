package tat.mukhutdinov.challenger.auth

import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.provider
import tat.mukhutdinov.challenger.auth.domain.AuthInteractor
import tat.mukhutdinov.challenger.auth.domain.boundary.AuthDomain
import tat.mukhutdinov.challenger.auth.domain.boundary.AuthGateway
import tat.mukhutdinov.challenger.auth.gateway.AuthRemoteGateway

object AuthModule {

    val module = Kodein.Module("CommonAuthModule") {

        bind<AuthDomain>() with provider {
            AuthInteractor(instance())
        }

        bind<AuthGateway>() with provider {
            AuthRemoteGateway(instance())
        }
    }
}