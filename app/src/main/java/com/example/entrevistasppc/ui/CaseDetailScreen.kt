package com.example.entrevistasppc.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.entrevistasppc.model.Case
import com.example.entrevistasppc.viewmodel.CaseViewModel

@Composable
fun CaseDetailScreen(
    navController: NavController,
    caseId: Int?,
    viewModel: CaseViewModel = viewModel()
) {
    val case by produceState<Case?>(initialValue = null, caseId) {
        value = caseId?.let { viewModel.getCaseById(it) }
    }

    var conclusion by remember { mutableStateOf("") }
    LaunchedEffect(case) { conclusion = case?.conclusion ?: "" }

    case?.let { selectedCase ->
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Título: ${selectedCase.title}", style = MaterialTheme.typography.titleLarge)
            Text("Descripción: ${selectedCase.description}")
            Text("Fecha: ${selectedCase.date}")
            Text("Estado: ${selectedCase.status}")

            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = conclusion,
                onValueChange = { conclusion = it },
                label = { Text("Conclusión") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Button(onClick = {
                    val updatedCase = selectedCase.copy(conclusion = conclusion)
                    viewModel.updateCase(updatedCase)
                }) { Text("Guardar Conclusión") }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { navController.navigate("case_form/${selectedCase.id}") }) { Text("Editar Caso") }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { viewModel.deleteCase(selectedCase); navController.popBackStack() }) { Text("Eliminar Caso") }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("interview_form/${selectedCase.id}") }) { Text("Ver Entrevistas") }
        }
    } ?: Text("Caso no encontrado")
}
