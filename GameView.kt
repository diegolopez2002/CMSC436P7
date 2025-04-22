package com.example.project7

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

@SuppressLint("ViewConstructor")
class GameView(context: Context, private val balloons: Balloons) : View(context) {
    private val paint = Paint().apply {
        color = Color.BLUE // Balloon color defined in code
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (balloon in balloons.getBalloons()) {
            if (!balloon.isPopped) {
                canvas.drawCircle(balloon.x, balloon.y, balloon.radius, paint)
            }
        }
    }
}
