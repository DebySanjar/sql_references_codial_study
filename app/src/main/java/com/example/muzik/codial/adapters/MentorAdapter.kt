package com.example.muzik.codial.adapters

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.muzik.codial.R
import com.example.muzik.codial.database.MyDatabaseHelper
import com.example.muzik.codial.databinding.ItemDialog2Binding
import com.example.muzik.codial.databinding.ItemGuruhmentorBinding
import com.example.muzik.codial.models.Mentors

class MentorAdapter(
    private val mentorList: MutableList<Mentors>,
    private val databaseHelper: MyDatabaseHelper
) : RecyclerView.Adapter<MentorAdapter.MentorViewHolder>() {

    inner class MentorViewHolder(private val binding: ItemGuruhmentorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(mentors: Mentors) {
            binding.name.text = mentors.mentor_name
            binding.count.visibility = View.GONE
            binding.btnSee.visibility = View.GONE

            binding.btnEdit.setOnClickListener {
                val bindingDialog =
                    ItemDialog2Binding.inflate(LayoutInflater.from(binding.root.context))
                val dialog = AlertDialog.Builder(binding.root.context)
                    .setView(bindingDialog.root)
                    .create()
                dialog.window?.apply {
                    setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // Transparent fon
                    attributes.windowAnimations = R.style.DialogAnimation // Animatsiyani qo‘shish
                }


                val nameEditText = bindingDialog.name
                val fNameEditText = bindingDialog.fName
                val phoneEditText = bindingDialog.phons

                nameEditText.setText(mentors.mentor_name)
                fNameEditText.setText(mentors.mentor_fathername)
                phoneEditText.setText(mentors.mentor_phone)

                bindingDialog.btnUpdate.setOnClickListener {

                    val updatedName = nameEditText.text.toString()
                    val updatedFName = fNameEditText.text.toString()
                    val updatedPhone = phoneEditText.text.toString()
                    mentors.mentor_name = updatedName
                    mentors.mentor_fathername = updatedFName
                    mentors.mentor_phone = updatedPhone

                    databaseHelper.updateMentors(mentors)


                    notifyItemChanged(adapterPosition)
                    dialog.dismiss()
                }

                bindingDialog.btnClose.setOnClickListener {
                    dialog.dismiss()
                }

                dialog.show()
            }


            binding.btnDelet.setOnClickListener {
                AlertDialog.Builder(binding.root.context).apply {
                    setTitle("O‘chirish")
                    setMessage("Siz rostdan ham ushbu mentorni o‘chirmoqchimisiz?")
                    setPositiveButton("Ha") { dialog, _ ->
                        val position = adapterPosition
                        if (position != RecyclerView.NO_POSITION) {
                            databaseHelper.deleteMentors(mentors)
                            mentorList.removeAt(position)
                            notifyItemRemoved(position)
                        }
                        dialog.dismiss()
                    }
                    setNegativeButton("Yo‘q", null)
                    show()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorViewHolder {
        val binding = ItemGuruhmentorBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MentorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MentorViewHolder, position: Int) {
        holder.bind(mentorList[position])

        val animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.item_slide)
        animation.startOffset = (position * 100).toLong()
        holder.itemView.startAnimation(animation)
    }

    override fun getItemCount(): Int = mentorList.size


    fun updateList(newList: List<Mentors>) {
        mentorList.clear()
        mentorList.addAll(newList)
        notifyItemRangeInserted(0, mentorList.size)
    }

}
