package tk.andivinu.deliveryapp.ui.deliverydetail

import android.arch.lifecycle.MutableLiveData
import tk.andivinu.deliveryapp.base.BaseViewModel
import tk.andivinu.deliveryapp.model.Delivery
import tk.andivinu.deliveryapp.utils.AT

class DeliveryIemViewModel : BaseViewModel() {
    private val description = MutableLiveData<String>()
    private val imageUrl = MutableLiveData<String>()
    private val address = MutableLiveData<String>()
    private val lat = MutableLiveData<Double>()
    private val lng = MutableLiveData<Double>()
    private val id = MutableLiveData<Int>()



    fun bind(delivery: Delivery){
        description.value = delivery.description + AT + delivery.location.address
        imageUrl.value = delivery.imageUrl
        address.value = delivery.location.address
        lat.value = delivery.location.lat
        lng.value = delivery.location.lng
        id.value=delivery.id

    }

    fun getDescription(): MutableLiveData<String> {
        return description
    }

    fun getImageUrl(): MutableLiveData<String> {
        return imageUrl
    }
    fun getAddress(): MutableLiveData<String> {
        return address
    }

    fun getLat(): MutableLiveData<Double> {
        return lat
    }
    fun getLng(): MutableLiveData<Double> {
        return lng
    }

    fun getId(): MutableLiveData<Int> {
        return id
    }

}