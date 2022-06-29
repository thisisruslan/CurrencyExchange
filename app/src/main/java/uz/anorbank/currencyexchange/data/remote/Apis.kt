package uz.anorbank.currencyexchange.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url
import uz.anorbank.currencyexchange.data.remote.dataclasses.AnorBankData
import uz.anorbank.currencyexchange.data.remote.dataclasses.CentralBankData

interface Apis {
    @GET("/api/v1/currencies/rates/exchange")
    suspend fun getAnorBankCurrencyRates(): Response<AnorBankData>

    @GET
    suspend fun getCentralBankCurrencyRates(@Url url: String): Response<List<CentralBankData>>
}