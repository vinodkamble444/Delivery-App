package tk.andivinu.deliveryapp.model

/**
 * Created by Vinod Kamble on 03-08-2018.
 */

import android.arch.persistence.room.Embedded
import com.squareup.moshi.JsonClass
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable

@Entity
data class Delivery(
        @PrimaryKey(autoGenerate = true)
        var id: Int=0,
        val description: String,
        val imageUrl: String,
        @Embedded
        val location: Location

        )