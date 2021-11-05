package com.soten.kiosk.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.soten.kiosk.model.Menu

class MenuViewModel : ViewModel() {

    private val _checkedLiveData = MutableLiveData(0)
    val checkedLiveData: LiveData<Int> get() = _checkedLiveData

    private val _menuTabLiveData = MutableLiveData(LIFE_SIGNATURE)
    val menuTabLiveData: LiveData<String> get() = _menuTabLiveData

    private val _menuListLiveData = MutableLiveData<List<Menu>>()
    val menuListLiveData: LiveData<List<Menu>> get() = _menuListLiveData

    private val firestore by lazy { Firebase.firestore }
    private var menuList = mutableListOf<Menu>()

    init {
        firestore.collection("menu")
            .get()
            .addOnSuccessListener {
                menuList.clear()
                menuList.addAll(it.toObjects(Menu::class.java))

                _menuListLiveData.value = menuList
            }
    }

    fun onSignatureChecked() {
        _checkedLiveData.value = 0
        _menuTabLiveData.value = LIFE_SIGNATURE

    }

    fun onMainMenuChecked() {
        _checkedLiveData.value = 1
        _menuTabLiveData.value = SPECIAL_MENU
    }

    fun onAlcoholicChecked() {
        _checkedLiveData.value = 2
        _menuTabLiveData.value = NORMAL_MENU
    }

    fun onDrinkChecked() {
        _checkedLiveData.value = 3
        _menuTabLiveData.value = FRUIT_MENU
    }

    fun onAdditionalMenuChecked() {
        _checkedLiveData.value = 4
        _menuTabLiveData.value = DRINKS
    }

    companion object {
        private const val LIFE_SIGNATURE = "인쌩 시그니처"
        private const val SPECIAL_MENU = "인쌩 특별메뉴"
        private const val NORMAL_MENU = "일반 메뉴"
        private const val FRUIT_MENU = "과일 메뉴"
        private const val DRINKS = "주류"
    }

}