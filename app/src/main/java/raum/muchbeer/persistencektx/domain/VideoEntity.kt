package raum.muchbeer.persistencektx.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import raum.muchbeer.persistencektx.util.smartTruncate

@Entity(tableName = "video_table")
data class VideoEntity(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("url")
    @PrimaryKey
     val url: String,
    @SerializedName("updated")
    val updated: String,
    @SerializedName("thumbnail")
    val thumbnail: String) {

    /**
     * Short description is used for displaying truncated descriptions in the UI
     */
    val shortDescription: String
        get() = description.smartTruncate(200)
}