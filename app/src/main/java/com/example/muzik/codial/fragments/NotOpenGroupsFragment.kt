package com.example.muzik.codial.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.muzik.codial.databinding.OpenAndNoopenFragmentBinding

class NotOpenGroupsFragment: Fragment() {

    lateinit var binding: OpenAndNoopenFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = OpenAndNoopenFragmentBinding.inflate(layoutInflater)



        return binding.root
    }
}