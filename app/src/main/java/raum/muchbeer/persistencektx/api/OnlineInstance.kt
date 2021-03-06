package raum.muchbeer.persistencektx.api

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class OnlineInstance {

    companion object {
        private const val BASE_URL = "https://mars.udacity.com/"

        val httpLogger = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY }

        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(httpLogger)
                .connectTimeout(30, TimeUnit.SECONDS) }.build()

        fun foodInstance() : OnlineService {
            Log.i("FoodInstance", "The application has access the FoodService")

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create(OnlineService::class.java)
        }
    }

}

enum class MarsApiFilter(val value: String) { SHOW_RENT("rent"), SHOW_BUY("buy"), SHOW_ALL("all") }

enum class MarsApiStatus { LOADING, ERROR, DONE }