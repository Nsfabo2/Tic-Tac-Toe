package com.example.tic_tac_toe

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private lateinit var TopLeft: Button
    private lateinit var TopCenter: Button
    private lateinit var TopRight: Button
    private lateinit var MiddelLeft: Button
    private lateinit var MiddelCenter: Button
    private lateinit var MiddelRight: Button
    private lateinit var BottomLeft: Button
    private lateinit var BottomCenter: Button
    private lateinit var BottomRight: Button

    private lateinit var QTV: TextView
    private lateinit var WinnerTV: TextView
    private lateinit var WinnerAnimation: Animation

    private lateinit var Player1: CardView
    private lateinit var Player2: CardView

    private lateinit var Player1TV: TextView
    private lateinit var Player2TV: TextView

    private lateinit var Buttons: List<Button>
    private var PlayerTurn = true
    private var TRY = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        QTV = findViewById(R.id.QTV)
        WinnerTV = findViewById(R.id.WinnerTV)

        Player1 = findViewById(R.id.Player1)
        Player2 = findViewById(R.id.Player2)

        Player1TV = findViewById(R.id.Player1TV)
        Player2TV = findViewById(R.id.Player2TV)

        Player1.setBackgroundColor(Color.MAGENTA)

        TopLeft = findViewById(R.id.TopLeft)
        TopCenter = findViewById(R.id.TopCenter)
        TopRight = findViewById(R.id.TopRight)
        MiddelLeft = findViewById(R.id.MiddelLeft)
        MiddelCenter = findViewById(R.id.MiddelCenter)
        MiddelRight = findViewById(R.id.MiddelRight)
        BottomLeft = findViewById(R.id.BottomLeft)
        BottomCenter = findViewById(R.id.BottomCenter)
        BottomRight = findViewById(R.id.BottomRight)

        Buttons = listOf(TopLeft, TopCenter, TopRight, MiddelLeft, MiddelCenter, MiddelRight, BottomLeft, BottomCenter, BottomRight)

        for( B in Buttons){
            addOnClickListener(B) }

    }// end oncreate

    private fun addOnClickListener(button: Button){
        button.setOnClickListener {
            if(!WinnerTV.isVisible){
                if(button.text.isBlank()){
                    if(PlayerTurn){
                        button.text = "X"
                        if(checkWin()){
                            gameOver(1)
                        }else{
                            PlayerTurn = false
                            Player2.setBackgroundColor(Color.MAGENTA)
                            Player1.setBackgroundColor(Color.WHITE)
                        }
                    }else{
                        button.text = "O"
                        if(checkWin()){
                            gameOver(2)
                        }else{
                            PlayerTurn = true
                            Player1.setBackgroundColor(Color.MAGENTA)
                            Player2.setBackgroundColor(Color.WHITE)
                        }
                    }
                }else{
                    Toast.makeText(this, "Please choose another tile", Toast.LENGTH_LONG).show()
                }
            }
            TRY = true
            for( button in Buttons){
                if(button.text.isBlank()){TRY = false}
            }
            if(TRY){gameOver(0)}
        }
    }

    private fun gameOver(player: Int){
        if(player>0){
            WinnerTV.text = "Player $player Wins!"
        }else{
            WinnerTV.text = "Draw"
        }
        WinnerTV.isVisible = true
        QTV.isVisible = true
        Player1TV.text = "YES"
        Player1.setOnClickListener { this.recreate() }
        Player2TV.text = "NO"
        Player1.setBackgroundColor(Color.BLUE)
        Player2.setBackgroundColor(Color.BLUE)
    }

    private fun checkWin(): Boolean{
        if(TopLeft.text == TopCenter.text && TopLeft.text == TopRight.text && TopLeft.text.isNotBlank()){
            return true }
        if(MiddelLeft.text == MiddelCenter.text && MiddelLeft.text == MiddelRight.text && MiddelLeft.text.isNotBlank()){
            return true }
        if(BottomLeft.text == BottomCenter.text && BottomLeft.text == BottomRight.text && BottomLeft.text.isNotBlank()){
            return true }
        if(TopLeft.text == MiddelLeft.text && TopLeft.text == BottomLeft.text && TopLeft.text.isNotBlank()){
            return true }
        if(TopCenter.text == MiddelCenter.text && TopCenter.text == BottomCenter.text && TopCenter.text.isNotBlank()){
            return true }
        if(TopRight.text == MiddelRight.text && TopRight.text == BottomRight.text && TopRight.text.isNotBlank()){
            return true }
        if(TopLeft.text == MiddelCenter.text && TopLeft.text == BottomRight.text && TopLeft.text.isNotBlank()){
            return true }
        if(BottomLeft.text == MiddelCenter.text && MiddelCenter.text == TopRight.text && BottomLeft.text.isNotBlank()){
            return true }
        return false
    }

}// end class