package com.example.ksiegarnia_klient.activities_ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.ksiegarnia_klient.*
import com.example.ksiegarnia_klient.drawer_ui.DrawerActivity
import kotlinx.android.synthetic.main.activity_start_screen.*


class StartScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_screen)

        loginAsGuestButton.setOnClickListener {
            isAdmin = false
            isGuest = true
            val sharedPreferences = getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            val login = "gość"
            editor.putString("user_login", login)
            editor.apply()
            val intent = Intent(this@StartScreenActivity, DrawerActivity::class.java)
            finish()
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            isAdmin = false
            val intent = Intent(this@StartScreenActivity, LoginActivity::class.java)
            finish()//zamknij start screen activity
            startActivity(intent)
        }


        registerButton.setOnClickListener {
            val intent = Intent(this@StartScreenActivity, RegisterActivity::class.java)
            finish()
            startActivity(intent)
        }
    }

    override fun onBackPressed() {//TODO:: BEZ TEGO PRZENOSI DO BIALEGO EKRANU XD
        val fm: FragmentManager = supportFragmentManager
        if (fm.backStackEntryCount == 0){
            finish()
            finishAffinity()

        }
        else {
            fm.popBackStack()
        }
        return
    }
}
