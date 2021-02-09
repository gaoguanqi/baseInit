package com.maple.baselib.widget.imageloader

import android.content.Context

interface BaseImageLoaderStrategy <in T : ImageConfig> {
    fun loadImage(ctx: Context, config:T)
}