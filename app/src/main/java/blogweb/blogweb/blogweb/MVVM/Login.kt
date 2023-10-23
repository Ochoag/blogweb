package blogweb.blogweb.blogweb.MVVM

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import blogweb.blogweb.blogweb.Objects.IDUser
import blogweb.blogweb.blogweb.Service.Service
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class Login : ViewModel() {
    private val service: Service
    private val idcl = MutableLiveData<IDUser>()
    fun getIdcl(): LiveData<IDUser> {
        return idcl
    }

     fun fetchLogin(usnemail: String, phn: String) {
        service.getLogin(usnemail, phn)
            ?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : DisposableSingleObserver<List<IDUser>>() {
                override fun onSuccess(value: List<IDUser>) {
                    if (value != null){
                        for (cmy in value) {
                            idcl.value = IDUser(cmy.iduser!!)
                        }
                    }
                }

                override fun onError(e: Throwable) {}
            })
    }

    init {
        service = Service()
        fetchLogin(usnemail = String(), phn = String())
    }
}
