package uz.anorbank.currencyexchange.data.remote

import retrofit2.Response
import retrofit2.http.GET
import uz.anorbank.currencyexchange.data.remote.dataclasses.AnorBankData

interface Apis {
    @GET("/api/v1/currencies/rates/exchange")
    suspend fun getAnorBankCurrencyRates(): Response<AnorBankData>

}