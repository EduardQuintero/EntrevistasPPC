package com.example.entrevistasppc.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.entrevistasppc.model.Interview
import com.example.entrevistasppc.viewmodel.InterviewViewModel

@Composable
fun InterviewFormScreen(
    navController: NavController,
    caseId: Int,
    viewModel: InterviewViewModel = viewModel()
) {
    var findings by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }

    val interviews by viewModel.getInterviewsByCase(caseId).collectAsState(initial = emptyList())

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Entrevistas del caso", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = findings,
            onValueChange = { findings = it },
            label = { Text("Hallazgos") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Fecha") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (findings.isNotBlank() && date.isNotBlank()) {
                viewModel.addInterview(
                    Interview(caseId = caseId, findings = findings, date = date)
                )
                findings = ""
                date = ""
            }
        }) {
            Text("Guardar Entrevista")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(interviews) { interview ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { /* Aquí podrías abrir edición */ }
                        .padding(8.dp)
                ) {
                    Column {
                        Text("Fecha: ${interview.date}", style = MaterialTheme.typography.bodyLarge)
                        Text("Hallazgos: ${interview.findings}")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = { viewModel.deleteInterview(interview) }) {
                        Text("Eliminar")
                    }
                }
            }
        }
    }
}
