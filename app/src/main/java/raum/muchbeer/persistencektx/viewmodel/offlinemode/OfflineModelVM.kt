package raum.muchbeer.persistencektx.viewmodel.offlinemode

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import raum.muchbeer.persistencektx.database.GeneralDatabase.Companion.getInstance
import raum.muchbeer.persistencektx.domain.VideoEntity
import raum.muchbeer.persistencektx.repository.Repository

class OfflineModelVM(val app : Application) : AndroidViewModel(app) {


    private val _checkOnProgressBar = MutableLiveData<Boolean>()

    val checkOnProgressBar: LiveData<Boolean>
        get() = _checkOnProgressBar


    private val database = getInstance(app)

    private val repository = Repository(database)
    init {
        refreshDataFromNetwork()
    }

    /**
     * Refresh data from network and pass it via LiveData. Use a coroutine launch to get to
     * background thread.
     */
    private fun refreshDataFromNetwork() = viewModelScope.launch {
       repository.refreshVideo()
    }


    val playlistDB = repository.videoList

    fun initiateProgressBar() {
        _checkOnProgressBar.value = true
    }
}