package raum.muchbeer.persistencektx.database

import androidx.lifecycle.LiveData
import androidx.room.*
import raum.muchbeer.persistencektx.model.SleepEntity

@Dao
interface SleepDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insert(sleepNight: SleepEntity)

   @Update
   suspend fun update(sleepNight: SleepEntity)

   @Query("SELECT * FROM sleep_table WHERE sleepID = :key")
   suspend fun get(key: Long) : SleepEntity?

   @Query("SELECT * FROM sleep_table ORDER BY sleepID DESC")
   fun getAllSleep() : LiveData<List<SleepEntity>>

   @Query("DELETE FROM sleep_table")
   suspend fun clear()

   @Query("SELECT * FROM sleep_table ORDER BY sleepID DESC LIMIT 1")
   suspend fun getTonight() : SleepEntity?
}