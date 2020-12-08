package com.fcupchan.zmeika

import android.opengl.GLSurfaceView
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var mainView :GLSurfaceView

    lateinit var field: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        mainView = CustomGLSurfaceView(this)

        setContentView(mainView)

        val checkThread = CheckThread()
        checkThread.execute()
    }

    fun showToast(num: Int){
        Toast.makeText(this, "New amount of segments: $num", Toast.LENGTH_LONG).show()
    }

    inner class CheckThread : AsyncTask<Unit, Unit, Unit>(){

        override fun doInBackground(vararg params: Unit?) {
            Looper.prepare()
            while (Snake.isGameRunning){
                if (Snake.lastAmountOfSegments != Snake.segments.size){
                    Snake.lastAmountOfSegments = Snake.segments.size
                    showToast(Snake.lastAmountOfSegments)
                }
            }

        }

    }

}