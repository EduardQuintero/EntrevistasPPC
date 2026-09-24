package com.example.entrevistasppc

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigationevent.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import android.os.Bundle
import com.example.entrevistasppc.ui.CaseListScreen
import com.example.entrevistasppc.ui.CaseDetailScreen
import com.example.entrevistasppc.ui.CaseFormScreen
import com.example.entrevistasppc.ui.InterviewFormScreen
import com.example.entrevistasppc.ui.theme.EntrevistasppcTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EntrevistasppcTheme {
                EntrevistasApp()
            }
        }
    }
}


@Composable
fun EntrevistasApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "case_list") {
        composable("case_list") { CaseListScreen(navController) }
        composable("case_detail/{caseId}") { backStackEntry ->
            val caseId = backStackEntry.arguments?.getString("caseId")?.toIntOrNull()
            CaseDetailScreen(navController, caseId)
        }
        composable("case_form/{caseId?}") { backStackEntry ->
            val caseId = backStackEntry.arguments?.getString("caseId")?.toIntOrNull()
            CaseFormScreen(navController, caseId)
        }
        composable("interview_form/{caseId}") { backStackEntry ->
            val caseId = backStackEntry.arguments?.getString("caseId")?.toIntOrNull()
            caseId?.let { InterviewFormScreen(navController, it) }
        }
    }

}
