package raum.muchbeer.persistencektx.viewmodel.onlinefragment

import android.app.Application
import androidx.lifecycle.*
import raum.muchbeer.persistencektx.R
import raum.muchbeer.persistencektx.model.MarsEntity

class OnlineDetailFragmentVM(val marsEntity: MarsEntity, val app: Application) : AndroidViewModel(app) {

    private val _selectedMars = MutableLiveData<MarsEntity>()

    val selectedMars : LiveData<MarsEntity>
        get() = _selectedMars

    init {
        _selectedMars.value = marsEntity
    }

    // The displayPropertyPrice formatted Transformation Map LiveData, which displays the sale
    // or rental price.
    val displayPropertyPrice = Transformations.map(selectedMars) {
        app.applicationContext.getString(
            when (it.isRental) {
                true -> R.string.display_price_monthly_rental
                false -> R.string.display_price
            }, it.price)
    }

    // The displayPropertyType formatted Transformation Map LiveData, which displays the
    // "For Rent/Sale" String
    val displayPropertyType = Transformations.map(selectedMars) {
        app.applicationContext.getString(
            R.string.display_type,
            app.applicationContext.getString(
                when(it.isRental) {
                    true -> R.string.type_rent
                    false -> R.string.type_sale
                }))
    }

}