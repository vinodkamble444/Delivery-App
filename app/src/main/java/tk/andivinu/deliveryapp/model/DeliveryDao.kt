package tk.andivinu.deliveryapp.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import org.intellij.lang.annotations.JdkConstants
import io.reactivex.Observable
import java.util.*

@Dao
interface DeliveryDao {
    @get:Query("SELECT * FROM Delivery")
    val all: List<Delivery>
  /*  @Query("SELECT * FROM Delivery")
    fun getAll(): List<Delivery>*/

    @Insert
    fun insertAll(vararg delivery: Delivery)
 /*   @Insert
    fun insertAll(delivery: List<Delivery>)*/
/*
    @Query("DELETE FROM Delivery")
    abstract fun deleteAllDeliveries()*/
/*
    @JdkConstants.ListSelectionMode
    fun select(vararg id: Int): Delivery*/

    @Query("SELECT * FROM Delivery WHERE id = :Id")
    fun loadDelivery(Id: Int): Delivery
}

