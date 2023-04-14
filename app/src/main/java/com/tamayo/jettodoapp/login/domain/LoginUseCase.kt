package com.tamayo.jetlogin.login.domain

import com.tamayo.jetlogin.login.data.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: LoginRepository) {

    suspend operator fun invoke(user: String, password: String): Boolean = repository.doLogin(user, password)
}