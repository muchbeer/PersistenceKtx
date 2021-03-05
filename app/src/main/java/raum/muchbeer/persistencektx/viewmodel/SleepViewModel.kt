package raum.muchbeer.persistencektx.viewmodel

import android.app.Application
import android.text.method.TransformationMethod
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import raum.muchbeer.persistencektx.database.SleepDao
import raum.muchbeer.persistencektx.database.SleepDatabase.Companion.getInstance
import raum.muchbeer.persistencektx.model.SleepEntity
import raum.muchbeer.persistencektx.util.formatNights

class SleepViewModel( application: Application) :
                AndroidViewModel(application){

    private val database = getInstance(application).sleepDao

    private var tonight = MutableLiveData<SleepEntity?>()

    private val nights = database.getAllSleep()


    val nightString = Transformations.map(nights) { night->
        formatNights(night, application.resources)
    }

    //Sleep quality
    private val _sleep_quality_tracker = MutableLiveData<SleepEntity>()
    val sleep_quality_tracker : LiveData<SleepEntity>
        get() = _sleep_quality_tracker

    fun doneTrackingQuality() {
        _sleep_quality_tracker.value = null
    }
    init {
        initializeTonight()
    }

    private fun initializeTonight()  {
        viewModelScope.launch {
            tonight.value = retrieveNightFromDatabase()

        }
    }

    private suspend fun retrieveNightFromDatabase(): SleepEntity? {
        var night = database.getTonight()
        if (night?.stopSleep != night?.startSleep) {
            night = null
        }
        return night
    }

    private suspend fun clear() {
        database.clear()
    }

    private suspend fun update(night: SleepEntity) {
        database.update(night)
    }

    private suspend fun insert(night: SleepEntity) {
        database.insert(night)
    }

    val startButtonVisible = Transformations.map(tonight) {
        it==null
    }

    val stopButtonVisible = Transformations.map(tonight) {
        it !=null
    }

    val clearButtonVisible = Transformations.map(nights) {
        it?.isNotEmpty()
    }

    private val _snackBarEnabledOnClear = MutableLiveData<Boolean>()
        val snackBarEnabledOnClear : LiveData<Boolean>
            get() = _snackBarEnabledOnClear

    fun doneWithSnackBarEnabled() {
        _snackBarEnabledOnClear.value = null
    }
    /**
     * Executes when the START button is clicked.
     */
    fun onStartTracking() {
        viewModelScope.launch {
            // Create a new night, which captures the current time,
            // and insert it into the database.
            val newNight = SleepEntity()

            insert(newNight)

            tonight.value = retrieveNightFromDatabase()
        }
    }

    /**
     * Executes when the STOP button is clicked.
     */
    fun onStopTracking() {
        viewModelScope.launch {
            // In Kotlin, the return@label syntax is used for specifying which function among
            // several nested ones this statement returns from.
            // In this case, we are specifying to return from launch(),
            // not the lambda.
            val oldNight = tonight.value ?: return@launch


            // Update the night in the database to add the end time.
            oldNight.stopSleep = System.currentTimeMillis()
            Log.d("QualitySleep", "The value of id is ${oldNight.sleepID}")
            update(oldNight)
            _sleep_quality_tracker.value = oldNight
        }
    }

    /**
     * Executes when the CLEAR button is clicked.
     */
    fun onClear() {
        viewModelScope.launch {
            // Clear the database table.
            clear()

            // And clear tonight since it's no longer in the database
            tonight.value = null
            _snackBarEnabledOnClear.value = true
        }
    }
}
