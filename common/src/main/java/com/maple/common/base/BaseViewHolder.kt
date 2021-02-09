package com.maple.common.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.maple.common.R

abstract class BaseViewHolder<T:Any>(itemView: View): RecyclerView.ViewHolder(itemView){

    val itemRoot: View? = itemView.findViewById(R.id.common_item_root)

    abstract fun setData(pos:Int,data:T?)

}