package tat.mukhutdinov.challenger

import org.kodein.di.Kodein
import tat.mukhutdinov.challenger.auth.AuthModule
import tat.mukhutdinov.challenger.infrastructure.InfrastructureModule

object CommonDiModules {

    val modules = Kodein.Module("CommonDiModules") {
        import(InfrastructureModule.module)
        import(AuthModule.module)
    }
}