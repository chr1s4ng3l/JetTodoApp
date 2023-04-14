package com.tamayo.jettodoapp.navifation

sealed class Routes(val route: String) {
    object LoginScreen : Routes("LoginScreen")
    object RegisterScreen : Routes("RegisterScreen")
    object TaskScreen : Routes("TaskScreen")

}