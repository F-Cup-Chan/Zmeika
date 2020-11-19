package com.fcupchan.zmeika

import android.opengl.GLES30
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import android.os.SystemClock
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10
import java.util.Random

private val vPMatrix = FloatArray(16)
private val projectionMatrix = FloatArray(16)
private val viewMatrix = FloatArray(16)
private var frameSkipCounter = 20



class CustomGLRenderer : GLSurfaceView.Renderer{
    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        GLES30.glClearColor(0.0f, 1.0f, 0.815686f, 1.0f)

        Snake.segments.add(Segment(0.7f, 0f, 1f))
        Food.createFood()
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES30.glViewport(0, 0, width, height)

        val ratio: Float = width.toFloat() / height.toFloat()

        Matrix.frustumM(projectionMatrix, 0, -ratio, ratio, -1f, 1f, 3f, 7f)

    }

    override fun onDrawFrame(gl: GL10?) {
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT)

        Matrix.setLookAtM(viewMatrix, 0, 0f, 0f, -3f, 0f, 0f, 0f, 0f, 1.0f, 0.0f)

        Matrix.multiplyMM(vPMatrix, 0, projectionMatrix, 0, viewMatrix, 0)

        if (frameSkipCounter == 20){
            Snake.segments.forEach{

                if (Snake.segments[0].centerX.compareTo(Food.positionFoodX) == 0 && Snake.segments[0].centerY.compareTo(Food.positionFoodY) == 0){
                    Snake.addSegment()
                    if (Snake.segments.size != 1) Snake.isGameRunning = false
                    Food.createFood()
                    Food.drawFood(vPMatrix)
                }

                when(it.direction){
                    Direction.RIGHT.toString() -> it.changePosition(it.centerX - 0.1f, it.centerY, it.centerZ)
                    Direction.LEFT.toString() -> it.changePosition(it.centerX + 0.1f, it.centerY, it.centerZ)
                    Direction.UP.toString() -> it.changePosition(it.centerX, it.centerY + 0.1f, it.centerZ)
                    Direction.DOWN.toString() -> it.changePosition(it.centerX, it.centerY - 0.1f, it.centerZ)
                }

                it.draw(vPMatrix)
            }
            frameSkipCounter = 0
        } else {
            Snake.segments.forEach{
                it.draw(vPMatrix)
            }
        }

        //if (!Food.isFoodExists) Food.createFood()
        Food.drawFood(vPMatrix)

        frameSkipCounter++
    }
}