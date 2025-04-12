package com.example.muzik.codial.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.muzik.codial.fragments.NotOpenGroupsFragment
import com.example.muzik.codial.fragments.OpenGroupsFragment

@Suppress("DEPRECATION")
class MyFragmentPagerAdapter(rm: FragmentManager) : FragmentPagerAdapter(rm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                OpenGroupsFragment()
            }

            1 -> {
                NotOpenGroupsFragment()
            }

            else -> {
                OpenGroupsFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Ochilgan guruhlar"
            1 -> "Ochilayotgan guruhlar"
            else -> {
                "Ochilgan guruhlar"
            }
        }
    }
}