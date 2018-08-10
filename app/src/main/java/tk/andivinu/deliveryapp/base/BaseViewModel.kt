package tk.andivinu.deliveryapp.base

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import tk.andivinu.deliveryapp.injection.component.DaggerViewModelInjector
import tk.andivinu.deliveryapp.injection.component.ViewModelInjector
import tk.andivinu.deliveryapp.injection.module.NetworkModule
import tk.andivinu.deliveryapp.ui.delivery.DeliveryListViewModel


abstract class BaseViewModel: ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is DeliveryListViewModel -> injector.inject(this)
        }
    }
}