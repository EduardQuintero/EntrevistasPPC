package com.example.entrevistasppc.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "interviews")
data class Interview(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val caseId: Int,
    val findings: String,
    val date: String
)
