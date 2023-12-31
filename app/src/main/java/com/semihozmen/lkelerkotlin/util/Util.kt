package com.semihozmen.lkelerkotlin.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.semihozmen.lkelerkotlin.R

fun ImageView.downloadFromUrl(url:String?,progressDrawable: CircularProgressDrawable){

    val options = RequestOptions()
        .placeholder(progressDrawable) // Rsim yüklenirken progress bar oluşturma
        .error(R.mipmap.ic_launcher_round) // Hata olursa gösterilir

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}

fun placeHolderProgressBar(context: Context):CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth  = 8f
        centerRadius = 40f
        start()
    }
}

@BindingAdapter("android:downloadUrlFromDataBinding")
fun downloadImageFromDataBinding(view:ImageView,url: String?){
    view.downloadFromUrl(url, placeHolderProgressBar(view.context))
}