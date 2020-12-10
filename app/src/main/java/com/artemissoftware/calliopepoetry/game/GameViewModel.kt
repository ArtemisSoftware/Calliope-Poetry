package com.artemissoftware.calliopepoetry.game

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val timer: CountDownTimer

    companion object {

        // Time when the game is over
        private const val DONE = 0L

        // Countdown time interval
        private const val ONE_SECOND = 1000L

        // Total time for the game
        private const val COUNTDOWN_TIME = 60000L

    }

    // The current _word
    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word




    // The list of words - the front of the list is the next _word to guess
    private lateinit var wordList: MutableList<String>



    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }


    init {
        _word.value = ""
//        _score.value = 0
//        Log.i("GameViewModel", "GameViewModel created!")
        resetList()
        nextWord()

        // Creates a timer which triggers the end of the game when it finishes
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
//                _currentTime.value = millisUntilFinished/ONE_SECOND
            }

            override fun onFinish() {
//                _currentTime.value = DONE
//                onGameFinish()
            }
        }

        timer.start()
    }


    /**
     * Moves to the next _word in the list.
     */
    private fun nextWord() {
        if (wordList.isEmpty()) {
            resetList()

        } else {
            //Select and remove a _word from the list
            _word.value = wordList.removeAt(0)
        }
    }



    /**
     * Callback called when the ViewModel is destroyed
     */
    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
        timer.cancel()
    }
}