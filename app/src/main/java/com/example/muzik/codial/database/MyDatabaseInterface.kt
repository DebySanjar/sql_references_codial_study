package com.example.muzik.codial.database

import com.example.muzik.codial.models.Courses
import com.example.muzik.codial.models.Groups
import com.example.muzik.codial.models.Mentors
import com.example.muzik.codial.models.Students

interface MyDatabaseInterface {

    fun addCourses(Courses: Courses)
    fun getCourses(): ArrayList<Courses>


    fun addMentors(mentors: Mentors)
    fun getMentors(): ArrayList<Mentors>
    fun deleteMentors(mentors: Mentors)
    fun updateMentors(mentors: Mentors)

    fun addGroups(groups: Groups)
    fun getGroups(): ArrayList<Groups>
    fun deleteGroups(groups: Groups)
    fun updateGroups(groups: Groups)
    fun getStudentCount(students: Students): Int
}