package tat.mukhutdinov.challenger

import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import tat.mukhutdinov.challenger.infrastructure.di.DiModules

class App : BaseApp(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(DiModules.modules)
    }

}