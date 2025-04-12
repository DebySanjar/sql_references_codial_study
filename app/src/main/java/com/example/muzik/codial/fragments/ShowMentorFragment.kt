package com.example.muzik.codial.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.muzik.codial.adapters.MentorAdapter
import com.example.muzik.codial.database.MyDatabaseHelper
import com.example.muzik.codial.databinding.ShowFragmentBinding
import com.example.muzik.codial.models.Mentors

class ShowMentorFragment : Fragment() {
    private lateinit var binding: ShowFragmentBinding
    private lateinit var mentorAdapter: MentorAdapter
    private var mentorList = mutableListOf<Mentors>()
    private lateinit var myDatabaseHelper: MyDatabaseHelper
    private var courseNames: String? = null
    private var descCorurse: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = ShowFragmentBinding.inflate(inflater, container, false)

        val args = arguments?.let { ShowMentorFragmentArgs.fromBundle(it) }
        courseNames = args?.courseName
        val sourceFragment = args?.sourceFragment
        descCorurse = args?.desc

        myDatabaseHelper = MyDatabaseHelper(requireContext())

        when (sourceFragment) {
            "kurs" -> {

                binding.apply {
                    emptyLogo.visibility = View.GONE
                    cname.text = courseNames
                    myNested.visibility = View.GONE
                    getdesc.visibility = View.VISIBLE
                    getdesc.text = descCorurse
                }
            }

            "students" -> {
                binding.apply {
                    myNested.visibility = View.VISIBLE
                    cname.text = courseNames
                    btnAdd.visibility = View.VISIBLE
                    btnAdd.setOnClickListener {
                        val action =
                            ShowMentorFragmentDirections.actionShowMentorFragmentToMentorAddFragment(
                                courseNames.toString()
                            )
                        findNavController().navigate(action)
                    }
                }

                setupRecyclerView()
                loadMentors()
            }

            else -> {
                Toast.makeText(context, "Xatolik yuz berdi!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        loadMentors()
    }

    private fun setupRecyclerView() {

        checkEmptyState()
        mentorAdapter = MentorAdapter(mentorList, myDatabaseHelper)
        binding.recy.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mentorAdapter
        }
    }

    private fun loadMentors() {
        val allMentors = myDatabaseHelper.getMentors()
        mentorAdapter = MentorAdapter(mentorList, myDatabaseHelper)
        mentorAdapter.updateList(allMentors.filter { it.mentor_course.course_name == courseNames })
        checkEmptyState()

    }

    private fun checkEmptyState() {
        if (mentorList.isEmpty()) {
            binding.emptyLogo.visibility = View.VISIBLE
        } else {
            binding.emptyLogo.visibility = View.GONE
        }
    }
}