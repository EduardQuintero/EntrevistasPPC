package com.example.entrevistasppc.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.entrevistasppc.data.AppDatabase
import com.example.entrevistasppc.model.Interview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import androidx.room.Room

class InterviewViewModel(application: Application) : AndroidViewModel(application) {

    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "entrevistas_db"
    ).build()

    private val interviewDao = db.interviewDao()

    fun getInterviewsByCase(caseId: Int): Flow<List<Interview>> {
        return interviewDao.getInterviewsByCase(caseId)
    }

    fun addInterview(interview: Interview) {
        viewModelScope.launch {
            interviewDao.insertInterview(interview)
        }
    }

    fun updateInterview(interview: Interview) {
        viewModelScope.launch {
            interviewDao.updateInterview(interview)
        }
    }

    fun deleteInterview(interview: Interview) {
        viewModelScope.launch {
            interviewDao.deleteInterview(interview)
        }
    }
}
