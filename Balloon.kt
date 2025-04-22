package com.example.project7

data class Balloon(
    val x: Float,
    val y: Float,
    val radius: Float,
    var isPopped: Boolean = false
) {
    fun isClicked(cx: Float, cy: Float): Boolean {
        if (isPopped) return false
        val distance = kotlin.math.sqrt((cx - x) * (cx - x) + (cy - y) * (cy - y))
        return distance <= radius
    }

    fun pop() {
        isPopped = true
    }
}
