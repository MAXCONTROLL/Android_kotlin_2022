package com.alanzlykh.hw2_photo_gallary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.load
import com.alanzlykh.hw2_photo_gallary.GalleryItem
import com.alanzlykh.hw2_photo_gallary.R
import com.alanzlykh.hw2_photo_gallary.databinding.ListItemGalleryBinding

class PhotoViewHolder(
    private val binding: ListItemGalleryBinding
): RecyclerView.ViewHolder(binding.root) {
    private val imageLoader = ImageLoader.Builder(binding.root.context)
        .components { add(GifDecoder.Factory()) }
        .build()

    fun bind(photoItem: GalleryItem?) {
        binding.ivPhoto.load(photoItem?.url, imageLoader = imageLoader) {
            placeholder(R.drawable.image_placeholder)
        }
    }
}

class PhotoListAdapter(diffCallback: DiffUtil.ItemCallback<GalleryItem>) :
    PagingDataAdapter<GalleryItem, PhotoViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemGalleryBinding.inflate(inflater, parent, false)
        return PhotoViewHolder(binding)
    }

}

object PhotoComparator : DiffUtil.ItemCallback<GalleryItem>() {
    override fun areItemsTheSame(oldItem: GalleryItem, newItem: GalleryItem): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: GalleryItem, newItem: GalleryItem): Boolean {
        return oldItem == newItem
    }
}