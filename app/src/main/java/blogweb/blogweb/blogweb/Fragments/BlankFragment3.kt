package blogweb.blogweb.blogweb.Fragments

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
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
import blogweb.blogweb.blogweb.MVVM.Login
import blogweb.blogweb.blogweb.MVVM.Register
import blogweb.blogweb.blogweb.R
import java.util.Calendar


class BlankFragment3 : Fragment() {
    private var viewModelInsertEntries: InsertEntries? = null
    private val PREFS_KEY = "mypreferences"
    val c = Calendar.getInstance()
    var fechapublicacion : EditText? = null
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
        fechapublicacion = view.findViewById<EditText>(R.id.fechpub)
        val selfech = view.findViewById<Button>(R.id.seldate)
        var contenido = view.findViewById<EditText>(R.id.cont)
        var guardar = view.findViewById<Button>(R.id.guardar)

        guardar!!.setOnClickListener {
            if(titulo!!.text.toString().isNotEmpty() && autor.text.toString().isNotEmpty() && fechapublicacion!!.text.toString().isNotEmpty() && contenido!!.text.toString().isNotEmpty()){
                val iduser = leerValor(requireContext(), "iduser")

                viewModelInsertEntries =
                    ViewModelProvider(this).get(InsertEntries::class.java)

                viewModelInsertEntries!!.fetchInsertEntries(
                    iduser!!,
                    titulo.text.toString(),
                    autor.text.toString(),
                    fechapublicacion!!.text.toString(),
                    contenido.text.toString()
                )
            }else{
                Toast.makeText(requireContext(), "Necesitas llenar todos los campos", Toast.LENGTH_LONG).show()
            }
        }

        selfech!!.setOnClickListener {
                showDatePickerDialog()
        }

        return view
    }

    private fun showDatePickerDialog() {
        val newFragment =
            DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
                val selectedDate = day.toString() + " / " + (month + 1) + " / " + year
                fechapublicacion!!.setText(selectedDate)
                c.set(Calendar.DAY_OF_MONTH, day)
                c.set(Calendar.MONTH, month)
                c.set(Calendar.YEAR, year)
            })

        newFragment.show(requireActivity().supportFragmentManager, "datePicker")
    }

    fun leerValor(context: Context, keyPref: String?): String? {
        val preferences = context.getSharedPreferences(PREFS_KEY, AppCompatActivity.MODE_PRIVATE)
        return preferences.getString(keyPref, "")
    }
}