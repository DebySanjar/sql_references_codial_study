package com.example.muzik.codial.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.muzik.codial.R
import com.example.muzik.codial.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(layoutInflater)

        val navOptions = NavOptions.Builder().setEnterAnim(R.anim.slide_in_left)
            .setExitAnim(R.anim.slide_out_right).setPopEnterAnim(R.anim.slide_in_right)
            .setPopExitAnim(R.anim.slide_out_left).build()

        binding.apply {
            btnCourses.setOnClickListener {
                findNavController().navigate(R.id.coursesFragment, null, navOptions)
            }
            btnStudents.setOnClickListener {
                findNavController().navigate(R.id.studentFragment, null, navOptions)
            }
            btnGroups.setOnClickListener {
                findNavController().navigate(R.id.groupsFragment, null, navOptions)
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    showExitDialog()
                }
            }
        )



        return binding.root
    }

    private fun showExitDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Dasturdan chiqish")
        builder.setMessage("Siz chindan ham dasturdan chiqmoqchimisiz?")
        builder.setPositiveButton("Ha") { _, _ ->
            requireActivity().finish()
        }
        builder.setNegativeButton("Yo'q") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }
}


