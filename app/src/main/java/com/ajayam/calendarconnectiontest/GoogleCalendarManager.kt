//package com.ajayam.calendarconnectiontest
//
//import android.app.Activity
//import android.app.Application
//import android.content.Context
//import android.content.Intent
//import androidx.core.app.ActivityCompat.startActivityForResult
//import com.google.android.gms.auth.api.Auth
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions
//import com.google.android.gms.auth.api.signin.GoogleSignInResult
//import com.google.android.gms.common.api.GoogleApiClient
//import com.google.android.gms.common.api.Scope
//import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
//import com.google.api.client.json.JsonFactory
//import com.google.api.client.json.gson.GsonFactory
//import com.google.api.client.util.ExponentialBackOff
//import com.google.api.services.calendar.CalendarScopes
//import java.util.*
//
//class GoogleCalendarManager(private val context: Context) {
//
//    private val SCOPES = listOf(CalendarScopes.CALENDAR)
//    lateinit var googleApiClient: GoogleApiClient
//
//
//    private val jsonFactory: JsonFactory = GsonFactory.getDefaultInstance()
//    private var credential: GoogleAccountCredential? = null
//
//    init {
//        setupCredentials()
//    }
//
//    private fun setupCredentials() {
//        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestEmail()
//            .requestId()
//            .requestProfile()
//            .requestScopes(Scope("https://www.googleapis.com/auth/calendar"))
//            .build()
//
//        val googleApiClient = GoogleApiClient.Builder(context)
//            .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
//            .build()
//
//        credential = GoogleAccountCredential.usingOAuth2(
//            context, SCOPES
//        ).setBackOff(ExponentialBackOff())
//
//        // You can set the account name if it's known, or allow the user to choose
//        // credential?.selectedAccountName = "user@gmail.com"
//    }
//
//    fun signIn(activity: Activity) {
//
//        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
//        startActivityForResult(activity,signInIntent, 101, null)
//    }
//
//    fun handleSignInResult(result: GoogleSignInResult?) {
//        if (result?.isSuccess == true) {
//            val account = result.signInAccount
//            credential?.selectedAccountName = account?.email
//            // You are now authenticated and can use the Calendar API
//        } else {
//            // Handle sign-in failure
//        }
//    }
//
//    // Add the following method to your activity/fragment
//    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (requestCode == 101) {
//            val result = data?.let { Auth.GoogleSignInApi.getSignInResultFromIntent(it) }
//            handleSignInResult(result)
//        }
//    }
//}
