package com.fcupchan.zmeika

import kotlin.random.Random


class Food {

    companion object{

        private val random = Random
        var positionFoodX = 0.5f
        var positionFoodY = 0f
        private var positionFoodZ = 1f
        private var X1 = positionFoodX + 0.02f
        private var X2 = positionFoodX - 0.02f
        private var X3 = positionFoodX + 0.02f
        private var X4 = positionFoodX - 0.02f
        private var Y1 = positionFoodY + 0.02f
        private var Y2 = positionFoodY + 0.02f
        private var Y3 = positionFoodY - 0.02f
        private var Y4 = positionFoodY - 0.02f
        private var Z1 = positionFoodZ
        private var Z2 = positionFoodZ
        private var Z3 = positionFoodZ
        private var Z4 = positionFoodZ


        private var triangle1: Triangle
        private var triangle2: Triangle

        init {
            triangle1 = Triangle(X1, Y1, Z1, X2, Y2, Z2, X3, Y3, Z3)
            triangle2 = Triangle(X2, Y2, Z2, X3, Y3, Z3, X4, Y4, Z4)
        }

        var isFoodExists = false


        fun createFood() {
            positionFoodX = random.nextInt(-7, 8).toFloat().div(10)
            positionFoodY = random.nextInt(-13, 14).toFloat().div(10)

            var bool = true
            while (bool) {
                bool = false
                Snake.segments.forEach {
                    if (it.centerX == positionFoodX) {
                        bool = true
                        positionFoodX = random.nextInt(-7, 8).toFloat().div(10)
                        return@forEach
                    }
                }
            }
            bool = true
            while (bool) {
                bool = false
                Snake.segments.forEach {
                    if (it.centerY == positionFoodY) {
                        bool = true
                        positionFoodY = random.nextInt(-13, 14).toFloat().div(10)
                        return@forEach
                    }
                }
            }

            X1 = positionFoodX + 0.02f
            X2 = positionFoodX - 0.02f
            X3 = positionFoodX + 0.02f
            X4 = positionFoodX - 0.02f
            Y1 = positionFoodY + 0.02f
            Y2 = positionFoodY + 0.02f
            Y3 = positionFoodY - 0.02f
            Y4 = positionFoodY - 0.02f

            triangle1.changePosition(floatArrayOf(X1, Y1, Z1, X2, Y2, Z2, X3, Y3, Z3))
            triangle2.changePosition(floatArrayOf(X2, Y2, Z2, X3, Y3, Z3, X4, Y4, Z4))

            isFoodExists = true
        }

        fun drawFood(matrix: FloatArray) {
            triangle1.draw(matrix)
            triangle2.draw(matrix)
        }


    }

}
