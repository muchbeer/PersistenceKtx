package raum.muchbeer.persistencektx.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import raum.muchbeer.persistencektx.domain.VideoEntity

@Entity(tableName = "view_video_demo")
data class DatabaseVideoEntity(
    @PrimaryKey
    val url: String,
    val updated: String,
    val title: String,
    val description: String,
    val thumbnail: String)

fun List<DatabaseVideoEntity>.asDomainModel(): List<VideoEntity> {
    return map {
        VideoEntity(
            url = it.url,
            title = it.title,
            description = it.description,
            updated = it.updated,
            thumbnail = it.thumbnail)
    }
}