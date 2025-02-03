package com.artinc.domain.interfaces

import com.artinc.domain.models.Offer
import com.artinc.domain.models.Vacancy

interface ApiRepository {
    suspend fun getOffers(): List<Offer>
    suspend fun getVacancies(): List<Vacancy>
}