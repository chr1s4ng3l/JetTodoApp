package com.tamayo.jettodoapp.addtask.domain

import com.tamayo.jettodoapp.addtask.data.TaskRepository
import com.tamayo.jettodoapp.addtask.ui.model.TaskModel
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    suspend operator fun invoke(taskModel: TaskModel){
        taskRepository.delete(taskModel)
    }
}