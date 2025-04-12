package com.example.muzik.codial.models

data class Groups(
    var id: Int,
    var group_name: String,
    var group_mentor: Mentors,
    var group_date: String,
    var group_time: String,
    var student_count: Students,
    var group_course: Courses
)
