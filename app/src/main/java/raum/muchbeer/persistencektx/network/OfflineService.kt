package raum.muchbeer.persistencektx.network

import kotlinx.coroutines.Deferred
import raum.muchbeer.persistencektx.model.VideoContainer
import retrofit2.http.GET

interface OfflineService {
    @GET("devbytes.json")
    fun getPlaylist(): Deferred<VideoContainer>
}