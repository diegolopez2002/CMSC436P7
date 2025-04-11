
package com.example.project7

class Balloons {
    val balloons = mutableListOf<Balloon>()

    fun addBalloon(balloon: Balloon) {
        balloons.add(balloon)
    }

    fun popBalloon(touchX: Float, touchY: Float): Boolean {
        for (balloon in balloons) {
            if (!balloon.isPopped && balloon.isInside(touchX, touchY)) {
                balloon.isPopped = true
                return true
            }
        }
        return false
    }

    fun allPopped(): Boolean {
        return balloons.all { it.isPopped }
    }
}
