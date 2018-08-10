package tk.andivinu.deliveryapp.network;

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import tk.andivinu.deliveryapp.model.Delivery


/**
 * The interface which provides methods to get result of webservices
 */
interface DeliveryApi {
    /**
     * Get the list of the deliveries from the API
     * https://api.myjson.com/bins/1cojug
     */

/*    @POST("/deliveries")*/
    @GET("/bins/1cojug")
    fun getDeliveries(): Observable<List<Delivery>>
}
