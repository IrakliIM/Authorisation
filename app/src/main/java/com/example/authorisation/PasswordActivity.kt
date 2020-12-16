package com.example.authorisation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class PasswordActivity : AppCompatActivity() {

    private lateinit var inputOldPassword: EditText
    private lateinit var inputNewPassword:EditText
    private lateinit var inputNewAgain: EditText
    private lateinit var changPasswordButton:Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)

        mAuth = FirebaseAuth.getInstance()

        inputOldPassword = findViewById(R.id.oldPasswordEditText)
        inputNewPassword = findViewById(R.id.newPasswordEditText)
        inputNewAgain = findViewById(R.id.repeatNewPasswordEditText)
        changPasswordButton = findViewById(R.id.changePasswordButton)

        changPasswordButton.setOnClickListener {
            val oldPassword = inputOldPassword.text.toString()
            val newPassword = inputNewPassword.text.toString()
            val repeatNewPassword = inputNewAgain.text.toString()

            if(oldPassword.isEmpty() || newPassword.isEmpty() || repeatNewPassword.isEmpty() || repeatNewPassword != newPassword){
                Toast.makeText(this, "404 Error :P", Toast.LENGTH_SHORT).show()
            }else{
                mAuth.currentUser?.updatePassword(newPassword)?.addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        startActivity(Intent(this, PersonActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this, "404 Error :P", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}