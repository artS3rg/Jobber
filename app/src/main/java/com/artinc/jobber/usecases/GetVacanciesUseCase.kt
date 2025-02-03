package com.artinc.jobber.usecases

import android.util.Log
import com.artinc.jobber.interfaces.ApiRepository
import com.artinc.jobber.models.Vacancy

class GetVacanciesUseCase(private val repository: ApiRepository) {
    suspend operator fun invoke(): List<Vacancy> {
        return repository.getVacancies() ?: emptyList()
    }
}