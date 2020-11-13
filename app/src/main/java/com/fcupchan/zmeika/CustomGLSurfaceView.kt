package com.fcupchan.zmeika

import android.content.Context
import android.opengl.GLSurfaceView
import android.view.MotionEvent
import android.widget.Toast

class CustomGLSurfaceView(context: Context) : GLSurfaceView(context) {

    private val renderer: CustomGLRenderer

    init {
        setEGLContextClientVersion(3)
        renderer = CustomGLRenderer()
        setRenderer(renderer)
        //renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

//        Snake.segments.forEach() {
//            it.changePosition(it.centerX - 0.05f, it.centerY + 0.05f, it.centerZ)
//        }

        performClick()

        val positionX = event?.x
        val positionY = event?.y

        if (positionX?.compareTo(540)!! == -1){
            if (positionY?.compareTo(960)!! == -1){
                Snake.segments.forEach() {
                    it.changePosition(it.centerX + 0.005f, it.centerY + 0.005f, it.centerZ)
                }
            } else {
                Snake.segments.forEach() {
                    it.changePosition(it.centerX + 0.005f, it.centerY - 0.005f, it.centerZ)
                }
            }
        } else {
            if (positionY?.compareTo(960)!! == -1){
                Snake.segments.forEach() {
                    it.changePosition(it.centerX - 0.005f, it.centerY + 0.005f, it.centerZ)
                }
            } else {
                Snake.segments.forEach() {
                    it.changePosition(it.centerX - 0.005f, it.centerY - 0.005f, it.centerZ)
                }
            }
        }

        return true
    }

}