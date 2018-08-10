package tk.andivinu.deliveryapp.injection.component

import dagger.Component
import tk.andivinu.deliveryapp.injection.module.NetworkModule
import tk.andivinu.deliveryapp.ui.delivery.DeliveryListViewModel
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified DeliveryListViewModel.
     * @param deliveryListViewModel DeliveryListViewModel in which to inject the dependencies
     */
    fun inject(deliveryListViewModel: DeliveryListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}