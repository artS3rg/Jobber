package com.artinc.jobber.interfaces

import com.artinc.jobber.models.Offer
import com.artinc.jobber.models.Vacancy

interface ApiRepository {
    suspend fun getOffers(): List<Offer>
    suspend fun getVacancies(): List<Vacancy>
}