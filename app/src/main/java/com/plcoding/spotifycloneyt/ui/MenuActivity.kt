package com.plcoding.spotifycloneyt.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.plcoding.spotifycloneyt.R
import com.plcoding.spotifycloneyt.other.Constants.CURRENT_MOOD
import com.plcoding.spotifycloneyt.other.Constants.SAD_MOOD

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }

    fun generateSadPLaylist(view: View) {
        CURRENT_MOOD = SAD_MOOD;
        startActivity(Intent(this,MainActivity::class.java));
    }
    fun generateFunnyPLaylist(view: View) {
        CURRENT_MOOD = "funny";
        startActivity(Intent(this,MainActivity::class.java));
    }
    fun generatePLaylistForSleep(view: View) {
        CURRENT_MOOD = "sleepy";
        startActivity(Intent(this,MainActivity::class.java));
    }
    fun generateRandomPLaylist(view: View) {
        CURRENT_MOOD = "";
        startActivity(Intent(this,MainActivity::class.java));
    }
    fun generateMoodPLaylist(view: View) {

    }
}