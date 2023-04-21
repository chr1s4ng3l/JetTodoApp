package com.tamayo.jettodoapp.login.data

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

interface LoginRepository {
    fun loginUser(email: String, password: String): Task<AuthResult>
    fun registerUser(email: String, password: String): Task<AuthResult>

    fun logOut()

    fun getUser(): FirebaseUser?

    fun isLoggedIn(): Boolean

    fun isEmailAndPasswordValid(email: String, password: String): Boolean
}

class LoginRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) :
    LoginRepository {
    override fun loginUser(email: String, password: String): Task<AuthResult> =
        firebaseAuth.signInWithEmailAndPassword(email, password)

    override fun registerUser(email: String, password: String): Task<AuthResult> =
        firebaseAuth.createUserWithEmailAndPassword(email, password)


    override fun logOut() = firebaseAuth.signOut()
    override fun getUser(): FirebaseUser? = firebaseAuth.currentUser
    override fun isLoggedIn(): Boolean = getUser() != null
    override fun isEmailAndPasswordValid(email: String, password: String): Boolean =
         try {
            firebaseAuth.signInWithEmailAndPassword(email, password)
            true
        } catch (e: FirebaseAuthException) {
            false
    }


}
