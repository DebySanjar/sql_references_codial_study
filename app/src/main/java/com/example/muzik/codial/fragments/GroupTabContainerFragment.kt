package com.example.muzik.codial.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.muzik.codial.adapters.MyFragmentPagerAdapter
import com.example.muzik.codial.databinding.FragmentGroupTabContainerBinding


class GroupTabContainerFragment : Fragment() {

    lateinit var binding: FragmentGroupTabContainerBinding
    lateinit var MyFragmentPagerAdapter: MyFragmentPagerAdapter
    private var courseNames: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGroupTabContainerBinding.inflate(layoutInflater)

        val args = arguments?.let { GroupTabContainerFragmentArgs.fromBundle(it) }
        courseNames = args?.courseName

        binding.cname.text = courseNames

        MyFragmentPagerAdapter = MyFragmentPagerAdapter(childFragmentManager)
        binding.myPager.adapter = MyFragmentPagerAdapter
        binding.myTab.setupWithViewPager(binding.myPager)

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnAdd.setOnClickListener {
            val action =
                GroupTabContainerFragmentDirections.actionGroupsFragmentToGroupTabContainerFragment(
                    courseNames.toString()
                )
            findNavController().navigate(action)
        }

        binding.myPager.addOnPageChangeListener(object :
            androidx.viewpager.widget.ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int, positionOffset: Float, positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (position == 1) {
                    binding.btnAdd.visibility = View.VISIBLE
                } else {
                    binding.btnAdd.visibility = View.GONE
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })


        return binding.root
    }

}