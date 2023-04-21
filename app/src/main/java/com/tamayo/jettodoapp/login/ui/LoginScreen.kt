package com.tamayo.jettodoapp.login.ui

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tamayo.jettodoapp.R
import com.tamayo.jettodoapp.navifation.Routes
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(loginViewModel: LoginViewModel, navController: NavController) {
    val activity = LocalContext.current as Activity
    val isLoading by loginViewModel.isLoading.observeAsState(false)

    Box(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        if (isLoading) {
            Box(modifier = Modifier
                .fillMaxSize()){
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            Header(modifier = Modifier
                .align(Alignment.TopEnd)
                .clickable { activity.finish() })
            Body(Modifier.align(Alignment.Center), loginViewModel, navController)
            Footer(Modifier.align(Alignment.BottomCenter), navController)
        }
    }

}

@Composable
fun Footer(modifier: Modifier, navController: NavController) {
    Column(modifier = modifier.fillMaxWidth()) {
        Divider(
            Modifier
                .background(color = Color(0xFFF9F9F9))
                .height(1.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(24.dp))
        SignUp(navController)
        Spacer(modifier = Modifier.size(24.dp))

    }

}

@Composable
fun SignUp(navController: NavController) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(text = "Don't have an account?", fontSize = 12.sp, color = Color(0xFFB5B5B5))
        Text(
            text = "Sing Up",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF000000),
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .clickable {
                    navController.navigate(
                        Routes.RegisterScreen.route
                    )
                }
        )
    }
}

@Composable
fun Body(modifier: Modifier, loginViewModel: LoginViewModel, navController: NavController) {

    val email: String by loginViewModel.email.observeAsState(initial = "")
    val password: String by loginViewModel.password.observeAsState(initial = "")
    val isLoginEnable: Boolean by loginViewModel.isEnable.observeAsState(initial = false)
    Column(modifier = modifier) {
        ImageLogo(modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        Email(email) {
            loginViewModel.onLoginChanged(email = it, password = password)
        }
        Spacer(modifier = Modifier.size(4.dp))
        Password(password) {
            loginViewModel.onLoginChanged(email = email, password = it)
        }
        Spacer(modifier = Modifier.size(8.dp))
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(16.dp))
        LoginEnable(isLoginEnable, loginViewModel, navController, email, password)
        Spacer(modifier = Modifier.size(16.dp))
        LoginDivider()
        Spacer(modifier = Modifier.size(32.dp))
        SocialLogin()


    }

}


@Composable
fun SocialLogin() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.fb),
            contentDescription = "Social Login Facebook", modifier = Modifier.size(18.dp)
        )
        Text(
            text = "Continue as Christopher",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF000000),
            modifier = Modifier.padding(horizontal = 8.dp)
        )

    }
}

@Composable
fun LoginDivider() {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

        Divider(
            modifier = Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
        Text(
            text = "OR",
            modifier = Modifier.padding(horizontal = 18.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold, color = Color(0xFFB5B5B5)
        )
        Divider(
            modifier = Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )

    }
}

@Composable
fun LoginEnable(loginEnable: Boolean, loginViewModel: LoginViewModel, navController: NavController, email: String, password: String) {

    val context = LocalContext.current
    val isLogin by loginViewModel.isLoggedIn.observeAsState(false)

    Button(
        onClick = {
            loginViewModel.loginUser(email, password)
                  if (isLogin){

                      Toast.makeText(context, "User found", Toast.LENGTH_SHORT).show()
                      navController.navigate(Routes.TaskScreen.route)
                  }else{
                      Toast.makeText(context, "User not found", Toast.LENGTH_SHORT).show()

                  }
         },
        enabled = loginEnable,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF000000),
            disabledBackgroundColor = Color(0xFF3F3E3E),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Log In")

    }

}


@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "Forgot password?",
        fontWeight = FontWeight.Bold,
        color = Color(0xFF000000),
        modifier = modifier
    )

}

@Composable
fun Password(password: String, onTextChange: (String) -> Unit) {
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
fun Email(email: String, onTextChange: (String) -> Unit) {
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
fun ImageLogo(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.banner), contentDescription = "Welcome", modifier.size(160.dp)
    )
}


@Composable
fun Header(modifier: Modifier) {
    Icon(imageVector = Icons.Default.Close, contentDescription = "X", modifier)
}



