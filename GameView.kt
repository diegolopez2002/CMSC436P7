package com.example.project7

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import android.widget.Toast

class GameView(context: Context, val balloons: Balloons) : View(context) {
    private val paint = Paint()
    private var gameOver = false

    init {
        // Set the color in code (choose your color)
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) return

        // Draw each balloon if it has not been popped
        for (balloon in balloons.balloonList) {
            if (!balloon.isPopped) {
                canvas.drawCircle(balloon.x, balloon.y, balloon.radius, paint)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null || gameOver) return false

        if (event.action == MotionEvent.ACTION_DOWN) {
            val touchX = event.x
            val touchY = event.y
            balloons.incrementAttempt()  // Count every touch as an attempt

            // Check each balloon to see if the touch is inside it
            for (balloon in balloons.balloonList) {
                if (!balloon.isPopped && balloon.contains(touchX, touchY)) {
                    balloon.pop()
                    break  // Only one balloon is popped per touch
                }
            }

            // Evaluate game state after the attempt
            if (balloons.isWin()) {
                Toast.makeText(context, "YOU WON", Toast.LENGTH_SHORT).show()
                gameOver = true
            } else if (balloons.isLose()) {
                Toast.makeText(context, "YOU LOST", Toast.LENGTH_SHORT).show()
                gameOver = true
            }
            invalidate()  // Force redraw to reflect updated balloons
        }
        return true
    }
}
