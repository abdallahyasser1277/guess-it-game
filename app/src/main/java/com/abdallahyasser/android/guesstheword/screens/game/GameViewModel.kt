package com.abdallahyasser.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {
    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L
        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L
        // This is the total time of the game
        const val COUNTDOWN_TIME = 5000L
    }
    private val timer: CountDownTimer

    // The current word
    private val _word = MutableLiveData<String>()
    val word : LiveData<String> get() = _word

    // The current score
    private val _score = MutableLiveData<Int>()
    val score : LiveData<Int> get() =_score

    private val _eventGameFinish =MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
    get() =_eventGameFinish

    private val _currentTime =MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() =_currentTime

    val currentTimeString: LiveData<String>
        get() =Transformations.map(currentTime) {
            DateUtils.formatElapsedTime(it)
        }


    // The list of words - the front of the list is the next word to guess
    lateinit var wordList: MutableList<String>

    init {
        _score.value=0
        _eventGameFinish.value=false
        resetList()
        nextWord()
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                // TODO implement what should happen each tick of the timer
                _currentTime.value=(millisUntilFinished/ ONE_SECOND)
            }

            override fun onFinish() {
                _eventGameFinish.value=true
            }
        }
        timer.start()

    }

    fun resetList() {
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
            "bubble",
            "Elephant",
            "Guitar",
            "Pineapple",
            "Basketball",
            "Bicycle",
            "Watermelon",
            "Sunglasses",
            "Butterfly",
            "Pizza",
            "Football",
            "Rainbow",
            "Headphones",
            "Tiger",
            "Dolphin",
            "Ice cream",
            "Laptop",
            "Hammer",
            "Firework",
            "Spider",
            "Mountain",
            "Carrot",
            "Moon",
            "Camera",
            "Dragon",
            "Sunflower",
            "Snowman",
            "Alarm clock",
            "Whale",
            "Statue of Liberty",
            "Helicopter",
            "Chocolate",
            "Scissors",
            "Piano",
            "Giraffe",
            "Sailboat",
            "Hot air balloon",
            "Kangaroo",
            "Cactus",
            "Motorcycle",
            "Robot",
            "Statue",
            "Mushroom",
            "Lightning",
            "Pine tree",
            "Chicken",
            "Bee",
            "Castle",
            "Train",
            "Jellyfish",
            "Octopus",
            "Apple",
            "Banana",
            "Chair",
            "Dog",
            "Fish",
            "Hat",
            "Lemon",
            "Monkey",
            "Necklace",
            "Orange",
            "Quilt",
            "Rabbit",
            "Snake",
            "Tomato",
            "Umbrella",
            "Violin",
            "Xylophone",
            "Yak",
            "Zebra",
            "Beach",
            "Dinosaur",
            "Globe",
            "Ice skate",
            "Jellybean",
            "Koala",
            "Lighthouse",
            "Nectarine",
            "Ocean",
            "Panda",
            "Quill",
            "Telescope",
            "Unicorn",
            "Volcano",
            "Waterfall",
            "Yoga"
        )
        wordList.shuffle()
    }
    fun nextWord() {
        //Select and remove a word from the list

        if (wordList.isEmpty()) {
            resetList()
        }
        _word.value = wordList.removeAt(0)

    }
    fun onSkip() {
        _score.value=score.value?.dec()
        nextWord()
    }

    fun onCorrect() {
        _score.value=score.value?.inc()
        nextWord()
    }

    fun onGameFinishComplete() {
        _eventGameFinish.value=false
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
}


