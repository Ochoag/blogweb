package blogweb.blogweb.blogweb.Objects

import com.google.gson.annotations.SerializedName

class User (email: String, phn: String) {

    @SerializedName("email")
    lateinit var email : String
    @SerializedName("phn")
    lateinit var phn : String

    init {
        this.email = email
        this.phn = phn
    }
}