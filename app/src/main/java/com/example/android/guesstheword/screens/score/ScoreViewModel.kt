package com.example.android.guesstheword.screens.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int): ViewModel() {

    private var _eventPlayAgain = MutableLiveData<Boolean>()
    val eventPlayAgain : LiveData<Boolean> get() = _eventPlayAgain

    private var _score = MutableLiveData<Int>()
    val score : LiveData<Int> get() = _score

    init {
        _eventPlayAgain.value=false
        _score.value=finalScore
    }

    fun onPlayAgainComplete(){
        _eventPlayAgain.value=false
    }

    fun onPlayAgain(){
        _eventPlayAgain.value=true
    }


}