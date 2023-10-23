package blogweb.blogweb.blogweb.adapters

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

import androidx.viewpager2.adapter.FragmentStateAdapter


class TabPagerAdapter(activity: FragmentActivity?, fragments: List<Fragment>) :
    FragmentStateAdapter(activity!!) {
    private val fragments: List<Fragment>

    init {
        this.fragments = fragments
    }

    @NonNull
    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    override fun getItemCount(): Int {
        return fragments.size
    }
}
