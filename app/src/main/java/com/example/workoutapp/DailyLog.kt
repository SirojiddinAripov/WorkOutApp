package com.example.workoutapp

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable

class DailyLog(
    val date: String?,
    val time: String?,
    val dayRating: Int,
    val imageSrcUri: Uri,
    val title: String?,
    val description: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readParcelable(Uri::class.java.classLoader)!!,
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date)
        parcel.writeString(time)
        parcel.writeInt(dayRating)
        parcel.writeParcelable(imageSrcUri, flags)
        parcel.writeString(title)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DailyLog> {
        override fun createFromParcel(parcel: Parcel): DailyLog {
            return DailyLog(parcel)
        }

        override fun newArray(size: Int): Array<DailyLog?> {
            return arrayOfNulls(size)
        }
    }

}