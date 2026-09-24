package com.example.entrevistasppc.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.entrevistasppc.viewmodel.CaseViewModel

@Composable
fun CaseListScreen(navController: NavController, viewModel: CaseViewModel = viewModel()) {
    val cases by viewModel.cases.collectAsState(initial = emptyList())
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Listado de Casos") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("case_form") }) {
                Text("+")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Buscar por título o estado") },
                modifier = Modifier.fillMaxWidth()
            )

            LazyColumn {
                items(cases.filter {
                    it.title.contains(searchQuery, ignoreCase = true) ||
                            it.status.contains(searchQuery, ignoreCase = true)
                }) { case ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate("case_detail/${case.id}") }
                            .padding(8.dp)
                    ) {
                        Text("${case.title} - Estado: ${case.status}")
                    }
                }
            }
        }
    }
}
