package com.tamayo.jettodoapp.login.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tamayo.jettodoapp.navifation.Routes

@Composable
fun RegisterScreen(loginViewModel: LoginViewModel, navController: NavController) {
    val email: String by loginViewModel.email.observeAsState("")
    val password: String by loginViewModel.email.observeAsState("")
    val name: String by loginViewModel.email.observeAsState("")
    val lastName: String by loginViewModel.email.observeAsState("")
    val isRegisterEnable: Boolean by loginViewModel.isEnable.observeAsState(initial = false)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Registration",
            fontSize = 46.sp,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.size(16.dp))

        Name(name = name) {
            loginViewModel.onRegisterChanged(
                email = email,
                password = password,
                name = name,
                lastName = it
            )
        }
        Spacer(modifier = Modifier.size(8.dp))

        LastName(lastName = lastName) {
            loginViewModel.onRegisterChanged(
                email = email,
                password = password,
                name = name,
                lastName = it
            )
        }

        Spacer(modifier = Modifier.size(8.dp))

        NewEmail(email = email) {
            loginViewModel.onRegisterChanged(
                email = it,
                password = password,
                name = name,
                lastName = lastName
            )
        }

        Spacer(modifier = Modifier.size(8.dp))

        NewPassword(password = password) {
            loginViewModel.onRegisterChanged(
                email = email,
                password = it,
                name = name,
                lastName = lastName
            )
        }



        Spacer(modifier = Modifier.size(24.dp))


        RegisterEnable(
            registerEnable = isRegisterEnable,
            navController = navController
        )


    }

}


@Composable
fun Name(name: String, onTextChange: (String) -> Unit) {
    TextField(
        value = name,
        onValueChange = { onTextChange(it) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        label = { Text(text = "Name") },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFB2B2B2),
            backgroundColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = Color(0xFFB2B2B2)
        )
    )
}


@Composable
fun LastName(lastName: String, onTextChange: (String) -> Unit) {
    TextField(
        value = lastName,
        onValueChange = { onTextChange(it) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        label = { Text(text = "Last Name") },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFB2B2B2),
            backgroundColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = Color(0xFFB2B2B2)
        )
    )
}


@Composable
fun NewEmail(email: String, onTextChange: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextChange(it) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        label = { Text(text = "Email") },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFB2B2B2),
            backgroundColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = Color(0xFFB2B2B2)
        )
    )
}

@Composable
fun NewPassword(password: String, onTextChange: (String) -> Unit) {
    var passwordVisibility by remember {
        mutableStateOf(false)
    }
    TextField(
        value = password,
        onValueChange = { onTextChange(it) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        label = { Text(text = "Password") },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFB2B2B2),
            backgroundColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = Color(0xFFB2B2B2)
        ),
        trailingIcon = {
            val image = if (passwordVisibility) {
                Icons.Filled.VisibilityOff
            } else {

                Icons.Filled.Visibility
            }
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(imageVector = image, contentDescription = "Show Password")

            }
        }, visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }

    )
}


@Composable
fun RegisterEnable(
    registerEnable: Boolean,
    navController: NavController
) {
    Button(
        onClick = { navController.navigate(Routes.TaskScreen.route) },
        enabled = registerEnable,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF000000),
            disabledBackgroundColor = Color(0xFF3F3E3E),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Register")

    }

}
