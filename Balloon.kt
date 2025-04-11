
package com.example.project7

data class Balloon(var x: Float, var y: Float, var radius: Float, var isPopped: Boolean = false)

fun Balloon.isInside(touchX: Float, touchY: Float): Boolean {
    val dx = touchX - x
    val dy = touchY - y
    return dx * dx + dy * dy <= radius * radius
}
