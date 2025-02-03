package com.artinc.jobber.usecases

import com.artinc.jobber.interfaces.ApiRepository
import com.artinc.jobber.models.Offer

class GetOffersUseCase(private val repository: ApiRepository) {
    suspend operator fun invoke(): List<Offer> {
        return repository.getOffers() ?: emptyList()
    }
}