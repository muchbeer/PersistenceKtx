package raum.muchbeer.persistencektx.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import raum.muchbeer.persistencektx.domain.VideoEntity
import raum.muchbeer.persistencektx.model.SleepEntity

@Database(entities = [SleepEntity::class, VideoEntity::class], exportSchema = false, version = 2)
abstract class GeneralDatabase : RoomDatabase() {

    abstract val sleepDao : SleepDao
    abstract val offlineDao: OfflineDao

    companion object {
        @Volatile
        private var INSTANCE : GeneralDatabase? = null


        fun getInstance(context: Context) : GeneralDatabase {
             var instance  = INSTANCE

            synchronized(this) {
                if (instance ==null) {
                    instance = Room.databaseBuilder(context.applicationContext, GeneralDatabase::class.java, "sleep_database")
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance!!
            }

        }

    }
}