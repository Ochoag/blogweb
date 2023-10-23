package blogweb.blogweb.blogweb.Service

import blogweb.blogweb.blogweb.Api.Api
import blogweb.blogweb.blogweb.Objects.CompleteEntries
import blogweb.blogweb.blogweb.Objects.EntriesContent
import blogweb.blogweb.blogweb.Objects.IDUser
import blogweb.blogweb.blogweb.Objects.User
import dagger.Module
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class Service @Singleton constructor() {
    private val api: Api
    fun getLogin(email: String?, phn: String?): Single<List<IDUser>> {
        return api.getLogin(email, phn)
    }

    fun getRegister(email: String?, phn: String?) : Single<List<User>> {
        return api.getRegister(email, phn)
    }

    fun getInsertEntries(iduser: String?, titulo: String?, autor: String?, fechapublicacion: String, contenido: String?): Single<List<CompleteEntries>> {
        return api.getInsertEntries(iduser, titulo, autor, fechapublicacion, contenido)
    }

    fun getEntries(iduser: String?, titulo: String?, autor: String?, fechapublicacion: String, contenido: String?): Single<List<CompleteEntries>> {
        return api.getEntries(iduser, titulo, autor, fechapublicacion, contenido)
    }

    fun getEntriesTitulo(iduser: String?, titulo: String?, autor: String?, fechapublicacion: String, contenido: String?): Single<List<CompleteEntries>> {
        return api.getEntriesTitulo(iduser, titulo, autor, fechapublicacion, contenido)
    }

    fun getEntriesAutor(iduser: String?, titulo: String?, autor: String?, fechapublicacion: String, contenido: String?): Single<List<CompleteEntries>> {
        return api.getEntriesAutor(iduser, titulo, autor, fechapublicacion, contenido)
    }

    fun getEntriesContenido(iduser: String?, titulo: String?, autor: String?, fechapublicacion: String, contenido: String?): Single<List<CompleteEntries>> {
        return api.getEntriesContenido(iduser, titulo, autor, fechapublicacion, contenido)
    }

    companion object {
        const val BASE_URL = "https://evotech.tech/webblog/"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        api = retrofit.create(Api::class.java)
    }
}