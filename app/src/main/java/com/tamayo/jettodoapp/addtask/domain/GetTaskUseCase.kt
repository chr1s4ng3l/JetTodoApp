package com.tamayo.jettodoapp.addtask.domain

import com.tamayo.jettodoapp.addtask.data.TaskRepository
import com.tamayo.jettodoapp.addtask.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    operator fun invoke(): Flow<List<TaskModel>> = taskRepository.task

}