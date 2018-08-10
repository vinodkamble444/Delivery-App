package tk.andivinu.deliveryapp.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import tk.andivinu.deliveryapp.model.Delivery
import tk.andivinu.deliveryapp.model.DeliveryDao

@Database(entities = arrayOf(Delivery::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun deliveryDao(): DeliveryDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, "deliveries").build()
                }
            }
            return INSTANCE

        }
    }
 fun destroyInstance(){
     INSTANCE= null
 }
}