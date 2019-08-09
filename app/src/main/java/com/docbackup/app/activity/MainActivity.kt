package com.docbackup.app.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.borshevik.mvpfragments.activity.MainActivity
import com.docbackup.app.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_new)

        startActivity(Intent(this, MainActivity::class.java))
    }

}