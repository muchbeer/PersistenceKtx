package raum.muchbeer.persistencektx

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import raum.muchbeer.persistencektx.database.SleepDao
import raum.muchbeer.persistencektx.database.GeneralDatabase
import raum.muchbeer.persistencektx.model.SleepEntity
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class SleepDatabaseTest {

    lateinit var sleepDao : SleepDao
    lateinit var db : GeneralDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        db = Room.inMemoryDatabaseBuilder(context, GeneralDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        sleepDao = db.sleepDao
    }

    @After
    @Throws(IOException::class)
    fun close() {
        db.close()
    }

    @Test
    @Throws(IOException::class)
     fun testQualityDataInput() {
        val night = SleepEntity()
        GlobalScope.launch(Dispatchers.IO) {
            sleepDao.insert(night)
            val retrieveNight = sleepDao.getTonight()
            assertEquals(retrieveNight?.sleepQuality, -1)
        }


    }
}