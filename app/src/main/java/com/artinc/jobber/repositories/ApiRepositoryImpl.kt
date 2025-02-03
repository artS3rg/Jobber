package com.artinc.jobber.repositories

import android.util.Log
import com.artinc.jobber.apis.ApiService
import com.artinc.jobber.interfaces.ApiRepository
import com.artinc.jobber.models.Offer
import com.artinc.jobber.models.Vacancy

class ApiRepositoryImpl(private val api: ApiService) : ApiRepository {
    override suspend fun getOffers(): List<Offer> {
        return api.getApi().offers ?: emptyList()
    }

    override suspend fun getVacancies(): List<Vacancy> {
        return api.getApi().vacancies ?: emptyList()
    }
}