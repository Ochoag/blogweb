package blogweb.blogweb.blogweb.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import blogweb.blogweb.blogweb.Objects.EntriesContent
import blogweb.blogweb.blogweb.R
import blogweb.blogweb.blogweb.adapters.ContentAdapter

class Content :  AppCompatActivity() {

    var recy : RecyclerView? = null
    var array = ArrayList<EntriesContent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content)

        recy = findViewById(R.id.recy)
        recy!!.layoutManager = LinearLayoutManager(this)
        recy!!.setHasFixedSize(true)


        val content = intent.extras!!.getString("contenido")

        array!!.add(EntriesContent(content!!))
        recy!!.adapter = ContentAdapter(this, array)
    }
}