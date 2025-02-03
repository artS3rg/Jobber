package com.artinc.jobber.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artinc.jobber.models.Offer
import com.artinc.jobber.models.Vacancy
import com.artinc.jobber.usecases.GetOffersUseCase
import com.artinc.jobber.usecases.GetVacanciesUseCase
import kotlinx.coroutines.launch

class ItemViewModel(
    private val getOffersUseCase: GetOffersUseCase,
    private val getVacanciesUseCase: GetVacanciesUseCase
) : ViewModel() {
    private val _offers = MutableLiveData<List<Offer>>(emptyList())
    val offers: LiveData<List<Offer>> get() = _offers

    private val _vacancies = MutableLiveData<List<Vacancy>>(emptyList())
    val vacancies: LiveData<List<Vacancy>> get() = _vacancies

    fun getOffers() {
        viewModelScope.launch {
            _offers.postValue(getOffersUseCase() ?: emptyList())
        }
    }

    fun getVacancies() {
        viewModelScope.launch {
            _vacancies.postValue(getVacanciesUseCase() ?: emptyList())
        }
    }
}