package raum.muchbeer.persistencektx.viewmodel.onlinefragment

import android.app.Application
import androidx.lifecycle.*
import raum.muchbeer.persistencektx.R
import raum.muchbeer.persistencektx.model.MarsEntity
import java.lang.IllegalArgumentException

class OnlineDetailFragVMFactory(val marsEntity: MarsEntity, val app: Application) :ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OnlineDetailFragmentVM::class.java)) {
            return OnlineDetailFragmentVM(marsEntity,app = app) as T
        }

        throw IllegalArgumentException("Unknown ViewModel exception...")
    }


}