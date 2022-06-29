package uz.anorbank.currencyexchange.domain.repository.impl

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.anorbank.currencyexchange.BuildConfig.CENTRALBANK_URL
import uz.anorbank.currencyexchange.data.remote.Apis
import uz.anorbank.currencyexchange.data.remote.dataclasses.AnorBankData
import uz.anorbank.currencyexchange.data.remote.dataclasses.CentralBankData
import uz.anorbank.currencyexchange.data.remote.dataclasses.ErrorResponse
import uz.anorbank.currencyexchange.domain.repository.MainRepository
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class MainRepositoryImpl @Inject constructor(
    private val api: Apis,
    private val gson: Gson
) : MainRepository {
    override fun getAnorBankData(): Flow<Result<AnorBankData>> = flow {
        val response = api.getAnorBankCurrencyRates()
        if (response.isSuccessful) {
            emit(Result.success(response.body()!!))
        } else {
            var str = "Error"
            response.errorBody()?.let {
                str = gson.fromJson(it.string(), ErrorResponse::class.java).message
            }
            emit(Result.failure(Throwable(str)))
        }
    }.flowOn(IO)

    override fun getCentralBankData(): Flow<Result<List<CentralBankData>>> = flow {
        val response = api.getCentralBankCurrencyRates(CENTRALBANK_URL)
        if (response.isSuccessful) {
            emit(Result.success(response.body()!!))
        } else {
            var str = "Error"
            response.errorBody()?.let {
                str = gson.fromJson(it.string(), ErrorResponse::class.java).message
            }
            emit(Result.failure(Throwable(str)))
        }
    }.flowOn(IO)

    override fun getLoadedTime(): Flow<String> = flow {
        val time: Date = Calendar.getInstance().time
        val dataFormat = SimpleDateFormat("hh:mm:ss", Locale.getDefault())
        emit(dataFormat.format(time))
    }.flowOn(IO)

}