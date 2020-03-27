package tat.mukhutdinov.challenger

import org.kodein.di.Kodein
import org.kodein.di.direct
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.provider

private val kodein = Kodein.lazy {
    import(CommonDiModules.modules)
}