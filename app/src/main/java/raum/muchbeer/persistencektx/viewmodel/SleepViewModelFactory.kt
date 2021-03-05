package raum.muchbeer.persistencektx.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import raum.muchbeer.persistencektx.database.SleepDao
import java.lang.IllegalArgumentException


class SleepViewModelFactory(private val app: Application) :
                ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SleepViewModel::class.java)) {
           return SleepViewModel(app) as T
        }

        throw IllegalArgumentException("Unknown ViewModel...")
    }
}
