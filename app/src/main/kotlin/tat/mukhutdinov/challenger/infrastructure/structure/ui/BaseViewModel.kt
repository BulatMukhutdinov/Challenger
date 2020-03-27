package tat.mukhutdinov.challenger.infrastructure.structure.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import tat.mukhutdinov.challenger.infrastructure.util.autoCleared
import timber.log.Timber
import tat.mukhutdinov.challenger.BR

abstract class BaseViewModel<V : ViewDataBinding> : Fragment(), KodeinAware {

    override val kodein by closestKodein()

    protected abstract val layoutId: Int

    protected var viewBinding: V by autoCleared()

    protected lateinit var viewScope: CoroutineScope

    protected lateinit var fragmentScope: CoroutineScope

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retainInstance = true
        fragmentScope = CoroutineScope(SupervisorJob() + Dispatchers.IO + exceptionHandler)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewScope = CoroutineScope(SupervisorJob() + Dispatchers.IO + exceptionHandler)

        viewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.lifecycleOwner = viewLifecycleOwner

        viewBinding.setVariable(BR.bindings, this)
    }

    override fun onDestroyView() {
        viewScope.cancel()

        super.onDestroyView()
    }

    override fun onDestroy() {
        fragmentScope.cancel()

        super.onDestroy()
    }

    protected fun navigate(direction: NavDirections, extras: Navigator.Extras? = null) {
        try {
            findNavController().navigate(direction.actionId, direction.arguments, null, extras)
        } catch (exception: IllegalArgumentException) {
            Timber.w(exception)
        }
    }
}