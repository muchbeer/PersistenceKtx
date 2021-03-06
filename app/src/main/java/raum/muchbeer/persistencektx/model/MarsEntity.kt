package raum.muchbeer.persistencektx.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MarsEntity(
    @SerializedName("id")
    val id : String,
    @SerializedName("img_src")
    val imgSrcUrl: String,
    @SerializedName("type")
    val type : String,
    @SerializedName("price")
    val price: Double
) : Parcelable {
    val isRental : Boolean
        get() = type == "rent"
}
