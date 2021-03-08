package raum.muchbeer.persistencektx.fragment.sleeptracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import raum.muchbeer.persistencektx.R
import raum.muchbeer.persistencektx.database.GeneralDatabase
import raum.muchbeer.persistencektx.databinding.FragmentSleepQualityBinding
import raum.muchbeer.persistencektx.viewmodel.SleepQualityViewModel
import raum.muchbeer.persistencektx.viewmodel.SleepQualityViewModelFactory

class SleepQualityFragment : Fragment() {

private lateinit var binding: FragmentSleepQualityBinding
private lateinit var viewModel: SleepQualityViewModel
private lateinit var viewModelFactory: SleepQualityViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sleep_quality, container, false)
        val application = requireNotNull(this.activity).application


        val databaseDao = GeneralDatabase.getInstance(application).sleepDao
        val argumentKey = SleepQualityFragmentArgs.fromBundle(requireArguments())
        viewModelFactory = SleepQualityViewModelFactory(argumentKey.keyLong, databaseDao)

        viewModel = ViewModelProvider(this, viewModelFactory).get(SleepQualityViewModel::class.java)

      binding.viewModelSleepQuality = viewModel
        binding.lifecycleOwner = this

        viewModel.navigateToSleepTracker.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
                this.findNavController().navigate(
                    SleepQualityFragmentDirections.actionSleepQualityFragmentToSleepTrackerFragment()
                )
                // Reset state to make sure we only navigate once, even if the device
                // has a configuration change.
                viewModel.doneNavigating()
            }
        })
        return binding.root
    }

}