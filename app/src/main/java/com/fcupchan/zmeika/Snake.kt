package com.fcupchan.zmeika

class Snake {
    companion object{
        var segments = arrayListOf<Segment>()
        var direction: String = Direction.RIGHT.toString()
        var isGameRunning = true

        fun addSegment(){
            segments.add(Segment(segments.last().centerX, segments.last().centerY, segments.last().centerZ))
            segments.last().direction = segments[segments.size-2].direction
        }
    }
}