package raum.muchbeer.persistencektx.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import raum.muchbeer.persistencektx.adapter.OnlineAdapter.*
import raum.muchbeer.persistencektx.databinding.OnlineItemBinding
import raum.muchbeer.persistencektx.model.MarsEntity

class OnlineAdapter(val onClickListener: OnClickListener) : ListAdapter<MarsEntity, OnlineViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnlineViewHolder {

     return  OnlineViewHolder(OnlineItemBinding.inflate(LayoutInflater.from(parent.context)))
     /*   val inflater = LayoutInflater.from(parent.context)
       val binding = DataBindingUtil.inflate<OnlineItemBinding>(inflater, R.layout.online_item, parent, false)
        return OnlineViewHolder(binding)*/
    }

    override fun onBindViewHolder(holder: OnlineViewHolder, position: Int) {
          val marsEntity = getItem(position)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(marsEntity)      }

        holder.bind(marsEntity)
    }

    class OnlineViewHolder(val binding: OnlineItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(marsEntity: MarsEntity) {
            binding.property = marsEntity

            binding.executePendingBindings()
        }

    }

    class OnClickListener(val clickListener: (marsEntity:MarsEntity) -> Unit) {
        fun onClick(marsEntityFun:MarsEntity) = clickListener(marsEntityFun)
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [MarsProperty]
     * has been updated.
     */
    companion object diffUtil : DiffUtil.ItemCallback<MarsEntity>() {
        override fun areItemsTheSame(oldItem: MarsEntity, newItem: MarsEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MarsEntity, newItem: MarsEntity): Boolean {
            return oldItem.id == newItem.id
        }
    }


}