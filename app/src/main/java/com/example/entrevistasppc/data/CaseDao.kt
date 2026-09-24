package com.example.entrevistasppc.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.example.entrevistasppc.model.Case

@Dao
interface CaseDao {
    @Query("SELECT * FROM cases ORDER BY date DESC")
    fun getAllCases(): Flow<List<Case>>

    @Query("SELECT * FROM cases WHERE id = :id")
    suspend fun getCaseById(id: Int): Case?

    @Insert
    suspend fun insertCase(case: Case)

    @Update
    suspend fun updateCase(case: Case)

    @Delete
    suspend fun deleteCase(case: Case)
}
