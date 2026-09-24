package com.example.entrevistasppc.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.example.entrevistasppc.model.Interview

@Dao
interface InterviewDao {
    @Query("SELECT * FROM interviews WHERE caseId = :caseId ORDER BY date DESC")
    fun getInterviewsByCase(caseId: Int): Flow<List<Interview>>

    @Insert
    suspend fun insertInterview(interview: Interview)

    @Update
    suspend fun updateInterview(interview: Interview)

    @Delete
    suspend fun deleteInterview(interview: Interview)
}
