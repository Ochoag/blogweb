package blogweb.blogweb.blogweb.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import blogweb.blogweb.blogweb.App.BaseApplication
import blogweb.blogweb.blogweb.MVVM.InsertEntries
import blogweb.blogweb.blogweb.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


class BlankFragment3 : Fragment() {
    private var viewModelInsertEntries: InsertEntries? = null
    private val PREFS_KEY = "mypreferences"
    val c = Calendar.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_blank3, container, false)

        val baseApplication = requireActivity().application as BaseApplication
        val appComponent = baseApplication.getAppComponent()
        appComponent.inject(this)

        var titulo = view.findViewById<EditText>(R.id.tit)
        var autor = view.findViewById<EditText>(R.id.aut)
        var contenido = view.findViewById<EditText>(R.id.cont)
        var guardar = view.findViewById<Button>(R.id.guardar)

        guardar!!.setOnClickListener {
            if(titulo!!.text.toString().isNotEmpty() && autor.text.toString().isNotEmpty() && contenido!!.text.toString().isNotEmpty()){
                val iduser = leerValor(requireContext(), "iduser")
                val date: Date = c.getTime()

                // Definir el formato deseado
                val dateFormat =
                    SimpleDateFormat("dd-MM-yyyy") // Cambia el formato según tus necesidades

                // Formatear la fecha a una cadena de texto
                val fechaActual: String = dateFormat.format(date)

                viewModelInsertEntries =
                    ViewModelProvider(this).get(InsertEntries::class.java)

                viewModelInsertEntries!!.fetchInsertEntries(
                    iduser!!,
                    titulo.text.toString(),
                    autor.text.toString(),
                    fechaActual,
                    contenido.text.toString()
                )
            }else{
                Toast.makeText(requireContext(), "Necesitas llenar todos los campos", Toast.LENGTH_LONG).show()
            }
        }


        return view
    }


    fun leerValor(context: Context, keyPref: String?): String? {
        val preferences = context.getSharedPreferences(PREFS_KEY, AppCompatActivity.MODE_PRIVATE)
        return preferences.getString(keyPref, "")
    }
}