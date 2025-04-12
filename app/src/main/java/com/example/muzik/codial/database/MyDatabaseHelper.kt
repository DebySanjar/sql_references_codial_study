package com.example.muzik.codial.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.muzik.codial.models.Courses
import com.example.muzik.codial.models.Groups
import com.example.muzik.codial.models.Mentors
import com.example.muzik.codial.models.Students

class MyDatabaseHelper(context: Context) : SQLiteOpenHelper(context, dataBaseName, null, version),
    MyDatabaseInterface {

    companion object {
        const val dataBaseName = "Codial"
        const val version = 1
        const val coursesTable = "Courses"
        const val mentorsTable = "Mentors"
        const val id = "id"
        const val groupsTable = "Groups"
        const val studentsTable = "Students"
        const val name = "name"
        const val groupCourseId = "group_course_id"
        const val groupMentorId = "group_mentor_id"
        const val courseDesci = "desc_course"
        const val mentorFatherName = "mentor_fathername"
        const val mentorPhone = "mentor_phone"
        const val mentorCourseId = "mentor_course_id"
        const val studentGroupId = "student_group_id"
    }


    override fun onCreate(db: SQLiteDatabase?) {
        val createCoursesTable =
            "CREATE TABLE $coursesTable($id INTEGER PRIMARY KEY AUTOINCREMENT, $name TEXT, $courseDesci TEXT);"
        val createMentorsTable =
            "CREATE TABLE $mentorsTable($id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$name TEXT, $mentorFatherName TEXT, $mentorPhone TEXT, $mentorCourseId INTEGER, " +
                    "FOREIGN KEY($mentorCourseId) REFERENCES $coursesTable($id));"
        val createGroupsTable =
            "CREATE TABLE $groupsTable($id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$name TEXT, $mentorFatherName TEXT, $mentorPhone TEXT, $mentorCourseId INTEGER, " +
                    "FOREIGN KEY($mentorCourseId) REFERENCES $coursesTable($id));"
        db?.execSQL(createCoursesTable)
        db?.execSQL(createMentorsTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    override fun addCourses(Courses: Courses) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(name, Courses.course_name)
        contentValues.put(courseDesci, Courses.course_description)
        db.insert(coursesTable, null, contentValues)
        db.close()
    }

    override fun getCourses(): ArrayList<Courses> {
        val list = ArrayList<Courses>()
        val query = "SELECT * FROM $coursesTable"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val c = Courses(cursor.getInt(0), cursor.getString(1), cursor.getString(2))
                list.add(c)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    override fun addMentors(mentors: Mentors) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(name, mentors.mentor_name)
        contentValues.put(mentorFatherName, mentors.mentor_fathername)
        contentValues.put(mentorPhone, mentors.mentor_phone)
        contentValues.put(mentorCourseId, mentors.mentor_course.id)
        db.insert(mentorsTable, null, contentValues)
        db.close()
    }

    override fun getMentors(): ArrayList<Mentors> {
        val db = this.readableDatabase
        val list = ArrayList<Mentors>()
        val query = "SELECT * FROM $mentorsTable"
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val courseId = cursor.getInt(4)
                val course = getCourses().find { it.id == courseId } ?: continue
                val mentor = Mentors(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    course
                )
                list.add(mentor)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    override fun deleteMentors(mentors: Mentors) {
        val db = this.writableDatabase
        db.delete(mentorsTable, "$id = ?", arrayOf(mentors.id.toString()))
        db.close()
    }

    override fun updateMentors(mentors: Mentors) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(name, mentors.mentor_name)
        contentValues.put(mentorFatherName, mentors.mentor_fathername)
        contentValues.put(mentorPhone, mentors.mentor_phone)
        contentValues.put(mentorCourseId, mentors.mentor_course.id)
        db.update(mentorsTable, contentValues, "$id = ?", arrayOf(mentors.id.toString()))
        db.close()
    }

    override fun addGroups(groups: Groups) {
        TODO("Not yet implemented")
    }

    override fun getGroups(): ArrayList<Groups> {
        TODO("Not yet implemented")
    }

    override fun deleteGroups(groups: Groups) {
        TODO("Not yet implemented")
    }

    override fun updateGroups(groups: Groups) {
        TODO("Not yet implemented")
    }

    override fun getStudentCount(students: Students): Int {
        TODO("Not yet implemented")
    }


}
