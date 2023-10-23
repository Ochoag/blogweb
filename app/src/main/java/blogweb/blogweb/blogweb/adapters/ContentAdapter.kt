package blogweb.blogweb.blogweb.adapters

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.MediaController
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import blogweb.blogweb.blogweb.Objects.EntriesContent
import blogweb.blogweb.blogweb.R
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

class ContentAdapter(var contexto: Context, items:ArrayList<EntriesContent>): RecyclerView.Adapter<ContentAdapter.ViewHolder>(){

    var items: ArrayList<EntriesContent>? = null

    init {
        this.items = items
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentAdapter.ViewHolder {
        val vista = LayoutInflater.from(contexto).inflate(R.layout.contenido, parent, false)
        val viewHolder = ViewHolder(vista)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items?.count()!!
    }

    override fun onBindViewHolder(holder: ContentAdapter.ViewHolder, position: Int) {
        val item = items?.get(position)

        holder.contenido!!.text = "Contenido: " + item!!.contenido
    }

    class ViewHolder(vista: View): RecyclerView.ViewHolder(vista){
        var vista = vista
        var contenido: TextView? = null

        init{
            contenido = vista.findViewById(R.id.textView559)

        }
    }
}