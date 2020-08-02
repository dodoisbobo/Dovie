package com.dodosetio.dovie.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CheckOut (
    var kursi:String?="",
    var harga:String?="",
    var balance:String?=""
): Parcelable