package raum.muchbeer.persistencektx.network

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

class OnlineInstance {


    companion object {
        private const val BASE_URL_ONLINE = "https://mars.udacity.com/"
        private const val BASE_URL_OFFLINE = "https://devbytes.udacity.com/"

        val httpLogger = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY }

        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(httpLogger)
                .connectTimeout(30, TimeUnit.SECONDS) }.build()

        fun onlineInstance() : OnlineService {
            Timber.i("Online Instance initiated")

            return Retrofit.Builder()
                .baseUrl(BASE_URL_ONLINE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create(OnlineService::class.java)
        }

        /**
         * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
         * full Kotlin compatibility.
         */


        fun offlineInstance() : OfflineService {
            Timber.i("Offline Instance initiated")
              return Retrofit.Builder()
                  .baseUrl(BASE_URL_OFFLINE)
                  .client(client)
                  .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                  .addCallAdapterFactory(CoroutineCallAdapterFactory())
                  .build().create(OfflineService::class.java)

        }
    }

}

enum class MarsApiFilter(val value: String) { SHOW_RENT("rent"), SHOW_BUY("buy"), SHOW_ALL("all") }

enum class MarsApiStatus { LOADING, ERROR, DONE }