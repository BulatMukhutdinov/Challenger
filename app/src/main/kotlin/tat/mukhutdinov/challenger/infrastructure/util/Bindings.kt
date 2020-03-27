package tat.mukhutdinov.challenger.infrastructure.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.gms.common.SignInButton

@BindingAdapter("image")
fun setImage(imageView: ImageView, url: String?) {
    Glide.with(imageView)
        .load(url)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(8)))
        .into(imageView)
}

@BindingAdapter("onClicked")
fun setClickListener(button: SignInButton, onClicked: () -> Unit) {
    button.setOnClickListener { onClicked() }
}
