package com.fcupchan.zmeika


class Segment(positionX: Float, positionY: Float, positionZ: Float) {
    private var color: FloatArray = floatArrayOf(0.156f, 0.015f, 0.925f, 1.0f)
    var centerX = positionX
    var centerY = positionY
    var centerZ = positionZ
    var directionX = Direction.RIGHT.toString()
    var directionY = Direction.UP.toString()
    private var X1 = centerX + 0.03f
    private var X2 = centerX - 0.03f
    private var X3 = centerX + 0.03f
    private var X4 = centerX - 0.03f
    private var Y1 = centerY + 0.03f
    private var Y2 = centerY + 0.03f
    private var Y3 = centerY - 0.03f
    private var Y4 = centerY - 0.03f
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
        triangle1.draw(matrix)
        triangle2.draw(matrix)
    }

    fun changePosition(positionX: Float, positionY: Float, positionZ: Float) {
        centerX = positionX
        centerY = positionY
        centerZ = positionZ
        X1 = positionX + 0.03f
        X2 = positionX - 0.03f
        X3 = positionX + 0.03f
        X4 = positionX - 0.03f
        Y1 = positionY + 0.03f
        Y2 = positionY + 0.03f
        Y3 = positionY - 0.03f
        Y4 = positionY - 0.03f
        Z1 = positionZ
        Z2 = positionZ
        Z3 = positionZ
        Z4 = positionZ

        triangle1.changePosition(floatArrayOf(X1, Y1, Z1, X2, Y2, Z2, X3, Y3, Z3))
        triangle2.changePosition(floatArrayOf(X2, Y2, Z2, X3, Y3, Z3, X4, Y4, Z4))
    }

    fun checkPosition() {
        if (directionX == Direction.RIGHT.toString() && centerX >= 0.74f) {
            directionX = Direction.LEFT.toString()
            return
        }
        if (directionX == Direction.LEFT.toString() && centerX <= -0.74f) {
            directionX = Direction.RIGHT.toString()
            return
        }
        if (directionY == Direction.UP.toString() && centerY >= 1.31f) {
            directionY = Direction.DOWN.toString()
            return
        }
        if (directionY == Direction.DOWN.toString() && centerY <= -1.31f) {
            directionY = Direction.UP.toString()
            return
        }
    }

}