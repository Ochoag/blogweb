package blogweb.blogweb.blogweb.MVVM

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import blogweb.blogweb.blogweb.Objects.CompleteEntries
import blogweb.blogweb.blogweb.Service.Service
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class EntriesContenido : ViewModel() {
    private val service: Service
    private val ent = MutableLiveData<CompleteEntries>()
    fun getEnt(): LiveData<CompleteEntries> {
        return ent
    }

     fun fetchEntries(iduser: String, titulo: String, autor: String, fechapublicacion: String, contenido: String) {
        service.getEntriesContenido(iduser, titulo, autor, fechapublicacion, contenido)
            ?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : DisposableSingleObserver<List<CompleteEntries>>() {
                override fun onSuccess(value: List<CompleteEntries>) {
                    if (value != null){
                        for (cmy in value) {
                            ent.value = CompleteEntries(cmy.iduser, cmy.titulo, cmy.autor, cmy.fechapublicacion, cmy.contenido)
                        }
                    }
                }

                override fun onError(e: Throwable) {}
            })
    }

    init {
        service = Service()
        fetchEntries(iduser = String(), titulo = String(), autor = String(), fechapublicacion = String(), contenido = String())
    }
}
