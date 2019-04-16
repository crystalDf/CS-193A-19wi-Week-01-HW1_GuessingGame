package com.star.hw1_guessinggame

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_number_guess.*
import java.util.*

class NumberGuessActivity : AppCompatActivity() {

    private var target = 0
    private var guessTimes = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number_guess)

        initGame()
    }

    private fun initGame() {

        val r = Random()

        target = r.nextInt(1000) + 1
        guessTimes = 0
        guess_button.isEnabled = true
        number_edit_text.text.clear()
        hint.text = ""
    }

    fun guessClick(view: View) {

        val guess: Int

        try {
            guess = number_edit_text.text.toString().toInt()
        } catch (e: Exception) {
            checkIncorrectInput()
            return
        }

        if (guess !in 1..1000) {
            checkIncorrectInput()
            return
        }

        guessTimes++

        when {
            guess < target -> hint.text = getString(R.string.higher)
            guess > target -> hint.text = getString(R.string.lower)
            else -> {
                hint.text = "You got it! Number of guesses: $guessTimes"
                guess_button.isEnabled = false
            }
        }
    }

    private fun checkIncorrectInput() {
        Toast.makeText(this, "Please input a number from 1 to 1000!", Toast.LENGTH_SHORT).show()
        number_edit_text.text.clear()
        hint.text = ""
    }

    fun playAgainClick(view: View) {

        initGame()
    }
}
