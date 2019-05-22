package com.arsonist.here.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arsonist.here.PhotoMetadata
import com.arsonist.here.R
import com.bumptech.glide.Glide
import com.l4digital.fastscroll.FastScroller
import kotlinx.android.synthetic.main.item_photo_view.view.*

class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val photoView = itemView.photoView

    fun loadView(photoMetadata: PhotoMetadata) {
        Glide.with(itemView.context)
            .load(photoMetadata.data)
            .centerCrop()
            .thumbnail(0.1f)
            .into(photoView)
    }
}

class PhotoRecyclerViewAdapter : RecyclerView.Adapter<PhotoViewHolder>(),
    FastScroller.SectionIndexer {

    private val photoMetadataList = mutableListOf<PhotoMetadata>()

    fun addPhoto(photoMetadata: PhotoMetadata) {
        photoMetadataList.add(photoMetadata)
        notifyItemInserted(photoMetadataList.size - 1)
    }

    override fun getSectionText(position: Int): CharSequence {
        return "$position"
    }
    // 설명 뜨는거 수정할 것
    // override fun getSectionText(position: Int) = photoMetadataList[position].location.toString()

    override fun getItemCount() = photoMetadataList.size

    override fun onBindViewHolder(viewHolder: PhotoViewHolder, position: Int) {
        photoMetadataList.getOrNull(position)?.let {
            viewHolder.loadView(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_photo_view, parent, false))
    }
}