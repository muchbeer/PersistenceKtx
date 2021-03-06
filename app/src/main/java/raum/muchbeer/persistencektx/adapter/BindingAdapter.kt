package raum.muchbeer.persistencektx.adapter

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import raum.muchbeer.persistencektx.R
import raum.muchbeer.persistencektx.domain.VideoEntity
import raum.muchbeer.persistencektx.network.MarsApiStatus
import raum.muchbeer.persistencektx.model.MarsEntity


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MarsEntity>?) {
    val adapter = recyclerView.adapter as OnlineAdapter
    adapter.submitList(data)
}

@BindingAdapter("listDataOffline")
fun bindRecyclerViewOffline(recyclerView: RecyclerView, data: List<VideoEntity>?) {
    val adapter = recyclerView.adapter as OfflineAdapter
    adapter.submitList(data)
}

/**
 * Uses the Glide library to load an image by URL into an [ImageView]
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            ).into(imgView)
    }
}

@BindingAdapter("goneIfNotNull")
fun progressView(progressBar: ProgressBar, videoTrue: Boolean) {
    if (videoTrue) {
        progressBar.visibility = View.GONE
    } else {
        progressBar.visibility = View.VISIBLE
    }
}

@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView, status: MarsApiStatus?) {
    when (status) {
        MarsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MarsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MarsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}