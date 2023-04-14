package com.tamayo.jettodoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tamayo.jettodoapp.login.ui.LoginScreen
import com.tamayo.jettodoapp.login.ui.LoginViewModel
import com.tamayo.jettodoapp.addtask.ui.ScreenTask
import com.tamayo.jettodoapp.login.ui.RegisterScreen
import com.tamayo.jettodoapp.navifation.Routes
import com.tamayo.jettodoapp.ui.theme.JetTodoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val taskViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetTodoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navigationController = rememberNavController()

                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.TaskScreen.route
                    ) {
                        composable(Routes.LoginScreen.route) { LoginScreen(loginViewModel = taskViewModel, navigationController) }
                        composable(Routes.RegisterScreen.route) { RegisterScreen(loginViewModel = taskViewModel, navigationController)}
                        composable(Routes.TaskScreen.route) { ScreenTask(taskViewModel) }
                }

                }
            }
        }
    }
}

