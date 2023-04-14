package com.tamayo.jettodoapp.login.ui

import android.util.Patterns
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tamayo.jettodoapp.addtask.domain.AddTaskUseCase
import com.tamayo.jettodoapp.login.domain.LoginUseCase
import com.tamayo.jettodoapp.addtask.domain.GetTaskUseCase
import com.tamayo.jettodoapp.addtask.domain.UpdateTaskUseCase
import com.tamayo.jettodoapp.addtask.ui.TaskUiState
import com.tamayo.jettodoapp.addtask.ui.TaskUiState.Success
import com.tamayo.jettodoapp.addtask.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    getTaskUseCase: GetTaskUseCase
) : ViewModel() {

    val uiState: StateFlow<TaskUiState> = getTaskUseCase().map(::Success)
        .catch { TaskUiState.Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TaskUiState.Loading)

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _lastName = MutableLiveData<String>()
    val lastName: LiveData<String> = _lastName

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    val isEnable = MutableLiveData<Boolean>()
    val showDialog = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        isEnable.value = enableLogin(email, password)
    }

    fun onRegisterChanged(name: String, lastName: String, email: String, password: String) {
        _email.value = email
        _password.value = password
        _name.value = name
        _lastName.value = lastName
        isEnable.value = enableRegister(name, lastName, email, password)
    }

    private fun enableLogin(email: String, password: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6

    private fun enableRegister(
        name: String,
        lastName: String,
        email: String,
        password: String
    ): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email)
            .matches() && password.length > 6 && name.isNotEmpty() && lastName.isNotEmpty()


    fun unLoginSelected() {
        viewModelScope.launch {
            isLoading.value = true
            val result = loginUseCase(email.value!!, password.value!!)
            if (result) {
                //Navigation
                println("Christopher")
                isLoading.value = false
            }
        }
    }

    fun dialogClose() {
        showDialog.value = false
    }

    fun taskCreated(task: String) {
        showDialog.value = false

        viewModelScope.launch {
            addTaskUseCase(TaskModel(task = task))
        }

    }

    fun onShowSelected() {
        showDialog.value = true
    }

    fun onCheckBox(taskModel: TaskModel) {
        viewModelScope.launch {
            updateTaskUseCase(taskModel.copy(selected = !taskModel.selected))
        }
    }

    fun onItemRemove(taskModel: TaskModel) {

    }


}








