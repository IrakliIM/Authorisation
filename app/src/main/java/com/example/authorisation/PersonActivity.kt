package com.example.authorisation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.log

class PersonActivity : AppCompatActivity() {

    private lateinit var userInfoTextView: TextView
    private lateinit var passwordChangeButton: Button
    private lateinit var logoutButton: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAuth = FirebaseAuth.getInstance()

        setContentView(R.layout.activity_person)




        userInfoTextView = findViewById(R.id.userInfoTextView)
        passwordChangeButton = findViewById(R.id.gotoPasswordChangeButton)
        logoutButton = findViewById(R.id.logoutButton)

        userInfoTextView.text = mAuth.currentUser?.uid

        passwordChangeButton.setOnClickListener {
            startActivity(Intent(this, PasswordActivity::class.java))
            finish()
        }

        logoutButton.setOnClickListener {
            mAuth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
}