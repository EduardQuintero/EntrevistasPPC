package com.example.entrevistasppc.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.entrevistasppc.model.Case
import com.example.entrevistasppc.model.Interview

@Database(entities = [Case::class, Interview::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun caseDao(): CaseDao
    abstract fun interviewDao(): InterviewDao
}
