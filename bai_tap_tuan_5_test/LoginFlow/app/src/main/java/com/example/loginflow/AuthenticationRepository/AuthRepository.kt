package com.example.loginflow.AuthenticationRepository

import android.app.Activity
import com.example.loginflow.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.Scope
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import com.google.api.services.people.v1.PeopleServiceScopes
import android.content.Context
import com.google.api.services.people.v1.PeopleService
import com.google.api.services.people.v1.model.Person

class AuthRepository(private val auth: FirebaseAuth, private val context: Context) {

    fun getGoogleSignInClient(activity: Activity): GoogleSignInClient {
        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(activity.getString(R.string.default_web_client_id))
            .requestEmail()
            .requestProfile()
            .requestScopes(Scope(Scopes.PROFILE), Scope(Scopes.PLUS_ME))
            .build()

        return GoogleSignIn.getClient(activity, options)
    }

    suspend fun firebaseAuthWithGoogle(idToken: String): FirebaseUser? {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        val authResult = auth.signInWithCredential(credential).await()
        return authResult.user
    }

    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    suspend fun getUserBirthday(account: GoogleSignInAccount): String? {
        val credential = GoogleAccountCredential.usingOAuth2(
            context, listOf(PeopleServiceScopes.USER_BIRTHDAY_READ)
        )
        credential.selectedAccount = account.account

        val peopleService = PeopleService.Builder(
            com.google.api.client.http.javanet.NetHttpTransport(),
            com.google.api.client.json.jackson2.JacksonFactory.getDefaultInstance(),
            credential
        ).setApplicationName("YourAppName").build()

        val person: Person = peopleService.people().get("people/me")
            .setPersonFields("birthdays")
            .execute()

        val birthday = person.birthdays?.firstOrNull()?.date
        return if (birthday != null) {
            "${birthday.year}-${birthday.month}-${birthday.day}"
        } else null
    }
}
