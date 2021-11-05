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

    private val _menuTabLiveData = MutableLiveData(Category.SIGNATURE.title)
    val menuTabLiveData: LiveData<String> get() = _menuTabLiveData

    private val _menuListLiveData = MutableLiveData<List<Menu>>()
    val menuListLiveData: LiveData<List<Menu>> get() = _menuListLiveData

    private val firestore by lazy { Firebase.firestore }

    private var menuList = arrayListOf<Menu>()

    init {
        firestore.collection(Category.SIGNATURE.name)
            .get()
            .addOnSuccessListener {
                menuList.clear()
                menuList.addAll(it.toObjects(Menu::class.java))

                _menuListLiveData.value = menuList
            }
    }

    fun onSignatureChecked() {
        _checkedLiveData.value = 0
        _menuTabLiveData.value = Category.SIGNATURE.title

        firestore.collection(Category.SIGNATURE.name)
            .get()
            .addOnSuccessListener {
                menuList.clear()
                menuList.addAll(it.toObjects(Menu::class.java))

                _menuListLiveData.value = menuList
            }
    }

    fun onMainMenuChecked() {
        _checkedLiveData.value = 1
        _menuTabLiveData.value = Category.SPECIAL_MENU.title

        firestore.collection(Category.SPECIAL_MENU.name)
            .get()
            .addOnSuccessListener {
                menuList.clear()
                menuList.addAll(it.toObjects(Menu::class.java))
                _menuListLiveData.value = menuList
            }
    }

    fun onAlcoholicChecked() {
        _checkedLiveData.value = 2
        _menuTabLiveData.value = Category.NORMAL_MENU.title

        firestore.collection(Category.NORMAL_MENU.name)
            .get()
            .addOnSuccessListener {
                menuList.clear()
                menuList.addAll(it.toObjects(Menu::class.java))

                _menuListLiveData.value = menuList
            }
    }

    fun onDrinkChecked() {
        _checkedLiveData.value = 3
        _menuTabLiveData.value = Category.FRUIT_MENU.title

        firestore.collection(Category.FRUIT_MENU.name)
            .get()
            .addOnSuccessListener {
                menuList.clear()
                menuList.addAll(it.toObjects(Menu::class.java))

                _menuListLiveData.value = menuList
            }
    }

    fun onAdditionalMenuChecked() {
        _checkedLiveData.value = 4
        _menuTabLiveData.value = Category.DRINKS.title

        firestore.collection(Category.DRINKS.name)
            .get()
            .addOnSuccessListener {
                menuList.clear()
                menuList.addAll(it.toObjects(Menu::class.java))

                _menuListLiveData.value = menuList
            }
    }

    enum class Category(
        val title: String,
    ) {
        SIGNATURE("인쌩 시그니처"),
        SPECIAL_MENU("인쌩 특별메뉴"),
        NORMAL_MENU("일반 메뉴"),
        FRUIT_MENU("과일 메뉴"),
        DRINKS("주류")
    }

}