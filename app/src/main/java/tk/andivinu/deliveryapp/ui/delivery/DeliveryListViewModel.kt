package tk.andivinu.deliveryapp.ui.delivery

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableInt
import io.reactivex.Observable
import android.util.Log
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import tk.andivinu.deliveryapp.R
import tk.andivinu.deliveryapp.base.BaseViewModel
import tk.andivinu.deliveryapp.model.*
import tk.andivinu.deliveryapp.network.DeliveryApi
import javax.inject.Inject


class DeliveryListViewModel(private val deliveryDao: DeliveryDao) : BaseViewModel() {
    @Inject
    lateinit var deliveryApi: DeliveryApi
    val resultListAdapter: DeliveryListAdapter = DeliveryListAdapter({ deliveryItem : Delivery -> deliveryItemClicked(deliveryItem) })
    var searchButtonVisibility: ObservableInt
    val deliveryItemId: MutableLiveData<Int> = MutableLiveData()

    init {
        searchButtonVisibility = ObservableInt(View.VISIBLE)
    }

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadDeliveries() }

    private lateinit var subscription: Disposable

    init {
        loadDeliveries()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadDeliveries() {
        subscription =
                Observable.fromCallable { deliveryDao.all }
                        .concatMap {
                            dbDeliveryList ->
                            if(dbDeliveryList.isEmpty()){
                                deliveryApi.getDeliveries().concatMap {
                                    apiDeliveryList -> deliveryDao.insertAll(*apiDeliveryList.toTypedArray())
                                    //Observable.just(apiDeliveryList)
                                    Observable.fromCallable { deliveryDao.all }
                                }}
                            else
                                Observable.just(dbDeliveryList)

                        }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { onRetrieveDeliveriesListStart() }
                        .doOnTerminate { onRetrieveDeliveriesListFinish() }
                        .subscribe(
                                { result -> onRetrieveDeliveryListSuccess(result) },
                                { onRetrievePostListError() }
                        )

    }

    private fun onRetrieveDeliveriesListStart() {
        Log.d("ResultListViewModel", "onRetrieveDeliveriesListStart")
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveDeliveriesListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveDeliveryListSuccess(searchResult:List<Delivery>) {
        Log.d("ResultListViewModel", "onRetrieveDeliveryListSuccess " + searchResult.toString())
        resultListAdapter.updateDeliveryList(searchResult)
    }

    private fun onRetrievePostListError() {
        Log.d("ResultListViewModel", "onRetrievePostListError")
        errorMessage.value = R.string.data_error
    }


    private fun deliveryItemClicked(deliveryItem : Delivery) {
        Log.d("ResultListViewModel", "deliveryItemClicked"+deliveryItem.id)
        deliveryItemId.value =deliveryItem.id
    }

}