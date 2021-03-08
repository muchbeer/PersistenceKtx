package raum.muchbeer.persistencektx.util

import android.app.Application
import android.os.Build
import androidx.work.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import raum.muchbeer.persistencektx.work.OfflineWorker
import raum.muchbeer.persistencektx.work.OfflineWorker.Companion.WORK_NAME
import timber.log.Timber
import java.util.concurrent.TimeUnit

class App : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        lateInitialize()
    }

    private fun lateInitialize() {
        applicationScope.launch {
           setupRecurringWork()
        }
    }

    private fun setupRecurringWork() {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(true)
            .apply {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setRequiresDeviceIdle(true) }
            }.build()


        val repeatingRequest = PeriodicWorkRequestBuilder<OfflineWorker>(
            1, TimeUnit.DAYS
        ).setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest
        )
    }
}