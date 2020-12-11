package com.artemissoftware.calliopepoetry.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.*

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

    // The current score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score


    // Countdown time
    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    // The String version of the current time
    val currentTimeString: LiveData<String> = Transformations.map(currentTime) { time ->
        DateUtils.formatElapsedTime(time)
    }


    // Event which triggers the end of the game
    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish



    private val _motivationMessage = MediatorLiveData<String>().apply {

        fun motivatePlayer(){

            score.value?.let {
                if(it == 2){
                    value = "My poetry is godlike"
                }
            }

        }

        fun advicePlayer(){

            currentTime.value?.let {

                if(it == (COUNTDOWN_TIME/2/ONE_SECOND)){
                    value = "Time is running out"
                }

            }
        }


        addSource(score){
            motivatePlayer()
        }

        addSource(currentTime){
            advicePlayer()
        }
    }


    val motivationMessage: LiveData<String>
        get() = _motivationMessage


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
        _score.value = 0

        resetList()
        nextWord()

        // Creates a timer which triggers the end of the game when it finishes
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished/ONE_SECOND
            }

            override fun onFinish() {
                _currentTime.value = DONE
                onGameFinish()
            }
        }

        timer.start()
    }




    /** Methods for updating the UI **/
    fun onSkip() {
        _score.value = (_score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (_score.value)?.plus(1)
        nextWord()
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


    /** Method for the game completed event **/

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }

    fun onGameFinish() {
        _eventGameFinish.value = true
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