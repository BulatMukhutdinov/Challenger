package tat.mukhutdinov.challenger.home

import tat.mukhutdinov.challenger.R
import tat.mukhutdinov.challenger.databinding.HomeBinding
import tat.mukhutdinov.challenger.infrastructure.structure.ui.BaseViewModel

class HomeViewModel : BaseViewModel<HomeBinding>(), HomeBindings {

    override val layoutId: Int = R.layout.home
}