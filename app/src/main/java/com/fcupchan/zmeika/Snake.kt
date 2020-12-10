package com.fcupchan.zmeika

class Snake {
    companion object{

        var segments = arrayListOf<Segment>()
        //var direction: String = Direction.RIGHT.toString()
        var isGameRunning = true
        private var lastX: Float = 0f
        private var lastY: Float = 0f
        private var lastZ: Float = 0f

        fun addSegment(){
            segments.add(Segment(lastX, lastY, lastZ))
            segments.last().direction = segments[segments.size-2].direction
        }

        fun rememberLastCoords(){
            lastX = segments.last().centerX
            lastY = segments.last().centerY
            lastZ = segments.last().centerZ
        }
    }
}