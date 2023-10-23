package blogweb.blogweb.blogweb.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import blogweb.blogweb.blogweb.Objects.EntriesContent
import blogweb.blogweb.blogweb.R

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