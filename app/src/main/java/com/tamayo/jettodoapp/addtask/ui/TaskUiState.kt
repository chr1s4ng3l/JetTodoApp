package com.tamayo.jettodoapp.addtask.ui

import com.tamayo.jettodoapp.addtask.ui.model.TaskModel

sealed interface TaskUiState {
    object Loading : TaskUiState
    data class Error(val throwable: Throwable) : TaskUiState
    data class Success(val task: List<TaskModel>) : TaskUiState
}