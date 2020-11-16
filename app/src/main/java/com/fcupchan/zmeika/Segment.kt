package com.fcupchan.zmeika


class Segment(positionX: Float, positionY: Float, positionZ: Float) {
    private var color: FloatArray = floatArrayOf(0.156f, 0.015f, 0.925f, 1.0f)
    var centerX = positionX
    var centerY = positionY
    var centerZ = positionZ
    var direction = Direction.RIGHT.toString()
    private var X1 = centerX + 0.05f
    private var X2 = centerX - 0.05f
    private var X3 = centerX + 0.05f
    private var X4 = centerX - 0.05f
    private var Y1 = centerY + 0.05f
    private var Y2 = centerY + 0.05f
    private var Y3 = centerY - 0.05f
    private var Y4 = centerY - 0.05f
    private var Z1 = centerZ
    private var Z2 = centerZ
    private var Z3 = centerZ
    private var Z4 = centerZ

    private var triangle1: Triangle
    private var triangle2: Triangle


    init {
        triangle1 = Triangle(X1, Y1, Z1, X2, Y2, Z2, X3, Y3, Z3)
        triangle2 = Triangle(X2, Y2, Z2, X3, Y3, Z3, X4, Y4, Z4)
    }

    fun draw(matrix: FloatArray) {
        if (Snake.isGameRunning){
            triangle1.draw(matrix)
            triangle2.draw(matrix)
        }
    }

    fun changePosition(positionX: Float, positionY: Float, positionZ: Float) {
        centerX = positionX
        centerY = positionY
        centerZ = positionZ
        X1 = positionX + 0.05f
        X2 = positionX - 0.05f
        X3 = positionX + 0.05f
        X4 = positionX - 0.05f
        Y1 = positionY + 0.05f
        Y2 = positionY + 0.05f
        Y3 = positionY - 0.05f
        Y4 = positionY - 0.05f
        Z1 = positionZ
        Z2 = positionZ
        Z3 = positionZ
        Z4 = positionZ

        triangle1.changePosition(floatArrayOf(X1, Y1, Z1, X2, Y2, Z2, X3, Y3, Z3))
        triangle2.changePosition(floatArrayOf(X2, Y2, Z2, X3, Y3, Z3, X4, Y4, Z4))

        if (positionX.compareTo(0.71) == 1 || positionX.compareTo(-0.71) == -1 || positionY.compareTo(1.31) == 1 || positionY.compareTo(-1.31) == -1){
            Snake.isGameRunning = false
        }
    }
}