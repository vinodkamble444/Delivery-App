package tk.andivinu.deliveryapp.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import tk.andivinu.deliveryapp.model.database.AppDatabase
import tk.andivinu.deliveryapp.ui.delivery.DeliveryListViewModel
import tk.andivinu.deliveryapp.ui.deliverydetail.DeliveryDetailViewModel

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeliveryListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DeliveryListViewModel(AppDatabase.getInstance(activity.applicationContext)!!.deliveryDao()) as T
        }
        if (modelClass.isAssignableFrom(DeliveryDetailViewModel::class.java)) {
          @Suppress("UNCHECKED_CAST")
            return DeliveryDetailViewModel(AppDatabase.getInstance(activity.applicationContext)!!.deliveryDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }




}