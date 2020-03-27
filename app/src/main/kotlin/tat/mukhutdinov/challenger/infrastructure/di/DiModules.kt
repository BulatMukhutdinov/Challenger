package tat.mukhutdinov.challenger.infrastructure.di

import org.kodein.di.Kodein
import tat.mukhutdinov.challenger.CommonDiModules
import tat.mukhutdinov.challenger.auth.di.AuthModule

object DiModules {

    val modules = Kodein.Module(DiModules::class.java.name) {
        import(CommonDiModules.modules)
        import(AuthModule.module)
    }
}