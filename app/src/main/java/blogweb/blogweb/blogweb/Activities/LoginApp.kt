package blogweb.blogweb.blogweb.Activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import blogweb.blogweb.blogweb.App.BaseApplication
import blogweb.blogweb.blogweb.MVVM.Login
import blogweb.blogweb.blogweb.MVVM.Register
import blogweb.blogweb.blogweb.R

class LoginApp : AppCompatActivity() {
    private var viewModelRegister: Register? = null
    private var viewModelLogin: Login? = null
    var usch : TextView? = null
    private val PREFS_KEY = "mypreferences"
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
                    viewModelLogin =
                        ViewModelProvider(this).get(Login::class.java)

                    viewModelLogin!!.fetchLogin(
                        email.text.toString(),
                        numtel.text.toString()
                    )

                    observeViewModelLogin()
                } else {
                    Toast.makeText(this, "Necesitas llenar los dos campos", Toast.LENGTH_LONG)
                        .show()
                }
            }

            reg!!.setOnClickListener {
                if (email.text.toString().isNotEmpty() && numtel.text.toString().isNotEmpty()) {
                    viewModelRegister =
                        ViewModelProvider(this).get(Register::class.java)

                    viewModelRegister!!.fetchRegister(
                        email.text.toString(),
                        numtel.text.toString()
                    )

                    observeViewModelRegister()

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