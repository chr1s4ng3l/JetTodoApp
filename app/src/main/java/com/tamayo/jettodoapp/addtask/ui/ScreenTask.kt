package com.tamayo.jettodoapp.addtask.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.tamayo.jettodoapp.addtask.ui.model.TaskModel
import com.tamayo.jettodoapp.login.ui.LoginViewModel

@Composable
fun ScreenTask(loginViewModel: LoginViewModel) {

    val showDialog: Boolean by loginViewModel.showDialog.observeAsState(false)
    
    TaskList(loginViewModel = loginViewModel)

    Box(modifier = Modifier.fillMaxSize()) {

        AddTaskDialog(
            show = showDialog,
            onDismiss = { loginViewModel.dialogClose() },
            onTaskAdded = { loginViewModel.taskCreated(it) })

        FabDialog(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp), loginViewModel
        )
    }

}


@Composable
fun TaskList(loginViewModel: LoginViewModel) {
    val myTask: List<TaskModel> = loginViewModel.task

    LazyColumn {
        items(myTask, key = { it.id }) { task ->

            ItemTask(taskModel = task, loginViewModel = loginViewModel)

        }
    }
}

@Composable
fun ItemTask(taskModel: TaskModel, loginViewModel: LoginViewModel) {

    Card(
        elevation = 16.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp).pointerInput(Unit) {
                detectTapGestures(onLongPress = {
                    loginViewModel.onItemRemove(taskModel)
                })
            }
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = taskModel.task,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Checkbox(
                checked = taskModel.selected,
                onCheckedChange = { loginViewModel.onCheckBox(taskModel) })

        }
    }


}

@Composable
fun FabDialog(modifier: Modifier, loginViewModel: LoginViewModel) {

    FloatingActionButton(
        onClick = {
            loginViewModel.onShowSelected()
        }, modifier = modifier, contentColor = Color.White, backgroundColor = Color.Black
    ) {
        Icon(
            imageVector = Icons.Default.Add, contentDescription = ""
        )
    }

}

@Composable
fun AddTaskDialog(show: Boolean, onDismiss: () -> Unit, onTaskAdded: (String) -> Unit) {
    var textState by remember {
        mutableStateOf("")
    }

    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White), horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Add Your Event",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Spacer(modifier = Modifier.size(20.dp))
                OutlinedTextField(value = textState, onValueChange = { textState = it }, label = {
                    Text(
                        text = "Type your event here..."
                    )
                }, singleLine = true, maxLines = 1)
                Spacer(modifier = Modifier.size(8.dp))
                Button(
                    onClick = {
                        onTaskAdded(textState)
                        textState = ""
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        backgroundColor = Color.Black
                    )
                ) {
                    Text(text = "Add Event")
                }

            }

        }
    }
}
