package com.udacity.shoestore.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    val email = MutableLiveData("")
    val password = MutableLiveData("")

    private val _areFieldsFilled = MutableLiveData(false)

    val areFieldsFilled: LiveData<Boolean>
        get() = _areFieldsFilled

    fun fieldsUpdated() {
        _areFieldsFilled.postValue(!email.value.isNullOrBlank() && !password.value.isNullOrBlank())
    }
}