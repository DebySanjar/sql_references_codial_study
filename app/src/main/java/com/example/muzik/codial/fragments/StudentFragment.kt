package com.example.muzik.codial.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.muzik.codial.adapters.CourseAdapter
import com.example.muzik.codial.database.MyDatabaseHelper
import com.example.muzik.codial.databinding.FullFragmentBinding
import com.example.muzik.codial.models.Courses

class StudentFragment : Fragment() {

    lateinit var binding: FullFragmentBinding

    private lateinit var list: ArrayList<Courses>
    private lateinit var adapter: CourseAdapter
    private lateinit var myDatabaseHelper: MyDatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = FullFragmentBinding.inflate(layoutInflater)

        binding = FullFragmentBinding.inflate(layoutInflater)
        binding.apply {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        myDatabaseHelper = MyDatabaseHelper(requireContext())
        list = myDatabaseHelper.getCourses()

        adapter = CourseAdapter(list) { course ->

            val action = StudentFragmentDirections.actionStudentFragmentToShowMentorFragment(
                desc = course.course_description ?: "",
                courseName = course.course_name ?: "",
                sourceFragment = "students"
            )
            findNavController().navigate(action)
        }

        binding.recy.adapter = adapter





        return binding.root
    }
}