package raum.muchbeer.persistencektx.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sleep_table")
data class SleepEntity(

    @PrimaryKey(autoGenerate = true)
     var sleepID: Long= 0L,

    @ColumnInfo(name = "start_sleep")
     val startSleep: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "stop_sleep")
     var stopSleep : Long = startSleep,

    @ColumnInfo(name = "sleep_rating")
     var sleepQuality: Int = -1


)
