package com.example.muzik.codial.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.muzik.codial.R
import com.example.muzik.codial.databinding.ItemCourseBinding
import com.example.muzik.codial.models.Courses

class CourseAdapter(
    private var list: List<Courses>,
    private val onItemClick: (Courses) -> Unit
) :
    RecyclerView.Adapter<CourseAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemCourseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(courses: Courses) {
            binding.txtCoursName.text = courses.course_name

            binding.btnNext.setOnClickListener {
                onItemClick.invoke(courses)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCourseBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])


        val animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.item_slide)
        animation.startOffset = (position * 100).toLong() // Zina zina chiqishi uchun kechikish
        holder.itemView.startAnimation(animation)
    }

}