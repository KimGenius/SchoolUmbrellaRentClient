package com.rinc.young.schoolumbrellarent.util

import android.content.Context
import android.widget.Toast

/**
 * Created by young on 2017-07-17/오후 3:37
 * This Project is SchoolUmbrellaRent
 */
object ToastUtils {
    fun show(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}