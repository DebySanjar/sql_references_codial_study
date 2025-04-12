package com.example.muzik.codial.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.muzik.codial.database.MyDatabaseHelper
import com.example.muzik.codial.databinding.FragmentMentorAddBinding
import com.example.muzik.codial.models.Mentors

class MentorAddFragment : Fragment() {

    private lateinit var binding: FragmentMentorAddBinding
    private lateinit var myDatabaseHelper: MyDatabaseHelper
    private val args: MentorAddFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMentorAddBinding.inflate(inflater, container, false)
        myDatabaseHelper = MyDatabaseHelper(requireContext())

        binding.apply {
            cname.text = "Mentor qo'shish"
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        binding.btnSaveMentor.setOnClickListener {
            saveMentor()
        }

        return binding.root
    }

    private fun saveMentor() {
        val name = binding.etMentorName.text.toString().trim()
        val fatherName = binding.etMentorFatherName.text.toString().trim()
        val phone = binding.etMentorPhone.text.toString().trim()

        if (name.isEmpty() || fatherName.isEmpty() || phone.isEmpty()) {
            Toast.makeText(requireContext(), "Barcha maydonlarni to'ldiring!", Toast.LENGTH_SHORT)
                .show()
            return
        }

        val courseName = args.courseName
        val course = myDatabaseHelper.getCourses().find { it.course_name == courseName }

        if (course != null) {
            val mentor = Mentors(0, name, fatherName, phone, course)
            myDatabaseHelper.addMentors(mentor)
            Toast.makeText(requireContext(), "Mentor qo'shildi!", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        } else {
            Toast.makeText(requireContext(), "Kurs topilmadi!", Toast.LENGTH_SHORT).show()
        }
    }
}