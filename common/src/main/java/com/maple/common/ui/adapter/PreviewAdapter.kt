package com.maple.common.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.github.chrisbanes.photoview.PhotoView
import com.maple.common.R
import com.maple.common.base.BaseAdapter
import com.maple.common.base.BaseViewHolder
import com.maple.common.ext.layoutInflater
import com.maple.common.ext.loadImage

class PreviewAdapter(context: Context):BaseAdapter<String, PreviewAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = context.layoutInflater.inflate(R.layout.item_preview, parent, false)
        val holder: ViewHolder = ViewHolder(view)
        return holder
    }


    inner class ViewHolder(itemView: View):BaseViewHolder<String>(itemView){
        private val pvImg: PhotoView = itemView.findViewById(R.id.pv_img)
        override fun setData(pos: Int, data: String?) {
            data?.let {url ->
                pvImg.loadImage(url)
            }
        }
    }

}