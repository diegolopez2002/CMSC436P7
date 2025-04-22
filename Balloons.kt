package com.example.project7

class Balloons {
    private val balloons = mutableListOf<Balloon>()
    private var attempts = 0
    private val maxAttempts: Int
        get() = balloons.size + 3

    fun addBalloon(balloon: Balloon) {
        balloons.add(balloon)
    }

    fun popBalloon(x: Float, y: Float): Boolean {
        attempts++
        for (balloon in balloons) {
            if (!balloon.isPopped && balloon.isClicked(x, y)) {
                balloon.pop()
                return true
            }
        }
        return false
    }

    fun isGameWon(): Boolean {
        return balloons.all { it.isPopped }
    }

    fun isGameLost(): Boolean {
        return attempts >= maxAttempts && !isGameWon()
    }

    fun getBalloons(): List<Balloon> {
        return balloons
    }
}
