package raum.muchbeer.persistencektx.database

import androidx.lifecycle.LiveData
import androidx.room.*
import raum.muchbeer.persistencektx.domain.VideoEntity

@Dao
interface OfflineDao {
    @Query("select * from video_table")
    fun getVideos(): LiveData<List<VideoEntity>>

    @Query("select * from video_table")
    fun getVideosList(): List<VideoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(videos: List<VideoEntity>)
}