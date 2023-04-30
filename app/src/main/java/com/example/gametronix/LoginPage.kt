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

class LoginPage : AppCompatActivity() {

    private lateinit var userName: EditText
    private lateinit var password: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignUp: Button

    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        userName = findViewById(R.id.userName)
        password = findViewById(R.id.password)
        btnLogin = findViewById(R.id.btn_Login)
        btnSignUp = findViewById(R.id.btn_signUp)

        btnLogin.setOnClickListener {

            if (userName.text.toString().isEmpty()) {
                userName.error = "Enter the username"
            }
            if (password.text.toString().isEmpty()) {
                password.error = "Enter the password"
            }

            val filename = userName.text.toString()
            val f = File("/data/data/" + getPackageName() +  "/shared_prefs/" + filename + ".xml")

            if (f.exists()) {
                sharedPrefs = getSharedPreferences(filename, Context.MODE_PRIVATE)
                if (userName.text.toString() == sharedPrefs.getString("userName", "")
                    && password.text.toString() == sharedPrefs.getString("password", "")) {

                    val i = Intent(this, NavigationDrawer::class.java)
                    val user = sharedPrefs.getString("userName", "")
                    i.putExtra("userName", user)
                    startActivity(i)
                    finish()
                }


                else {
                    Toast.makeText(this, "Please check your user name or password",
                        Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Username does not exist!!", Toast.LENGTH_SHORT).show()
            }
        }

        btnSignUp.setOnClickListener {
            val i = Intent(this, SignUp::class.java)
            startActivity(i)
        }
    }
}