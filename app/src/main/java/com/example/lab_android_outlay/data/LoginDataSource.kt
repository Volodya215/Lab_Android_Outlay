package com.example.lab_android_outlay.data

import com.example.lab_android_outlay.data.model.LoggedInUser
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.awaitAll
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var res: Result<LoggedInUser>;

    fun login(username: String, password: String): Result<LoggedInUser> {
        mAuth = FirebaseAuth.getInstance()

        try {

            var responce = mAuth.signInWithEmailAndPassword(username, password)

            //checkCompletion(responce)
            while(!responce.isComplete) {}

            var uid: String = responce?.result?.user!!.uid
            val user = LoggedInUser(uid, username)
            return Result.Success(user)

        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        mAuth = FirebaseAuth.getInstance()
        mAuth.signOut()
    }
}