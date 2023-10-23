package blogweb.blogweb.blogweb.Objects

import com.google.gson.annotations.SerializedName

class EntriesContent(contenido : String) {

    @SerializedName("contenido")
    lateinit var contenido: String

    init {
        this.contenido = contenido
    }
}