package com.rinc.young.schoolumbrellarent.models

import com.google.gson.annotations.SerializedName

/**
 * Created by young on 2017-07-24/오후 12:06
 * This Project is SchoolUmbrellaRent
 */
class UmbrellaList {
    @SerializedName("data")
    lateinit var umbrellas: List<Umbrella>
}