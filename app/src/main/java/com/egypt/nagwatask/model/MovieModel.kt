package com.egypt.nagwatask.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


class MovieModel : ArrayList<MovieModel.MovieModelItem>() {
    data class MovieModelItem(
        @SerializedName("id")
        var id: Int = 0,
        @SerializedName("type")
        var type: String = "",
        @SerializedName("url")
        var url: String = "",
        @SerializedName("name")
        var name: String = "",
        @SerializedName("status")
        var status: String = "Not Downloaded"
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(id)
            parcel.writeString(type)
            parcel.writeString(url)
            parcel.writeString(name)
            parcel.writeString(status)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<MovieModelItem> {
            override fun createFromParcel(parcel: Parcel): MovieModelItem {
                return MovieModelItem(parcel)
            }

            override fun newArray(size: Int): Array<MovieModelItem?> {
                return arrayOfNulls(size)
            }
        }
    }
}