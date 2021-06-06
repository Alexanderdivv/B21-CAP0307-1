package com.project1.fatigueapplication.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataParcel(
        val iImage: Int,
        val iName: String,
        val iBangkitNumber: String,
        val iBangkitEmail: String,
) : Parcelable