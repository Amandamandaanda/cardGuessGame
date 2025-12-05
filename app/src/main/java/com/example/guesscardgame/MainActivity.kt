package com.example.guesscardgame
import CardDeck
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {

    // ======== Variables for UI and gamestates ========
    lateinit var revealCardView: ImageView
    lateinit var scoreText: TextView
    var score = 0
    val deck = CardDeck()

    // ======== Activity start ========

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        revealCardView = findViewById(R.id.cardImageView)
        scoreText = findViewById(R.id.scoreText)

       val clubButton = findViewById<ImageButton>(R.id.btn_club)
       clubButton.setOnClickListener {
           guess("club", clubButton)
       }

       val heartButton = findViewById<ImageButton>(R.id.btn_heart)
        heartButton.setOnClickListener {
            guess("heart", heartButton)
        }

       val spadeButton = findViewById<ImageButton>(R.id.btn_spade)
        spadeButton.setOnClickListener {
            guess("spade", spadeButton)
        }

       val diamondButton = findViewById<ImageButton>(R.id.btn_diamond)
        diamondButton.setOnClickListener {
            guess("diamond", diamondButton)
        }

       val exitGameButton = findViewById<Button>(R.id.exitGameButton)
       exitGameButton.setOnClickListener {
           finish()
       }
    }

    // ======== Handling cards ========
    private fun showNewCard() {
        deck.getNewCard()

        revealCardView.setImageResource(R.drawable.card_start)
    }

    private fun revealCard() {
        val suit = deck.currentCard.suit
        val revealedCardImage = when (suit) {
            "heart" -> R.drawable.card_heart
            "spade" -> R.drawable.card_spade
            "diamond" -> R.drawable.card_diamond
            else -> R.drawable.card_club
        }
        revealCardView.setImageResource(revealedCardImage)
    }

    // ======= Guess card handling =======
    private fun guess(suit: String, button: ImageButton) {

        // ======== Button animation ========
        val pressedButton = when (suit) {
            "club" -> R.drawable.pressed_club
            "heart" -> R.drawable.pressed_heart
            "spade" -> R.drawable.pressed_spade
            "diamond" -> R.drawable.pressed_diamond
            else -> null
        }

        val notPressedButton = when (suit) {
            "club" -> R.drawable.btn_club
            "heart" -> R.drawable.btn_heart
            "spade" -> R.drawable.btn_spade
            "diamond" -> R.drawable.btn_diamond
            else -> null
        }

        button.setImageResource(pressedButton!!)

        button.postDelayed({
            button.setImageResource(notPressedButton!!)
        }, 200)

        // ======== Reveal card ========
        revealCard()

        // ======== Feedback right/wrong ========
        val feedbackImage: ImageView = findViewById(R.id.feedbackImageView)
        val feedBackDrawable = if (suit == deck.currentCard.suit) {
            R.drawable.ratt_
        } else {
            R.drawable.fel
        }

        // ======== Update score ========
        if (suit == deck.currentCard.suit) {
            score ++
        }

        scoreText.text = "Poäng: $score"

        feedbackImage.setImageResource(feedBackDrawable)
        feedbackImage.visibility = View.VISIBLE

        // ======== Next card ========
        revealCardView.postDelayed({
            feedbackImage.visibility = View.INVISIBLE
            showNewCard()
        }, 2000)
    }
}
