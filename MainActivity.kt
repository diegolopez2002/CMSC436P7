package com.example.project7

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader

// Group Members: [Your Names Here]

class MainActivity : ComponentActivity() {
    private lateinit var balloons: Balloons
    private lateinit var gameView: GameView

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Parse JSON file
        val jsonString = readJsonFile(R.raw.balloons3)
        val jsonArray = JSONArray(jsonString)
        balloons = Balloons()

        // Populate balloons and log data
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val x      = jsonObject.getDouble("x").toFloat()
            val y      = jsonObject.getDouble("y").toFloat()
            val radius = jsonObject.getDouble("radius").toFloat()

            balloons.addBalloon(Balloon(x, y, radius))
        }

// 2) Now log exactly once per balloon
        val tag = "MainActivity"
        for (b in balloons.getBalloons()) {        // or `.all` if that’s your API
            val sx = b.x.toInt()
            val sy = b.y.toInt()
            val sr = b.radius.toInt()
            Log.i(tag, "$sx; $sy; $sr")
        }

        // Set up GameView
        gameView = GameView(this, balloons)
        setContentView(gameView)

        // Set click listener for popping balloons
        gameView.setOnTouchListener { _, event ->
            val x = event.x
            val y = event.y
            val popped = balloons.popBalloon(x, y)
            if (popped) {
                gameView.invalidate() // Redraw view
            }

            // Check game status
            if (balloons.isGameWon()) {
                Toast.makeText(this, "YOU WON", Toast.LENGTH_LONG).show()
                gameView.isEnabled = false
            } else if (balloons.isGameLost()) {
                Toast.makeText(this, "YOU LOST", Toast.LENGTH_LONG).show()
                gameView.isEnabled = false
            }
            true
        }
    }

    private fun readJsonFile(resourceId: Int): String {
        val inputStream = resources.openRawResource(resourceId)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            stringBuilder.append(line)
        }
        reader.close()
        inputStream.close()
        return stringBuilder.toString()
    }
}
