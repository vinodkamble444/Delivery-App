package tk.andivinu.deliveryapp.ui.deliverydetail

import android.arch.lifecycle.MutableLiveData
import io.reactivex.Observable
import android.util.Log
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import tk.andivinu.deliveryapp.base.BaseViewModel
import tk.andivinu.deliveryapp.model.*
import tk.andivinu.deliveryapp.network.DeliveryApi
import javax.inject.Inject




class DeliveryDetailViewModel(private val deliveryDao: DeliveryDao) : BaseViewModel() {

    val errorMessage: MutableLiveData<String> = MutableLiveData()
    private lateinit var subscription: Disposable
    val delivery: MutableLiveData<Delivery> = MutableLiveData()



    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

     fun loadDeliveries(id: Int) {
        subscription = Observable.fromCallable { deliveryDao.loadDelivery(id)}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { onLoadDeliveriyStart() }
                        .doOnTerminate { onLoadDeliveryFinish() }
                        .subscribe(
                                { result -> onLoadDeliverySuccess(result) },
                                { onLoadDeliveryError() }
                        )
    }

    private fun onLoadDeliveriyStart() {
        Log.d("VINOD", "onLoadDeliveriyStart")
    }

    private fun onLoadDeliveryFinish() {
        Log.d("VINOD", "onLoadDeliveryFinish")
    }

    private fun onLoadDeliverySuccess(delivery: Delivery) {
        this.delivery.value= delivery
        errorMessage.value = delivery.description
        Log.d("VINOD", "onLoadDeliverySuccess " + delivery.toString())
    }

    private fun onLoadDeliveryError() {
        Log.d("VINOD", "onLoadDeliveryError")

    }

   

}