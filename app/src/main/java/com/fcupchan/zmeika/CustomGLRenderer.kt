package com.fcupchan.zmeika

import android.opengl.GLES30
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import android.widget.Toast
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

private val vPMatrix = FloatArray(16)
private val projectionMatrix = FloatArray(16)
private val viewMatrix = FloatArray(16)
private var needNewSegment: Boolean = false
private var segmentAdded: Boolean = true



class CustomGLRenderer : GLSurfaceView.Renderer{
    
    private var frameSkipCounter = 0

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        GLES30.glClearColor(0.0f, 1.0f, 0.815686f, 1.0f)

        Snake.segments.add(Segment(0.7f, 00.2f, 1f))
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

        if (Snake.isGameRunning){
            atChange()
        }

    }

    private fun atChange(){

        if (frameSkipCounter == 20){

            Snake.segments.forEach{

                when(it.direction){
                    Direction.RIGHT.toString() -> it.changePosition(it.centerX - 0.1f, it.centerY, it.centerZ)
                    Direction.LEFT.toString() -> it.changePosition(it.centerX + 0.1f, it.centerY, it.centerZ)
                    Direction.UP.toString() -> it.changePosition(it.centerX, it.centerY + 0.1f, it.centerZ)
                    Direction.DOWN.toString() -> it.changePosition(it.centerX, it.centerY - 0.1f, it.centerZ)
                }

                val centerX = BigDecimal.valueOf(it.centerX.toDouble()).setScale(1, RoundingMode.HALF_EVEN).toFloat()
                val centerY = BigDecimal.valueOf(it.centerY.toDouble()).setScale(1, RoundingMode.HALF_EVEN).toFloat()
                val centerFoodX = BigDecimal.valueOf(Food.positionFoodX.toDouble()).setScale(1, RoundingMode.HALF_EVEN).toFloat()
                val centerFoodY = BigDecimal.valueOf(Food.positionFoodY.toDouble()).setScale(1, RoundingMode.HALF_EVEN).toFloat()

                if (!needNewSegment) needNewSegment = centerX == centerFoodX && centerY == centerFoodY

                it.draw(vPMatrix)

                //it.changeColor(floatArrayOf(1.0f, 1.0f, 1.0f, 1.0f))
            }

            if (!segmentAdded){
                Snake.addSegment()
                Food.createFood()
                segmentAdded = true
            }

            if (needNewSegment){
                Snake.rememberLastCoords()
                segmentAdded = false
                needNewSegment = false
                Snake.segments.forEach(){
                    val random = Random()
                    it.changeColor(floatArrayOf(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1.0f))
                }
            }

            frameSkipCounter = 0
        } else {

            Snake.segments.forEach{
                it.draw(vPMatrix)
            }
        }

        Food.drawFood(vPMatrix)

        frameSkipCounter++
    }
}