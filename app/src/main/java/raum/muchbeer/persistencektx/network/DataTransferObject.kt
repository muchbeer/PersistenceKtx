package raum.muchbeer.persistencektx.network

import raum.muchbeer.persistencektx.database.DatabaseVideoEntity
import raum.muchbeer.persistencektx.domain.VideoEntity



data class NetworkVideoContainer(val videos: List<VideoEntity>)


data class NetworkVideo(
    val title: String,
    val description: String,
    val url: String,
    val updated: String,
    val thumbnail: String,
    val closedCaptions: String?)


/**
 * Convert Network results to database objects
 */
fun NetworkVideoContainer.asDomainModel(): List<VideoEntity> {
    return videos.map {
        VideoEntity(
            title = it.title,
            description = it.description,
            url = it.url,
            updated = it.updated,
            thumbnail = it.thumbnail)
    }
}

fun NetworkVideoContainer.asDatabaseModel(): Array<DatabaseVideoEntity> {
    return videos.map {
        DatabaseVideoEntity(
            title = it.title,
            description = it.description,
            url = it.url,
            updated = it.updated,
            thumbnail = it.thumbnail)
    }.toTypedArray()
}