package com.example.muzik.codial.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.muzik.codial.R
import com.example.muzik.codial.adapters.CourseAdapter
import com.example.muzik.codial.database.MyDatabaseHelper
import com.example.muzik.codial.databinding.FullFragmentBinding
import com.example.muzik.codial.databinding.ItemDialogBinding
import com.example.muzik.codial.models.Courses

class CoursesFragment : Fragment() {
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
            btnAdd.visibility = View.VISIBLE
        }

        myDatabaseHelper = MyDatabaseHelper(requireContext())
        list = myDatabaseHelper.getCourses()


        adapter = CourseAdapter(list) { course ->

            val action = CoursesFragmentDirections.actionCoursesFragmentToShowMentorFragment(
                desc = course.course_description ?: "",
                courseName = course.course_name ?: "",
                sourceFragment = "kurs"
            )
            findNavController().navigate(action)
        }
        binding.recy.adapter = adapter


        binding.btnAdd.setOnClickListener {
            showAddDialog()
        }


        return binding.root
    }


    private fun showAddDialog() {

        val background = GradientDrawable()
        background.setColor(Color.TRANSPARENT)
        background.cornerRadius = 41f

        val dialogBinding = ItemDialogBinding.inflate(layoutInflater)
        var dialog = AlertDialog.Builder(requireContext()).setView(dialogBinding.root).create()

        dialog.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // Transparent fon
            attributes.windowAnimations = R.style.DialogAnimation // Animatsiyani qo‘shish
        }
        dialogBinding.btnClose.setOnClickListener {
            dialog.dismiss()
        }

        dialogBinding.btnQosh.setOnClickListener {
            val ask = dialogBinding.name.text.toString().trim()
            val answer = dialogBinding.desc.text.toString().trim()

            var boshmi = true

            if (ask.isEmpty()) {
                dialogBinding.name.error = "Kurs nomi bo'sh!"
                boshmi = false
            }

            if (answer.isEmpty()) {
                dialogBinding.desc.error = "Kurs haqida ma'lumot bering!"
                boshmi = false
            }

            if (boshmi) {
                val course = Courses(0, ask, answer)
                myDatabaseHelper.addCourses(course)
                list.add(course)
                adapter.notifyItemInserted(list.size - 1)
                adapter.notifyDataSetChanged()
                Toast.makeText(context, "Kurs muvaffaqyatli qo'shildi!", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            } else {

            }
        }

        dialog.show()
    }


}