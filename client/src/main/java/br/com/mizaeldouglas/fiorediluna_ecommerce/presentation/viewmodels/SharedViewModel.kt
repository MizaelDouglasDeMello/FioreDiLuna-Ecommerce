package br.com.mizaeldouglas.fiorediluna_ecommerce.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _userEmail = MutableLiveData<String?>()
    val userEmail: LiveData<String?> get() = _userEmail

    private val _userName = MutableLiveData<String?>()
    val userName: LiveData<String?> get() = _userName

    fun setUserData(email: String?, name: String?) {
        _userEmail.value = email
        _userName.value = name
    }
}
