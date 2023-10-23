package blogweb.blogweb.blogweb.MVVM

import androidx.lifecycle.ViewModel
import blogweb.blogweb.blogweb.Objects.CompleteEntries
import blogweb.blogweb.blogweb.Objects.IDUser
import blogweb.blogweb.blogweb.Service.Service
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class InsertEntries : ViewModel() {
    private val service: Service


     fun fetchInsertEntries(iduser: String, titulo: String, autor: String, fechapublicacion: String, contenido: String) {
        service.getInsertEntries(iduser, titulo, autor, fechapublicacion, contenido)
            ?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : DisposableSingleObserver<List<CompleteEntries>>() {
                override fun onSuccess(value: List<CompleteEntries>) {
                }

                override fun onError(e: Throwable) {}
            })
    }

    init {
        service = Service()
        fetchInsertEntries(iduser = String(), titulo = String(), autor = String(), fechapublicacion = String(), contenido = String())
    }
}
