package blogweb.blogweb.blogweb.MVVM

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import blogweb.blogweb.blogweb.Objects.IDUser
import blogweb.blogweb.blogweb.Objects.User
import blogweb.blogweb.blogweb.Service.Service
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class Register : ViewModel() {
    private val service: Service
    private val idcl = MutableLiveData<User>()
    fun getIdcl(): LiveData<User> {
        return idcl
    }

     fun fetchRegister(usnemail: String, phn: String) {
        service.getRegister(usnemail, phn)
            ?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : DisposableSingleObserver<List<User>>() {
                override fun onSuccess(value: List<User>) {
                    if (value != null){
                        for (cmy in value) {
                            idcl.value = User(cmy.email!!, cmy.phn!!)
                        }
                    }
                }

                override fun onError(e: Throwable) {}
            })
    }

    init {
        service = Service()
        fetchRegister(usnemail = String(), phn = String())
    }
}
