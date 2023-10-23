package blogweb.blogweb.blogweb.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import blogweb.blogweb.blogweb.App.BaseApplication
import blogweb.blogweb.blogweb.R
import blogweb.blogweb.blogweb.adapters.TabPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator



class BlankFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_blank, container, false)

        val baseApplication = requireActivity().application as BaseApplication
        val appComponent = baseApplication.getAppComponent()
        appComponent.inject(this)

        val tabLayout: TabLayout = view.findViewById(R.id.tabLayout)
        val viewPager: ViewPager2 = view.findViewById(R.id.viewPager)

        val fragments: MutableList<Fragment> = ArrayList()
        fragments.add(BlankFragment2())
        fragments.add(BlankFragment3())

// Agrega más fragmentos según sea necesario
        val adapter = TabPagerAdapter(requireActivity(), fragments)
        viewPager.adapter = adapter

        val tabIcons = listOf(
            R.mipmap.blog_icon_foreground, R.mipmap.edit_blog_icon_foreground
        )

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(tabIcons[position])
        }.attach()


        return view
    }
}