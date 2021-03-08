package raum.muchbeer.persistencektx.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import raum.muchbeer.persistencektx.database.GeneralDatabase
import raum.muchbeer.persistencektx.domain.VideoEntity
import raum.muchbeer.persistencektx.network.OnlineInstance.Companion.offlineInstance

class Repository(val database: GeneralDatabase) {

    /*  val videos: LiveData<List<Video>> =
        Transformations.map(database.videoDao.getVideos()) {
            it.asDomainModel()
        }*/

    val videoList: LiveData<List<VideoEntity>> = database.offlineDao.getVideos()

    suspend fun refreshVideo() {
        withContext(Dispatchers.IO) {
            val playList = offlineInstance().getPlaylist().await()
            database.offlineDao.insertAll(playList.videos)

            //  database.videoDao.insertAll(*playlist.asDatabaseModel())
        }
    }


}