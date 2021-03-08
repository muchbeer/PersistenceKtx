package raum.muchbeer.persistencektx.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import raum.muchbeer.persistencektx.databinding.OfflineItemBinding
import raum.muchbeer.persistencektx.domain.VideoEntity

class OfflineAdapter(val clickListener: OnClickListenerOffline) : ListAdapter<VideoEntity, OfflineAdapter.OfflineVH>(diffUtl)  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfflineVH {
       val inflater = LayoutInflater.from(parent.context)
        return OfflineVH(OfflineItemBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: OfflineVH, position: Int) {
        val videoEntity = getItem(position)
        holder.bind(videoEntity)
        holder.itemView.setOnClickListener {
            clickListener.onClick(videoEntity)
        }
    }
    class OfflineVH(val binding: OfflineItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(videoEntity: VideoEntity) {
            binding.videoEntity = videoEntity
            binding.executePendingBindings()
        }
    }

    companion object diffUtl : DiffUtil.ItemCallback<VideoEntity>() {

        override fun areItemsTheSame(oldItem: VideoEntity, newItem: VideoEntity): Boolean {
          return  oldItem ==newItem
        }

        override fun areContentsTheSame(oldItem: VideoEntity, newItem: VideoEntity): Boolean {
            return oldItem.description ==newItem.description
        }
    }

    class OnClickListenerOffline (val clickListener : (videoEntity: VideoEntity) ->Unit) {
        fun onClick(videoEntity: VideoEntity) = clickListener(videoEntity)
    }
}