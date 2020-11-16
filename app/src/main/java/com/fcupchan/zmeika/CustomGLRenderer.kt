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

        Snake.segments.add(Segment(0.4f, -1.3f, 1f))
//        Snake.segments.add(Segment(0.0f, -1f, 1f))
//        Snake.segments.add(Segment(0.7f, 0.3f, 1f))
//        Snake.segments.add(Segment(0.4f, -1.1f, 1f))
//        Snake.segments.add(Segment(0.34f, 0.8f, 1f))
//        Snake.segments.add(Segment(0.58f, -0.1f, 1f))
//        Snake.segments.add(Segment(0.8f, -0.1f, 1f))
//        Snake.segments.add(Segment(0.53f, -0.7f, 1f))
//        Snake.segments.add(Segment(-0.58f, 0.1f, 1f))
//        Snake.segments.add(Segment(-0.8f, 1.1f, 1f))
//        Snake.segments.add(Segment(-0.34f, 0.1f, 1f))
//        Snake.segments.add(Segment(-0.12f, -0.1f, 1f))
//        Snake.segments.add(Segment(0.28f, -1.1f, 1f))
//        Snake.segments.add(Segment(-0.87f, -0.6f, 1f))
//        Snake.segments.add(Segment(-0.17f, -0.2f, 1f))
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
                when(it.direction){
                    Direction.RIGHT.toString() -> it.changePosition(it.centerX , it.centerY, it.centerZ)
                    Direction.LEFT.toString() -> it.changePosition(it.centerX, it.centerY, it.centerZ)
                    Direction.UP.toString() -> it.changePosition(it.centerX, it.centerY, it.centerZ)
                    Direction.DOWN.toString() -> it.changePosition(it.centerX, it.centerY, it.centerZ)
                }

                it.draw(vPMatrix)
            }
        }

        frameSkipCounter++

    }
}