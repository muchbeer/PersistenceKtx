package raum.muchbeer.persistencektx.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import raum.muchbeer.persistencektx.R
import raum.muchbeer.persistencektx.database.SleepDatabase
import raum.muchbeer.persistencektx.databinding.FragmentSleepTrackerBinding
import raum.muchbeer.persistencektx.viewmodel.SleepViewModel
import raum.muchbeer.persistencektx.viewmodel.SleepViewModelFactory


class SleepTrackerFragment : Fragment() {

    private lateinit var binding: FragmentSleepTrackerBinding
    private lateinit var viewModel: SleepViewModel
    private lateinit var viewModelFactory: SleepViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_sleep_tracker, container, false)

        val application = requireNotNull(this.activity).application
       viewModelFactory = SleepViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SleepViewModel::class.java)

        binding.sleepTrackerViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.sleep_quality_tracker.observe(viewLifecycleOwner, Observer { night->
            night?.let {
                Log.i("SleepTrackerID", "The id is : ${night.sleepID}")
             //   this.findNavController().navigate(SleepTrackerFragmentDirections.sleepTrackFragToSleepQualFrag(night.sleepID))
                this.findNavController().navigate(SleepTrackerFragmentDirections.sleepTrackFragToSleepQualFrag(night.sleepID))
                viewModel.doneTrackingQuality()
            }

        })

        viewModel.snackBarEnabledOnClear.observe(viewLifecycleOwner, Observer {
            if(it ==true) {
                Snackbar.make(requireActivity()!!.findViewById(android.R.id.content), "all data is clear",
                                       Snackbar.LENGTH_LONG).show()

                viewModel.doneWithSnackBarEnabled()
            }

        })
        return binding.root
    }



}