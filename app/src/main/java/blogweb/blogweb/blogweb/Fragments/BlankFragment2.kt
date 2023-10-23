package blogweb.blogweb.blogweb.Fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import blogweb.blogweb.blogweb.Activities.Content
import blogweb.blogweb.blogweb.Activities.MainActivity
import blogweb.blogweb.blogweb.App.BaseApplication
import blogweb.blogweb.blogweb.MVVM.Entries
import blogweb.blogweb.blogweb.MVVM.EntriesAutor
import blogweb.blogweb.blogweb.MVVM.EntriesContenido
import blogweb.blogweb.blogweb.MVVM.EntriesTitulo
import blogweb.blogweb.blogweb.Objects.CompleteEntries
import blogweb.blogweb.blogweb.R
import blogweb.blogweb.blogweb.adapters.EntriesAdapter
import blogweb.blogweb.blogweb.adapters.OnPURCItemClickListner


class BlankFragment2 : Fragment(), OnPURCItemClickListner {
    private var viewModelEntries: Entries? = null
    private var viewModelEntriesTitulo: EntriesTitulo? = null
    private var viewModelEntriesAutor: EntriesAutor? = null
    private var viewModelEntriesContenido: EntriesContenido? = null
    private val PREFS_KEY = "mypreferences"
    var array = ArrayList<CompleteEntries>()
    var arraytit = ArrayList<CompleteEntries>()
    var arrayaut = ArrayList<CompleteEntries>()
    var arraycont = ArrayList<CompleteEntries>()
    var recy : RecyclerView? = null
    var desc : Button? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_blank2, container, false)

        val baseApplication = requireActivity().application as BaseApplication
        val appComponent = baseApplication.getAppComponent()
        appComponent.inject(this)


        recy = view.findViewById<RecyclerView>(R.id.recy)

        recy!!.layoutManager = LinearLayoutManager(requireContext())
        recy!!.setHasFixedSize(true)

        val search = view.findViewById<EditText>(R.id.search)
        val btntit = view.findViewById<Button>(R.id.btntit)
        val btnaut = view.findViewById<Button>(R.id.btnaut)
        val btncont = view.findViewById<Button>(R.id.btncont)
        desc = view.findViewById<Button>(R.id.desc)

        desc!!.setOnClickListener {
            desc!!.setEnabled(false)
        }

        val connectivityManager: ConnectivityManager = requireActivity().getSystemService(
            AppCompatActivity.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        if (connectivityManager != null) {

            val capabilities : NetworkCapabilities? = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities == null) {
               desc!!.visibility = View.GONE
            }
        }

        btntit!!.setOnClickListener {
            if(search!!.text.toString().isNotEmpty()){
                guardarValor(requireContext(), "titulo", "si")

                requireActivity().finish()
                val i1 = Intent(requireContext(), MainActivity::class.java)
                i1.putExtra("search", search.text.toString())
                startActivity(i1)
            }else{
                Toast.makeText(requireContext(), "Necesitas escribir algo", Toast.LENGTH_LONG).show()
            }
        }

        btnaut!!.setOnClickListener {
            if(search!!.text.toString().isNotEmpty()){
                guardarValor(requireContext(), "autor", "si")

                requireActivity().finish()
                val i1 = Intent(requireContext(), MainActivity::class.java)
                i1.putExtra("search", search.text.toString())
                startActivity(i1)
            }else{
                Toast.makeText(requireContext(), "Necesitas escribir algo", Toast.LENGTH_LONG).show()
            }
        }

        btncont!!.setOnClickListener {
            if(search!!.text.toString().isNotEmpty()){
                guardarValor(requireContext(), "contenido", "si")

                requireActivity().finish()
                val i1 = Intent(requireContext(), MainActivity::class.java)
                i1.putExtra("search", search.text.toString())
                startActivity(i1)
            }else{
                Toast.makeText(requireContext(), "Necesitas escribir algo", Toast.LENGTH_LONG).show()
            }
        }



        val titulo = leerValor(requireContext(), "titulo")
        if(titulo.contentEquals("si")){
            val iduser = leerValor(requireContext(), "iduser")
            val search = requireActivity().intent.extras!!.getString("search")
            viewModelEntriesTitulo =
                ViewModelProvider(this).get(EntriesTitulo::class.java)

            viewModelEntriesTitulo!!.fetchEntries(
                iduser!!, search!!, "", "", ""
            )


            observeViewModelEntriesTitulo()

            guardarValor(requireContext(), "titulo", "")
        }

        val autor = leerValor(requireContext(), "autor")
        if(autor.contentEquals("si")){
            val iduser = leerValor(requireContext(), "iduser")
            val search = requireActivity().intent.extras!!.getString("search")
            viewModelEntriesAutor =
                ViewModelProvider(this).get(EntriesAutor::class.java)

            viewModelEntriesAutor!!.fetchEntries(
                iduser!!, "", search!!, "", ""
            )

            observeViewModelEntriesAutor()

            guardarValor(requireContext(), "autor", "")
        }

        val contenido = leerValor(requireContext(), "contenido")
        if(contenido.contentEquals("si")){
            val iduser = leerValor(requireContext(), "iduser")
            val search = requireActivity().intent.extras!!.getString("search")
            viewModelEntriesContenido =
                ViewModelProvider(this).get(EntriesContenido::class.java)

            viewModelEntriesContenido!!.fetchEntries(
                iduser!!, "", "", "", search!!
            )

            observeViewModelEntriesContenido()

            guardarValor(requireContext(), "contenido", "")
        }

        val ref = view.findViewById<Button>(R.id.ref)

        ref!!.setOnClickListener {
            requireActivity().finish()
            val i1 = Intent(requireContext(), MainActivity::class.java)
            startActivity(i1)
        }


        if(titulo.contentEquals("") && autor.contentEquals("") && contenido.contentEquals("")) {
            loadData()
        }

        return view
    }


    fun loadData(){
        val iduser = leerValor(requireContext(), "iduser")

        viewModelEntries =
            ViewModelProvider(this).get(Entries::class.java)

        viewModelEntries!!.fetchEntries(
            iduser!!, "", "", "", ""
        )

        observeViewModelEntries()
    }


    fun leerValor(context: Context, keyPref: String?): String? {
        val preferences = context.getSharedPreferences(PREFS_KEY, AppCompatActivity.MODE_PRIVATE)
        return preferences.getString(keyPref, "")
    }

    fun observeViewModelEntries(){
        viewModelEntries!!.getEnt().observe(requireActivity()) { ent ->
            array.add(CompleteEntries(ent.iduser, ent.titulo, ent.autor, ent.fechapublicacion, ent.contenido))
            recy!!.adapter = EntriesAdapter(array, this)
        }
    }

    fun observeViewModelEntriesTitulo(){
        viewModelEntriesTitulo!!.getEnt().observe(requireActivity()) { ent ->
            arraytit.add(CompleteEntries(ent.iduser, ent.titulo, ent.autor, ent.fechapublicacion, ent.contenido))
            recy!!.adapter = EntriesAdapter(arraytit, this)
        }
    }

    fun observeViewModelEntriesAutor(){
        viewModelEntriesAutor!!.getEnt().observe(requireActivity()) { ent ->
            arrayaut.add(CompleteEntries(ent.iduser, ent.titulo, ent.autor, ent.fechapublicacion, ent.contenido))
            recy!!.adapter = EntriesAdapter(arrayaut, this)
        }
    }

    fun observeViewModelEntriesContenido(){
        viewModelEntriesContenido!!.getEnt().observe(requireActivity()) { ent ->
            arraycont.add(CompleteEntries(ent.iduser, ent.titulo, ent.autor, ent.fechapublicacion, ent.contenido))
            recy!!.adapter = EntriesAdapter(arraycont, this)
        }
    }

    override fun onItemClick(item: CompleteEntries, position: Int) {

        if(!desc!!.isEnabled){

        }else {
            val i1 = Intent(requireContext(), Content::class.java)
            i1.putExtra("contenido", item.contenido)
            startActivity(i1)
        }
    }

    fun guardarValor(context: Context, keyPref: String?, valor: String?) {
        val settings = context.getSharedPreferences(PREFS_KEY, AppCompatActivity.MODE_PRIVATE)
        val editor: SharedPreferences.Editor
        editor = settings.edit()
        editor.putString(keyPref, valor)
        editor.commit()
    }
}