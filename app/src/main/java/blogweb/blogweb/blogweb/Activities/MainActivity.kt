package blogweb.blogweb.blogweb.Activities

import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import blogweb.blogweb.blogweb.App.BaseApplication
import blogweb.blogweb.blogweb.Fragments.BlankFragment
import blogweb.blogweb.blogweb.Fragments.BlankFragmentOFFLINE
import blogweb.blogweb.blogweb.R

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appComponent = (application as BaseApplication).getAppComponent()
        appComponent.inject(this)


        val connectivityManager: ConnectivityManager = getSystemService(
            CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        if (connectivityManager != null) {

            val capabilities : NetworkCapabilities? = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    val fragment: Fragment = BlankFragment()
                    supportFragmentManager.beginTransaction().add(R.id.content_main, fragment).commit()
                }
            } else {
                val fragment: Fragment = BlankFragmentOFFLINE()
                supportFragmentManager.beginTransaction().add(R.id.content_main, fragment).commit()

                Toast.makeText(this, "No hay Internet", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onBackPressed() {
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_settings->{
                val i1= Intent(this, LoginApp2::class.java)
                startActivity(i1)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}