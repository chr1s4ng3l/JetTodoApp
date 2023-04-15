package com.tamayo.jettodoapp.login.utils

sealed class UIState<out T> {
    object LOADING : UIState<Nothing>()
    data class SUCCESS<T>(val response: T) : UIState<T>()
    data class ERROR<T>(val error: String) : UIState<T>()
}