package com.example.project7

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.xml.sax.InputSource
import org.xml.sax.XMLReader
import java.io.InputStream
import javax.xml.parsers.SAXParserFactory

// Diego Lopez, Shifa Bhutta, Zhuo Cheng Xie, Riya Singhal

class MainActivity : AppCompatActivity() {
    private lateinit var gameView: GameView
    private lateinit var balloons: Balloons
    private var attempts = 0
    private var maxAttempts = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameView = findViewById(R.id.gameView)
        balloons = parseXML(R.raw.balloons3) // Default to balloons3.xml
        maxAttempts = balloons.balloons.size + 3

        balloons.balloons.forEach { balloon ->
            Log.d("MainActivity", "Balloon: x=${balloon.x}, y=${balloon.y}, radius=${balloon.radius}")
        }

        gameView.setBalloons(balloons)
    }

    private fun parseXML(resourceId: Int): Balloons {
        val parserFactory = SAXParserFactory.newInstance()
        val parser = parserFactory.newSAXParser()
        val xmlReader: XMLReader = parser.xmlReader
        val saxHandler = SAXHandler()
        xmlReader.contentHandler = saxHandler

        val inputStream: InputStream = resources.openRawResource(resourceId)
        xmlReader.parse(InputSource(inputStream))

        return saxHandler.getBalloons()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            attempts++
            if (balloons.popBalloon(event.x, event.y)) {
                gameView.invalidate()
            }

            if (balloons.allPopped()) {
                Toast.makeText(this, "YOU WON", Toast.LENGTH_LONG).show()
            } else if (attempts >= maxAttempts) {
                Toast.makeText(this, "Game Over. You ran out of attempts!", Toast.LENGTH_LONG).show()
                finish()
            }
        }
        return super.onTouchEvent(event)
    }
}
