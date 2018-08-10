package tk.andivinu.deliveryapp.model

/**
 * Created by Vinod Kamble on 03-08-2018.
 */
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
        val lat: Double,
        val lng: Double,
        val address: String

)
