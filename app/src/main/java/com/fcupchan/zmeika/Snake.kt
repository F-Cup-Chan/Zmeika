package com.fcupchan.zmeika

class Snake {
    companion object{
        var counter = 0f

        var segments = arrayListOf<Segment>()
        //var direction: String = Direction.RIGHT.toString()
        var isGameRunning = true

        fun addSegment(){
            segments.add(Segment(counter, counter, 1f))
            //segments.last().direction = segments[segments.size-2].direction
            counter += 0.1f
        }
    }
}