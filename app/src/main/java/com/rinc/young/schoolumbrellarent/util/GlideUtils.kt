package com.rinc.young.schoolumbrellarent.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by young on 2017-07-17/오후 3:51
 * This Project is SchoolUmbrellaRent
 */
object GlideUtils {
    fun setImage(ctx: Context, id: Int, image: ImageView) {
        Glide.with(ctx).load(id).into(image)
    }

}