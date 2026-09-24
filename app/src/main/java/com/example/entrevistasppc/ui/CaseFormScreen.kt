package com.example.entrevistasppc.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.entrevistasppc.model.Case
import com.example.entrevistasppc.viewmodel.CaseViewModel

@Composable
fun CaseFormScreen(navController: NavController, caseId: Int?, viewModel: CaseViewModel = viewModel()) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }

    LaunchedEffect(caseId) {
        caseId?.let {
            val case = viewModel.getCaseById(it)
            case?.let {
                title = it.title
                description = it.description
                date = it.date
                status = it.status
            }
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Título") })
        OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Descripción") })
        OutlinedTextField(value = date, onValueChange = { date = it }, label = { Text("Fecha") })
        OutlinedTextField(value = status, onValueChange = { status = it }, label = { Text("Estado") })

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (caseId == null) {
                viewModel.addCase(Case(title = title, description = description, date = date, status = status))
            } else {
                viewModel.updateCase(Case(id = caseId, title = title, description = description, date = date, status = status))
            }
            navController.popBackStack()
        }) {
            Text("Guardar")
        }
    }
}
