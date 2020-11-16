package com.fcupchan.zmeika

import java.math.BigDecimal
import kotlin.random.Random


class Food {
    companion object {
        var isFoodExists = false

        private val random = Random
        var positionFoodX = BigDecimal.valueOf(random.nextDouble(-0.7, 0.7)).setScale(1).toDouble()
        var positionFoodY = BigDecimal.valueOf(random.nextDouble(-1.3, 1.3)).setScale(1).toDouble()


        fun createFood(){
            var bool = true
            while (bool){
                bool = false
                Snake.segments.forEach{
                    if (it.centerX == positionFoodX.toFloat()){
                        bool = true
                        positionFoodX = BigDecimal.valueOf(random.nextDouble(-0.7, 0.7)).setScale(1).toDouble()
                        return@forEach
                    }
                }
            }
            bool = true
            while (bool){
                bool = false
                Snake.segments.forEach{
                    if (it.centerY == positionFoodY.toFloat()){
                        bool = true
                        positionFoodY = BigDecimal.valueOf(random.nextDouble(-1.3, 1.3)).setScale(1).toDouble()
                        return@forEach
                    }
                }
            }
        }

        fun drawFood(matrix: FloatArray){

        }
    }
}
