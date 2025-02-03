package com.artinc.data.repositories

import com.artinc.data.apis.ApiService
import com.artinc.domain.interfaces.ApiRepository
import com.artinc.domain.models.Offer
import com.artinc.domain.models.Vacancy

class ApiRepositoryImpl(private val api: ApiService) : ApiRepository {
    override suspend fun getOffers(): List<Offer> {
        return api.getApi().offers ?: emptyList()
    }

    override suspend fun getVacancies(): List<Vacancy> {
        return api.getApi().vacancies ?: emptyList()
    }
}