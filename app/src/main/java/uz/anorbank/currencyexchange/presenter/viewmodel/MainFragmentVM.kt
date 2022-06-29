package uz.anorbank.currencyexchange.presenter.viewmodel

import androidx.lifecycle.LiveData
import uz.anorbank.currencyexchange.data.remote.dataclasses.AnorBankData
import uz.anorbank.currencyexchange.data.remote.dataclasses.CentralBankData


interface MainFragmentVM {
    val loading: LiveData<Boolean>
    val successGetAnorBankDataLD: LiveData<AnorBankData>
    val errorGetAnorBankDataLD: LiveData<String>
    fun getAnorBankData()

    val successGetCentralBankDataLD: LiveData<List<CentralBankData>>
    val errorGetCentralBankDataLD: LiveData<String>
    fun getCentralBankData()

    val getLoadedTimeLD: LiveData<String>
    fun getLoadedTime()

}