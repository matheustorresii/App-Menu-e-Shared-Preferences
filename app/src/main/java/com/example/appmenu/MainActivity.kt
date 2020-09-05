package com.example.appmenu

import android.content.Context
import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pref = getSharedPreferences("isDark", Context.MODE_PRIVATE)
        getChosenTheme(pref)

        val switchTheme = findViewById<androidx.appcompat.widget.SwitchCompat>(R.id.switchTheme)
        switchTheme.setOnCheckedChangeListener { _, isDark ->
            if (isDark) {
                changeToDark()
            } else {
                changeToLight()
            }
            setChosenTheme(pref, isDark)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.lightThemeMenu -> {
                changeToLight()
                switchTheme.toggle()
                true
            }
            R.id.darkThemeMenu -> {
                changeToDark()
                switchTheme.toggle()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun setChosenTheme(pref: SharedPreferences, dark: Boolean) {
        val editor = pref.edit()
        editor.putString("isDark", dark.toString()).apply()
    }

    private fun getChosenTheme(pref: SharedPreferences) {
        val isDark: String? = pref.getString("isDark", "false")
        if(isDark == "true") {
            changeToDark()
            switchTheme.toggle()
        } else {
            changeToLight()
        }
    }

    private fun changeToDark() {
        val backgroundActivity = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.backgroundActivity)
        val textLight = findViewById<TextView>(R.id.textLight)
        val textDark = findViewById<TextView>(R.id.textDark)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorSecondaryDark)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimaryDark)))
        backgroundActivity.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundDark))
        textDark.setTextColor(ContextCompat.getColor(this ,R.color.colorTextDark))
        textLight.setTextColor(ContextCompat.getColor(this ,R.color.colorTextDark))
    }

    private fun changeToLight() {
        val backgroundActivity = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.backgroundActivity)
        val textLight = findViewById<TextView>(R.id.textLight)
        val textDark = findViewById<TextView>(R.id.textDark)

        window.statusBarColor = ContextCompat.getColor(this, R.color.colorSecondaryLight)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimaryLight)))
        backgroundActivity.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundLight))
        textDark.setTextColor(ContextCompat.getColor(this ,R.color.colorTextLight))
        textLight.setTextColor(ContextCompat.getColor(this ,R.color.colorTextLight))
    }
}