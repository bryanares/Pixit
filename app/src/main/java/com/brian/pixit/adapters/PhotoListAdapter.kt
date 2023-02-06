package com.brian.pixit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.brian.pixit.databinding.PhotoItemBinding
import com.brian.pixit.models.PhotoItem
import com.squareup.picasso.Picasso

class PhotoListAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<PhotoItem, PhotoListAdapter.PhotoViewHolder>(PhotoDiffUtil) {

    companion object PhotoDiffUtil : DiffUtil.ItemCallback<PhotoItem>() {
        override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem.id == newItem.id
        }
    }

    inner class PhotoViewHolder(private val binding: PhotoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(photoItem: PhotoItem) {
            Picasso.get()
                .load(photoItem.download_url)
                .into(binding.pixitImageView)

            binding.authorLabel.text = photoItem.author.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            PhotoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photoItem = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(photoItem)
        }
        holder.bind(photoItem)
    }
}

class OnClickListener(val clickListener: (photo: PhotoItem) -> Unit) {
    fun onClick(photo: PhotoItem) = clickListener(photo)
}