package raum.muchbeer.persistencektx.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import raum.muchbeer.persistencektx.database.SleepDao
import java.lang.IllegalArgumentException

class SleepQualityViewModelFactory(val key:Long, val database: SleepDao) :
          ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepQualityViewModel::class.java)) {
          return  SleepQualityViewModel(key, database) as T
        }

        throw IllegalArgumentException("Unknown ViewModel...")
    }
}