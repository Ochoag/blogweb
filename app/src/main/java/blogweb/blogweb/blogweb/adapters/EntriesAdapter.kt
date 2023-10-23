package blogweb.blogweb.blogweb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import blogweb.blogweb.blogweb.Objects.CompleteEntries
import blogweb.blogweb.blogweb.R
import blogweb.blogweb.blogweb.databinding.EntriesBinding


class EntriesAdapter(var items : ArrayList<CompleteEntries>, var clickListner: OnPURCItemClickListner) : RecyclerView.Adapter<PURCViewHolder>(){

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PURCViewHolder {
        lateinit var purcViewHolder : PURCViewHolder
        purcViewHolder = PURCViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.entries,parent,false ))
        return purcViewHolder
    }

    override fun onBindViewHolder(holder: PURCViewHolder, position: Int) {

        holder.initialize(items.get(position),clickListner)

    }
}

class PURCViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

    private val binding = EntriesBinding.bind(itemView)


    fun initialize(item: CompleteEntries, action:OnPURCItemClickListner){

        binding.textView559.text = item.titulo
        binding.textView600.text = item.autor
        binding.textView601.text = item.fechapublicacion

        val primeros70Caracteres: String
        if (item.contenido.length > 70) {
            primeros70Caracteres = item.contenido.substring(0, 70)
            binding.textView602.text = primeros70Caracteres
        } else {
            binding.textView602.text = item.contenido
        }



        itemView.setOnClickListener{
            action.onItemClick(item,adapterPosition)
        }

    }

}

interface OnPURCItemClickListner{
    fun onItemClick(item: CompleteEntries, position: Int)
}
