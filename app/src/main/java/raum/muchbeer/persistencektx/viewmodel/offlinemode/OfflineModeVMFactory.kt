package raum.muchbeer.persistencektx.viewmodel.offlinemode

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class OfflineModeVMFactory(val app: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OfflineModelVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OfflineModelVM(app) as T
        }

        throw IllegalArgumentException("Unknown ViewModel....")
    }
}