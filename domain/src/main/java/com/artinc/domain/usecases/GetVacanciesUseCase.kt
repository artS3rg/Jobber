package com.artinc.domain.usecases

import com.artinc.domain.interfaces.ApiRepository
import com.artinc.domain.models.Vacancy

class GetVacanciesUseCase(private val repository: ApiRepository) {
    suspend operator fun invoke(): List<Vacancy> {
        return repository.getVacancies() ?: emptyList()
    }
}