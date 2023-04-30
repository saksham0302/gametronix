package com.example.gametronix

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.File

class SignUp : AppCompatActivity() {

    private lateinit var newUserName: EditText
    private lateinit var newPassword: EditText
    private lateinit var retypePassword: EditText
    private lateinit var create: Button

    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        newUserName = findViewById(R.id.newUserName)
        newPassword = findViewById(R.id.newPassword)
        retypePassword = findViewById(R.id.retypePassword)
        create = findViewById(R.id.create)

        create.setOnClickListener {

            if (newUserName.text.toString().isEmpty()) {
                newUserName.error = "Enter the username"
            }
            if (newPassword.text.toString().isEmpty()) {
                newPassword.error = "Enter the password"
            }
            if (newPassword.text.toString() == retypePassword.text.toString()) {
                val password = newPassword.text.toString()
            }
            if (newPassword.text.toString() != retypePassword.text.toString()) {
                Toast.makeText(this, "Passwords does not match", Toast.LENGTH_SHORT).show()
                retypePassword.text.clear()
                newPassword.text.clear()
            }

            val filename = newUserName.text.toString()
            val f = File("/data/data/" + getPackageName() +  "/shared_prefs/" + filename + ".xml")

            if (f.exists()) {
                Toast.makeText(this, "Username already taken. Enter new user name"
                    , Toast.LENGTH_SHORT).show()
                newUserName.text.clear()
            } else {
                sharedPrefs = getSharedPreferences(filename, Context.MODE_PRIVATE)
                val edit = sharedPrefs.edit()
                edit.putString("userName", newUserName.text.toString())
                edit.putString("password", newPassword.text.toString())
                edit.putString("userDataFile", newUserName.text.toString()+"Data")
                edit.apply()

                Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show()
                val i = Intent(this, LoginPage::class.java)
                startActivity(i)
                finish()
            }
        }
    }
}