package com.fcupchan.zmeika

import android.opengl.GLSurfaceView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast

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

        //val snake: Snake
    }

    fun toast(key: Int){
        val string: String = when(key){
            1 -> "LEFT UP"
            2 -> "RIGHT UP"
            3 -> "LEFT DOWN"
            4 -> "RIGHT DOWN"
            else -> "unknown"
        }

        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }
}