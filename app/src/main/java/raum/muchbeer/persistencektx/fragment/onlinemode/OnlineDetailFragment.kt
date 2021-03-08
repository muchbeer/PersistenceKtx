package raum.muchbeer.persistencektx.fragment.onlinemode

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import raum.muchbeer.persistencektx.databinding.FragmentOnlineDetailBinding
import raum.muchbeer.persistencektx.viewmodel.onlinefragment.OnlineDetailFragVMFactory
import raum.muchbeer.persistencektx.viewmodel.onlinefragment.OnlineDetailFragmentVM


class OnlineDetailFragment : Fragment() {

        private lateinit var binding: FragmentOnlineDetailBinding
        private lateinit var viewModel: OnlineDetailFragmentVM
        private lateinit var viewModelFactory: OnlineDetailFragVMFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOnlineDetailBinding.inflate(inflater)


        binding.lifecycleOwner = this
        val marsEntityArgs = OnlineDetailFragmentArgs.fromBundle(requireArguments()).marsParcelable

        val application = requireNotNull(this.activity).application
        viewModelFactory = OnlineDetailFragVMFactory(marsEntityArgs, application)
       viewModel = ViewModelProvider(this, viewModelFactory).get(OnlineDetailFragmentVM::class.java)

        binding.viewModel =viewModel
            viewModel.selectedMars.observe(viewLifecycleOwner, Observer {

        })
        return binding.root
    }

}