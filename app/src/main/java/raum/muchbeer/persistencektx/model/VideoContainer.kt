package raum.muchbeer.persistencektx.model

import raum.muchbeer.persistencektx.domain.VideoEntity

data class VideoContainer(
    val videos : List<VideoEntity>
)