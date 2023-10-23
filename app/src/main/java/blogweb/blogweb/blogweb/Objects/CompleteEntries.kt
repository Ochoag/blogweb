package blogweb.blogweb.blogweb.Objects

import com.google.gson.annotations.SerializedName

class CompleteEntries (iduser: String, titulo: String, autor: String, fechapublicacion: String, contenido: String) {

    @SerializedName("iduser")
    lateinit var iduser : String
    @SerializedName("titulo")
    lateinit var titulo : String
    @SerializedName("autor")
    lateinit var autor : String
    @SerializedName("fechapublicacion")
    lateinit var fechapublicacion : String
    @SerializedName("contenido")
    lateinit var contenido: String

    init {
        this.titulo = titulo
        this.autor = autor
        this.fechapublicacion = fechapublicacion
        this.contenido = contenido
        this.iduser = iduser
    }
}