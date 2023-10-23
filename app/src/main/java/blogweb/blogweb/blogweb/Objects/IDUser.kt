package blogweb.blogweb.blogweb.Objects

import com.google.gson.annotations.SerializedName

class IDUser(iduser: String) {

    @SerializedName("iduser")
    lateinit var iduser: String

    init {
        this.iduser = iduser
    }
}