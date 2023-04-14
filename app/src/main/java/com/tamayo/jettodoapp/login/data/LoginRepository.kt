package com.tamayo.jetlogin.login.data

import com.tamayo.jetlogin.login.data.network.LoginService
import javax.inject.Inject

class LoginRepository @Inject constructor(private val api: LoginService) {
    suspend fun doLogin(user: String, password: String): Boolean =
        api.doLogin(user, password)

}