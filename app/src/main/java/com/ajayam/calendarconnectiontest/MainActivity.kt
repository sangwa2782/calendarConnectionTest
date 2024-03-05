package com.ajayam.calendarconnectiontest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.ajayam.calendarconnectiontest.databinding.ActivityMainBinding
import com.ajayam.calendarconnectiontest.databinding.ActivitySecondBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class MainActivity : AppCompatActivity() {
//    private lateinit var googleCalendarManager: GoogleCalendarManager

    private lateinit var gso: GoogleSignInOptions
    private lateinit var gsc: GoogleSignInClient
    private lateinit var GButton : Button

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail()
            .build()
        gsc = GoogleSignIn.getClient(this, gso)


        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct!=null) {
            navigateToSecondActivity()
        }

        binding.GButton.setOnClickListener {
            signIn()
        }

    }

    fun signIn() {
        val signIntent: Intent = gsc.signInIntent
        startActivityForResult(signIntent, 1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000) {
            val task : Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                Log.d("TAG", "Test 69: = " )
                task.getResult(ApiException::class.java)
                Log.d("TAG", "Test 96: = " )
                navigateToSecondActivity()
            } catch (e: ApiException) {
                Toast.makeText(applicationContext, "Something went Wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToSecondActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }
}