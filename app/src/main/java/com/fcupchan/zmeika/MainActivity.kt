package com.fcupchan.zmeika

import android.content.Context
import android.opengl.GLSurfaceView
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class MainActivity : AppCompatActivity() {

    private lateinit var mainView :GLSurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        mainView = CustomGLSurfaceView(this)

        setContentView(mainView)
    }

}