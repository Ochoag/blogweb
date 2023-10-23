package blogweb.blogweb.blogweb.Api

import blogweb.blogweb.blogweb.Objects.CompleteEntries
import blogweb.blogweb.blogweb.Objects.EntriesContent
import blogweb.blogweb.blogweb.Objects.IDUser
import blogweb.blogweb.blogweb.Objects.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("login.php")
    fun getLogin(
        @Query("email") email: String?,
        @Query("phn") phn: String?
    ): Single<List<IDUser>>

    @GET("register.php")
    fun getRegister(
        @Query("email") email: String?,
        @Query("phn") phn: String?
    ): Single<List<User>>

    @GET("insertentries.php")
    fun getInsertEntries(
        @Query("iduser") iduser : String?,
        @Query("titulo") titulo : String?,
        @Query("autor") autor : String?,
        @Query("fechapublicacion") fechapublicacion : String?,
        @Query("contenido") contenido : String?
    ): Single<List<CompleteEntries>>

    @GET("entries.php")
    fun getEntries(
        @Query("iduser") iduser : String?,
        @Query("titulo") titulo : String?,
        @Query("autor") autor : String?,
        @Query("fechapublicacion") fechapublicacion : String?,
        @Query("contenido") contenido : String?
    ): Single<List<CompleteEntries>>

    @GET("entriestitulo.php")
    fun getEntriesTitulo(
        @Query("iduser") iduser : String?,
        @Query("titulo") titulo : String?,
        @Query("autor") autor : String?,
        @Query("fechapublicacion") fechapublicacion : String?,
        @Query("contenido") contenido : String?
    ): Single<List<CompleteEntries>>

    @GET("entriesautor.php")
    fun getEntriesAutor(
        @Query("iduser") iduser : String?,
        @Query("titulo") titulo : String?,
        @Query("autor") autor : String?,
        @Query("fechapublicacion") fechapublicacion : String?,
        @Query("contenido") contenido : String?
    ): Single<List<CompleteEntries>>

    @GET("entriescontenido.php")
    fun getEntriesContenido(
        @Query("iduser") iduser : String?,
        @Query("titulo") titulo : String?,
        @Query("autor") autor : String?,
        @Query("fechapublicacion") fechapublicacion : String?,
        @Query("contenido") contenido : String?
    ): Single<List<CompleteEntries>>
}


