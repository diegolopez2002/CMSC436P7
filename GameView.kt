
package com.example.project7

import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler

class GameView : DefaultHandler() {
    private val balloons = Balloons()
    private var currentElement: String? = null
    private var currentX: Float = 0f
    private var currentY: Float = 0f
    private var currentRadius: Float = 0f

    fun getBalloons(): Balloons {
        return balloons
    }

    override fun startElement(uri: String?, localName: String?, qName: String?, attributes: Attributes?) {
        currentElement = qName
    }

    override fun characters(ch: CharArray?, start: Int, length: Int) {
        val value = ch?.let { String(it, start, length) }
        when (currentElement) {
            "x" -> currentX = value?.toFloat() ?: 0f
            "y" -> currentY = value?.toFloat() ?: 0f
            "radius" -> currentRadius = value?.toFloat() ?: 0f
        }
    }

    override fun endElement(uri: String?, localName: String?, qName: String?) {
        if (qName == "balloon") {
            val balloon = Balloon(currentX, currentY, currentRadius)
            balloons.addBalloon(balloon)
        }
        currentElement = null
    }
}
