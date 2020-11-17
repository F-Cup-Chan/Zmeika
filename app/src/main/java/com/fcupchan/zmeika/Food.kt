package com.fcupchan.zmeika

import kotlin.random.Random


class Food(positionX: Float, positionY: Float, positionZ: Float) {

    private val random = Random
    var centerX = positionX
    var centerY = positionY
    var centerZ = positionZ
    private var X1 = centerX + 0.02f
    private var X2 = centerX - 0.02f
    private var X3 = centerX + 0.02f
    private var X4 = centerX - 0.02f
    private var Y1 = centerY + 0.02f
    private var Y2 = centerY + 0.02f
    private var Y3 = centerY - 0.02f
    private var Y4 = centerY - 0.02f
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

    var isFoodExists = false


    private var positionFoodX = random.nextInt(-6, 7).toFloat().div(10)
    private var positionFoodY = random.nextInt(-12, 13).toFloat().div(10)


    fun createFood() {
        var bool = true
        X1 = positionFoodX + 0.02f
        X2 = positionFoodX - 0.02f
        X3 = positionFoodX + 0.02f
        X4 = positionFoodX - 0.02f
        Y1 = positionFoodY + 0.02f
        Y2 = positionFoodY + 0.02f
        Y3 = positionFoodY - 0.02f
        Y4 = positionFoodY - 0.02f
//            while (bool){
//                bool = false
//                Snake.segments.forEach{
//                    if (it.centerX == positionFoodX){
//                        bool = true
//                        positionFoodX = random.nextInt(-7, 8).toFloat().div(10)
//                        return@forEach
//                    }
//                }
//            }
//            bool = true
//            while (bool){
//                bool = false
//                Snake.segments.forEach{
//                    if (it.centerY == positionFoodY){
//                        bool = true
//                        positionFoodY = random.nextInt(-13, 14).toFloat().div(10)
//                        return@forEach
//                    }
//                }
//            }


        triangle1.changePosition(floatArrayOf(X1, Y1, Z1, X2, Y2, Z2, X3, Y3, Z3))
        triangle2.changePosition(floatArrayOf(X2, Y2, Z2, X3, Y3, Z3, X4, Y4, Z4))
    }

    fun drawFood(matrix: FloatArray) {
        isFoodExists = true
        if (Snake.isGameRunning) {
            triangle1.draw(matrix)
            triangle2.draw(matrix)
        }
    }

}
