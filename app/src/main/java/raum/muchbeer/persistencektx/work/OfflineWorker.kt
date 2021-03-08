package raum.muchbeer.persistencektx.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import raum.muchbeer.persistencektx.database.GeneralDatabase
import raum.muchbeer.persistencektx.database.GeneralDatabase.Companion.getInstance
import raum.muchbeer.persistencektx.repository.Repository
import retrofit2.HttpException

class OfflineWorker(val context: Context, val workParam: WorkerParameters) :
            CoroutineWorker(context, workParam){

    companion object {
        val WORK_NAME = "RefreshWorkerMuchbeer"
    }
    override suspend fun doWork(): Result {

        val database = getInstance(context)
        val repository = Repository(database)

        return try {
            repository.refreshVideo()
            Result.success()
        } catch (exception: HttpException) {
            Result.retry()
        }
    }
}