package blogweb.blogweb.blogweb.Activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import blogweb.blogweb.blogweb.App.BaseApplication
import blogweb.blogweb.blogweb.Fragments.BlankFragment
import blogweb.blogweb.blogweb.MVVM.Login
import blogweb.blogweb.blogweb.MVVM.Register
import blogweb.blogweb.blogweb.Objects.CompleteEntries
import blogweb.blogweb.blogweb.R
import blogweb.blogweb.blogweb.adapters.EntriesAdapter
import blogweb.blogweb.blogweb.db.DatabaseModule
import blogweb.blogweb.blogweb.db.entity.Users
import blogweb.blogweb.blogweb.db.entity.Webblog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginApp : AppCompatActivity() {
    private var viewModelRegister: Register? = null
    private var viewModelLogin: Login? = null
    var usch : TextView? = null
    private val PREFS_KEY = "mypreferences"

    @Inject
    lateinit var databaseModule : DatabaseModule
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)


        val appComponent = (application as BaseApplication).getAppComponent()
        appComponent.inject(this)


        var email = findViewById<EditText>(R.id.email)
        var numtel = findViewById<EditText>(R.id.numtel)
        var ins = findViewById<Button>(R.id.ins)
        var reg = findViewById<Button>(R.id.reg)
        usch = findViewById<TextView>(R.id.usercheck)

        val iduser = leerValor(this, "iduser")

        if(iduser!!.isNotEmpty()){
            val i1 = Intent(this, MainActivity::class.java)
            startActivity(i1)
        }else {
            ins!!.setOnClickListener {
                if (email.text.toString().isNotEmpty() && numtel.text.toString().isNotEmpty()) {
                    val connectivityManager: ConnectivityManager = getSystemService(
                        AppCompatActivity.CONNECTIVITY_SERVICE
                    ) as ConnectivityManager

                    if (connectivityManager != null) {

                        val capabilities: NetworkCapabilities? =
                            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

                        if (capabilities == null) {
                            GlobalScope.launch(Dispatchers.IO) {
                                val a = databaseModule.provideAppDatabase(applicationContext)!!.usersDAO()!!
                                    .findUser(email.text.toString(), numtel.text.toString())

                                if (a.isNotEmpty()) {
                                    for (user in a) {
                                        Log.d("TAG", "Usuario ID: ${user.iduser}")
                                        guardarValor(applicationContext, "idlocal", user.iduser.toString())

                                        val i1 = Intent(applicationContext, MainActivity::class.java)
                                        startActivity(i1)
                                    }
                                    Log.d("TAG", "Datos mostrados")
                                } else {
                                    Log.d("TAG", "NO HAY DATOS")
                                    GlobalScope.launch (Dispatchers.Main) {
                                        Toast.makeText(
                                            applicationContext,
                                            "Usuario no registrado",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            }
                        }

                        if (capabilities != null) {
                            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                                GlobalScope.launch (Dispatchers.IO) {
                                val a = databaseModule.provideAppDatabase(applicationContext)!!.usersDAO()!!
                                    .findUser(email.text.toString(), numtel.text.toString())

                                if (a.isNotEmpty()) {
                                    for (user in a) {
                                        Log.d("TAG", "Usuario ID: ${user.iduser}")
                                        guardarValor(
                                            applicationContext,
                                            "idlocal",
                                            user.iduser.toString()
                                        )

                                        val i1 =
                                            Intent(applicationContext, MainActivity::class.java)
                                        startActivity(i1)
                                    }
                                    Log.d("TAG", "Datos mostrados")
                                } else {
                                    Log.d("TAG", "NO HAY DATOS")
                                }
                            }

                                viewModelLogin =
                                    ViewModelProvider(this).get(Login::class.java)

                                viewModelLogin!!.fetchLogin(
                                    email.text.toString(),
                                    numtel.text.toString()
                                )


                                observeViewModelLogin()
                            }
                        }
                    }
                } else {
                    Toast.makeText(this, "Necesitas llenar los dos campos", Toast.LENGTH_LONG)
                        .show()
                }
            }

            reg!!.setOnClickListener {
                if (email.text.toString().isNotEmpty() && numtel.text.toString().isNotEmpty()) {

                    val connectivityManager: ConnectivityManager = getSystemService(
                        AppCompatActivity.CONNECTIVITY_SERVICE
                    ) as ConnectivityManager

                    if (connectivityManager != null) {

                        val capabilities: NetworkCapabilities? =
                            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

                        if (capabilities == null) {
                            GlobalScope.launch(Dispatchers.IO) {
                                val newUser = Users()
                                newUser.email = email.text.toString()
                                newUser.phn = numtel.text.toString()

                                GlobalScope.launch(Dispatchers.IO) {
                                    val t = databaseModule.provideAppDatabase(applicationContext)!!.usersDAO()!!.insert(newUser)

                                    if(t > 0){
                                        Log.d("TAG", "INSERCION CON EXITO")
                                        GlobalScope.launch (Dispatchers.Main) {
                                            Toast.makeText(
                                                applicationContext,
                                                "Usuario registrado con exito",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    }else{
                                        Log.d("TAG", "NO EXITO")
                                    }
                                }
                            }
                        }

                        if (capabilities != null) {
                            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                                GlobalScope.launch(Dispatchers.IO) {
                                    val newUser = Users()
                                    newUser.email = email.text.toString()
                                    newUser.phn = numtel.text.toString()

                                    GlobalScope.launch(Dispatchers.IO) {
                                        val t = databaseModule.provideAppDatabase(applicationContext)!!.usersDAO()!!.insert(newUser)

                                        if(t > 0){
                                            Log.d("TAG", "INSERCION CON EXITO")
                                            GlobalScope.launch (Dispatchers.Main) {
                                                Toast.makeText(
                                                    applicationContext,
                                                    "Usuario registrado con exito",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                        }else{
                                            Log.d("TAG", "NO EXITO")
                                        }
                                    }
                                }

                                viewModelRegister =
                                    ViewModelProvider(this).get(Register::class.java)

                                viewModelRegister!!.fetchRegister(
                                    email.text.toString(),
                                    numtel.text.toString()
                                )


                                observeViewModelRegister()
                            }
                        }
                    }
                } else {
                    Toast.makeText(this, "Necesitas llenar los dos campos", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    fun observeViewModelLogin(){
        viewModelLogin!!.getIdcl().observe(this) { iduser ->
                if (!iduser.iduser.contentEquals("not exist")) {
                    usch!!.text = ""
                    guardarValor(this, "iduser", iduser.iduser)
                    val i1 = Intent(this, MainActivity::class.java)
                    startActivity(i1)
                } else {
                    usch!!.text = "Usuario no registrado"
                }
        }
    }

    fun observeViewModelRegister(){
        viewModelRegister!!.getIdcl().observe(this) { iduser ->
            if(iduser.email.contentEquals("exist") && iduser.phn.contentEquals("exist")){
                usch!!.text = "Usuario registrado con exito"
            }
        }
    }

    fun guardarValor(context: Context, keyPref: String?, valor: String?) {
        val settings = context.getSharedPreferences(PREFS_KEY, AppCompatActivity.MODE_PRIVATE)
        val editor: SharedPreferences.Editor
        editor = settings.edit()
        editor.putString(keyPref, valor)
        editor.commit()
    }

    fun leerValor(context: Context, keyPref: String?): String? {
        val preferences = context.getSharedPreferences(PREFS_KEY, MODE_PRIVATE)
        return preferences.getString(keyPref, "")
    }
}