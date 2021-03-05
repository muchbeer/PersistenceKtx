package raum.muchbeer.persistencektx.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import raum.muchbeer.persistencektx.model.SleepEntity

@Database(entities = [SleepEntity::class], exportSchema = false, version = 1)
abstract class SleepDatabase : RoomDatabase() {

    abstract val sleepDao : SleepDao

    companion object {
        @Volatile
        private var INSTANCE : SleepDatabase? = null


        fun getInstance(context: Context) : SleepDatabase {
             var instance  = INSTANCE

            synchronized(this) {
                if (instance ==null) {
                    instance = Room.databaseBuilder(context.applicationContext, SleepDatabase::class.java, "sleep_database")
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance!!
            }

        }

    }
}