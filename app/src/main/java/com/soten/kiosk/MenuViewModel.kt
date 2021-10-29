package com.soten.kiosk

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MenuViewModel : ViewModel() {

    private val _checkedLiveData = MutableLiveData(0)
    val checkedLiveData: LiveData<Int> get() = _checkedLiveData

    fun onSignatureChecked() {
        _checkedLiveData.value = 0
    }

    fun onMainMenuChecked() {
        _checkedLiveData.value = 1
    }

    fun onAlcoholicChecked() {
        _checkedLiveData.value = 2
    }

    fun onDrinkChecked() {
        _checkedLiveData.value = 3
    }

    fun onAdditionalMenuChecked() {
        _checkedLiveData.value = 4
    }

}