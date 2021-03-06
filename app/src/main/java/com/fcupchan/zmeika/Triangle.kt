package com.fcupchan.zmeika

import android.opengl.GLES30
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

class Triangle(X1: Float, Y1: Float, Z1: Float,
               X2: Float, Y2: Float, Z2: Float,
               X3: Float, Y3: Float, Z3: Float) {

    private val fragmentShaderCode =
        "precision mediump float;" +
                "uniform vec4 vColor;" +
                "void main() {" +
                "  gl_FragColor = vColor;" +
                "}"

    private val vertexShaderCode =
        "uniform mat4 uMVPMatrix;" +
                "attribute vec4 vPosition;" +
                "void main() {" +
                "  gl_Position = uMVPMatrix * vPosition;" +
                "}"

    private var vPMatrixHandle: Int = 0

    private val COORDS_PER_VERTEX = 3
    var triangleCoords = floatArrayOf(X1, Y1, Z1, X2, Y2, Z2, X3, Y3, Z3)

    private var color = floatArrayOf(1f, 0f, 0f, 1.0f)

    private var mProgram: Int

    init {

        val vertexShader: Int = loadShader(GLES30.GL_VERTEX_SHADER, vertexShaderCode)
        val fragmentShader: Int = loadShader(GLES30.GL_FRAGMENT_SHADER, fragmentShaderCode)

        mProgram = GLES30.glCreateProgram().also {

            GLES30.glAttachShader(it, vertexShader)

            GLES30.glAttachShader(it, fragmentShader)

            GLES30.glLinkProgram(it)
        }
    }

    private var vertexBuffer: FloatBuffer = ByteBuffer.allocateDirect(triangleCoords.size * 4).run {
        order(ByteOrder.nativeOrder())

        asFloatBuffer().apply {
            put(triangleCoords)
            position(0)
        }
    }

    private var positionHandle: Int = 0
    private var mColorHandle: Int = 0

    private val vertexCount: Int = triangleCoords.size / COORDS_PER_VERTEX
    private val vertexStride: Int = COORDS_PER_VERTEX * 4 // 4 bytes per vertex

    fun draw(mvpMatrix: FloatArray) {

        GLES30.glUseProgram(mProgram)

        positionHandle = GLES30.glGetAttribLocation(mProgram, "vPosition").also {

            GLES30.glEnableVertexAttribArray(it)

            GLES30.glVertexAttribPointer(
                it,
                COORDS_PER_VERTEX,
                GLES30.GL_FLOAT,
                false,
                vertexStride,
                vertexBuffer
            )

            mColorHandle = GLES30.glGetUniformLocation(mProgram, "vColor").also { colorHandle ->

                GLES30.glUniform4fv(colorHandle, 1, color, 0)
            }

            GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, vertexCount)


            GLES30.glDisableVertexAttribArray(it)
        }

        vPMatrixHandle = GLES30.glGetUniformLocation(mProgram, "uMVPMatrix")

        GLES30.glUniformMatrix4fv(vPMatrixHandle, 1, false, mvpMatrix, 0)

        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, vertexCount)

        GLES30.glDisableVertexAttribArray(positionHandle)
    }

    private fun loadShader(type: Int, shaderCode: String): Int {

        return GLES30.glCreateShader(type).also { shader ->
            GLES30.glShaderSource(shader, shaderCode)
            GLES30.glCompileShader(shader)
        }
    }

    fun changePosition(newPosition: FloatArray){
        vertexBuffer.put(newPosition)
        vertexBuffer.position(0)
    }

    fun changeColor(newColor: FloatArray){
        color = newColor
    }


}