package uz.anorbank.currencyexchange.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.anorbank.currencyexchange.data.remote.dataclasses.AnorBankData
import uz.anorbank.currencyexchange.data.remote.dataclasses.CentralBankData


interface MainRepository  {
    fun getAnorBankData() : Flow<Result<AnorBankData>>
    fun getLoadedTime() : Flow<String>
}