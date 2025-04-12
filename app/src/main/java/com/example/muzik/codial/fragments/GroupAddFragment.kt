package com.example.muzik.codial.fragments

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.muzik.codial.database.MyDatabaseHelper
import com.example.muzik.codial.databinding.FragmentGroupAddBinding
import com.example.muzik.codial.models.Mentors


class GroupAddFragment : Fragment() {

    lateinit var binding: FragmentGroupAddBinding
    lateinit var mydatabaseHelper: MyDatabaseHelper
    lateinit var mentorList: List<Mentors>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGroupAddBinding.inflate(layoutInflater)

        binding.apply {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        val listDays = listOf(
            "Dushanba", "Seshanba", "Chorshanba", "Payshanba", "Juma", "Shanba", "Yakshanba"
        )
        val adapterDays = ArrayAdapter(requireContext(), R.layout.simple_list_item_1, listDays)
        binding.changeDays.adapter = adapterDays

        mydatabaseHelper = MyDatabaseHelper(requireContext())
        mentorList = mydatabaseHelper.getMentors()

        loadSpinner()





        return binding.root
    }

    private fun loadSpinner() {
        val mentorNames = ArrayList<String>()

        mentorList.forEach {
            mentorNames.add(it.mentor_name)
        }

        val adapter = ArrayAdapter(requireContext(), R.layout.simple_list_item_1, mentorNames)
        binding.mentorName.adapter = adapter

    }

}