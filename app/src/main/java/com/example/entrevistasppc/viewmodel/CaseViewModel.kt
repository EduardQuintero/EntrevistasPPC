package com.example.entrevistasppc.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.entrevistasppc.data.AppDatabase
import com.example.entrevistasppc.model.Case
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import androidx.room.Room

class CaseViewModel(application: Application) : AndroidViewModel(application) {

    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "entrevistas_db"
    ).build()

    private val caseDao = db.caseDao()

    val cases: Flow<List<Case>> = caseDao.getAllCases()

    fun addCase(case: Case) {
        viewModelScope.launch {
            caseDao.insertCase(case)
        }
    }

    fun updateCase(case: Case) {
        viewModelScope.launch {
            caseDao.updateCase(case)
        }
    }

    fun deleteCase(case: Case) {
        viewModelScope.launch {
            caseDao.deleteCase(case)
        }
    }

    suspend fun getCaseById(id: Int): Case? {
        return caseDao.getCaseById(id)
    }
}
