package com.fcupchan.zmeika

import android.content.Context
import android.opengl.GLSurfaceView
import android.view.MotionEvent
import android.widget.Toast

class CustomGLSurfaceView(context: Context) : GLSurfaceView(context) {

    private val renderer: CustomGLRenderer

    init {
        setEGLContextClientVersion(3) // should(?) be 2
        renderer = CustomGLRenderer()
        setRenderer(renderer)
        //renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        super.performClick()
        val positionX = (event?.x?.minus(540))?.div(-540)
        val positionY = (event?.y?.minus(960))?.div(-960)

        when {
            positionY?.compareTo(0.65)  == 1 -> {
                Snake.segments.forEachIndexed(){ i, _ ->
                    if (i != 0){
                        Snake.segments[i].direction = Snake.segments[i-1].direction
                    }
                }
                if (Snake.segments[0].direction != Direction.DOWN.toString()){
                    Snake.segments[0].direction = Direction.UP.toString()
                }
            }
            positionY?.compareTo(-0.65)  == -1 -> {
                Snake.segments.forEachIndexed(){ i, _ ->
                    if (i != 0){
                        Snake.segments[i].direction = Snake.segments[i-1].direction
                    }
                }
                if (Snake.segments[0].direction != Direction.UP.toString()){
                    Snake.segments[0].direction = Direction.DOWN.toString()
                }
            }
            positionX?.compareTo(0.35) == 1 -> {
                Snake.segments.forEachIndexed(){ i, _ ->
                    if (i != 0){
                        Snake.segments[i].direction = Snake.segments[i-1].direction
                    }
                }
                if (Snake.segments[0].direction != Direction.RIGHT.toString()){
                    Snake.segments[0].direction = Direction.LEFT.toString()
                }
            }
            positionX?.compareTo(-0.35) == -1 -> {
                Snake.segments.forEachIndexed(){ i, _ ->
                    if (i != 0){
                        Snake.segments[i].direction = Snake.segments[i-1].direction
                    }
                }
                if (Snake.segments[0].direction != Direction.LEFT.toString()){
                    Snake.segments[0].direction = Direction.RIGHT.toString()
                }
            }
            else -> {
                if (!Snake.isGameRunning){
                    Snake.segments.clear()
                    Snake.segments.add(Segment(0f, 0f, 1f))
                    Snake.isGameRunning = true
                }
            }
        }

        return true
    }

}