package com.example.diceapp

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private fun rollDice(): Int {
        val randomNum = (1..6).random()
        return when (randomNum) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }

    private fun animateDice(dice: ImageView, newImage: Int ,dur:Long ) {
        // Rotation on X-axis
        val rotateX = ObjectAnimator.ofFloat(dice, "rotationX", 0f, 1080f)
        // Rotation on Y-axis
        val rotateY = ObjectAnimator.ofFloat(dice, "rotationY", 0f, 1080f)
        // Random translation (simulate dice movement)
//        val translateX = ObjectAnimator.ofFloat(dice, "translationX", 0f, (-100..100).random().toFloat())
//        val translateY = ObjectAnimator.ofFloat(dice, "translationY", 0f, (-100..100).random().toFloat())

        // Duration for all animations
        val duration = dur
        rotateX.duration = duration
        rotateY.duration = duration
//        translateX.duration = duration
//        translateY.duration = duration

        // AnimatorSet to play animations together
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(rotateX , rotateY)
        animatorSet.addListener(object : android.animation.AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: android.animation.Animator ) {
                super.onAnimationEnd(animation)
                dice.setImageResource(newImage) // Update the dice image after animation
                dice.translationX = 0f // Reset translation for future rolls
                dice.translationY = 0f
            }
        })

        animatorSet.start()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val iv1: ImageView = findViewById(R.id.dice_1_iv)
        val iv2: ImageView = findViewById(R.id.dice_2_iv)
        val btn: Button = findViewById(R.id.roll_button)

        btn.setOnClickListener {
            // Animate both dice with a realistic rolling effect
            animateDice(iv1, rollDice() , 1000L)

            animateDice(iv2, rollDice(),800L)
        }
    }
}
