package ru.alexandrorlov.moviescompose.model

import android.os.Parcel
import android.os.Parcelable
data class Movie(
    val id: Int,
    val name: String,
    val photo: String,/*можно добавать по умолчанию ссылку на заглушку из drawable*/
    val dateRelease: String,
    val rating: String,
    val ageRating: String,
    val description: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(photo)
        parcel.writeString(dateRelease)
        parcel.writeString(rating)
        parcel.writeString(ageRating)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}
