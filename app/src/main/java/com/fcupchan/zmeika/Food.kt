package com.fcupchan.zmeika

import java.math.BigDecimal
import kotlin.random.Random


class Food {
    companion object {
        var isFoodExists = false


        fun createFood(){
            val random = Random
            var positionX = BigDecimal.valueOf(random.nextDouble(-0.7, 0.7)).setScale(1).toDouble()
            var positionY = BigDecimal.valueOf(random.nextDouble(-1.3, 1.3)).setScale(1).toDouble()
            //positionX = BigDecimal.valueOf(positionX).setScale(1).toDouble()
            //positionY = BigDecimal.valueOf(positionY).setScale(1).toDouble()
            var bool = true

            while (bool){
                bool = false
                Snake.segments.forEach{
                    if (it.centerX == positionX.toFloat()){
                        bool = true
                        positionX = BigDecimal.valueOf(random.nextDouble(-0.7, 0.7)).setScale(1).toDouble()
                        return@forEach
                    }
                }
            }

            bool = true
            while (bool){
                bool = false
                Snake.segments.forEach{
                    if (it.centerY == positionY.toFloat()){
                        bool = true
                        positionY = BigDecimal.valueOf(random.nextDouble(-1.3, 1.3)).setScale(1).toDouble()
                        return@forEach
                    }
                }
            }
        }
    }
}
