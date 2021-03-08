package raum.muchbeer.persistencektx.fragment.offlinemode

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import raum.muchbeer.persistencektx.R
import raum.muchbeer.persistencektx.adapter.OfflineAdapter
import raum.muchbeer.persistencektx.databinding.FragmentOfflineBinding
import raum.muchbeer.persistencektx.domain.VideoEntity
import raum.muchbeer.persistencektx.viewmodel.offlinemode.OfflineModeVMFactory
import raum.muchbeer.persistencektx.viewmodel.offlinemode.OfflineModelVM


class OfflineFragment : Fragment() {

    private lateinit var binding: FragmentOfflineBinding
    private lateinit var viewModel : OfflineModelVM
    private lateinit var viewModelFactory: OfflineModeVMFactory
    private lateinit var adapter: OfflineAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOfflineBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application
        viewModelFactory = OfflineModeVMFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(OfflineModelVM::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        adapter = OfflineAdapter(OfflineAdapter.OnClickListenerOffline {
            Toast.makeText(requireActivity(), "Clicked: ${it.title}", Toast.LENGTH_LONG).show()


  /*          val packageManager = context?.packageManager ?: return@VideoClick

            // Try to generate a direct intent to the YouTube app
            var intent = Intent(Intent.ACTION_VIEW, it.launchUri)
            if(intent.resolveActivity(packageManager) == null) {
                // YouTube app isn't found, use the web url
                intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.url))
            }

            startActivity(intent)*/

        })

        binding.recyclerView.adapter = adapter

        viewModel.playlistDB.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                viewModel.initiateProgressBar()
                adapter.submitList(it)
            }
        })


        return binding.root
    }

    /**
     * Helper method to generate YouTube app links
     */
    private val VideoEntity.launchUri: Uri
        get() {
            val httpUri = Uri.parse(url)
            return Uri.parse("vnd.youtube:" + httpUri.getQueryParameter("v"))
        }


}