package raum.muchbeer.persistencektx.fragment.onlinemode

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import raum.muchbeer.persistencektx.R
import raum.muchbeer.persistencektx.adapter.OnlineAdapter
import raum.muchbeer.persistencektx.network.MarsApiFilter
import raum.muchbeer.persistencektx.databinding.FragmentOnlineBinding
import raum.muchbeer.persistencektx.viewmodel.onlinefragment.OnlineFragmentVM


class OnlineFragment : Fragment() {
    private lateinit var binding : FragmentOnlineBinding
    private lateinit var adapter : OnlineAdapter

    private val viewModel: OnlineFragmentVM by lazy {
        ViewModelProvider(this).get(OnlineFragmentVM::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // binding = DataBindingUtil.inflate(inflater, R.layout.fragment_online, container, false)

        binding = FragmentOnlineBinding.inflate(inflater)

        adapter = OnlineAdapter(OnlineAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        binding.photosGrid.adapter = adapter

        // for another navigation event.
        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer { marsEntity ->
            if ( marsEntity !=null ) {
                // Must find the NavController from the Fragment
               this.findNavController().navigate(
                   OnlineFragmentDirections.actionShowDetail(
                       marsEntity
                   )
               )
                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                viewModel.displayPropertyDetailsComplete()
            }
        })
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.online, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.updateFilter(
            when (item.itemId) {
                R.id.show_rent_menu -> MarsApiFilter.SHOW_RENT
                R.id.show_buy_menu -> MarsApiFilter.SHOW_BUY
                else -> MarsApiFilter.SHOW_ALL
            }
        )
        return true
    }
}