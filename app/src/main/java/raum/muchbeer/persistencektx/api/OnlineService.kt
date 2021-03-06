package raum.muchbeer.persistencektx.api

import raum.muchbeer.persistencektx.model.MarsEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OnlineService {

    @GET("realestate")
    suspend fun getEstate(@Query("filter") type: String) : List<MarsEntity>
}