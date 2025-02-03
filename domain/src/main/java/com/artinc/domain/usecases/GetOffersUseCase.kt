package com.artinc.domain.usecases

import com.artinc.domain.interfaces.ApiRepository
import com.artinc.domain.models.Offer

class GetOffersUseCase(private val repository: ApiRepository) {
    suspend operator fun invoke(): List<Offer> {
        return repository.getOffers() ?: emptyList()
    }
}