package hf.mobweb.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hf.mobweb.fragments.menu.*

class MenuPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    override fun getItem(position: Int): Fragment = when (position) {
            0 -> StartersMenuFragment()
            1 -> SoupMenuFragment()
            2 -> MainMenuFragment()
            3 -> DesertMenuFragment()
            4 -> DrinksMenuFragment()
            else -> StartersMenuFragment()
    }

    override fun getCount() : Int = NUM_PAGES

    companion object{
        const val NUM_PAGES = 5
    }

}