package uz.anorbank.currencyexchange.presenter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.anorbank.currencyexchange.data.remote.dataclasses.AnorBankData
import uz.anorbank.currencyexchange.data.remote.dataclasses.CentralBankData
import uz.anorbank.currencyexchange.domain.repository.MainRepository
import xyz.teamgravity.checkinternet.CheckInternet
import javax.inject.Inject

@HiltViewModel
class MainFragmentVMImpl @Inject constructor(
    private val mainRepository: MainRepository
) : MainFragmentVM, ViewModel() {

    override val loading = MutableLiveData<Boolean>()


    override val successGetAnorBankDataLD = MutableLiveData<AnorBankData>()
    override val errorGetAnorBankDataLD = MutableLiveData<String>()
    override fun getAnorBankData() {

        loading.value = true
        mainRepository.getAnorBankData().onEach { result ->
            result.onSuccess {
                successGetAnorBankDataLD.value = it
            }
            result.onFailure {
                errorGetAnorBankDataLD.value = it.message
            }
        }.catch {
            loading.value = false
            errorGetAnorBankDataLD.value = it.message
        }.launchIn(viewModelScope)

    }

    override val successGetCentralBankDataLD = MutableLiveData<List<CentralBankData>>()
    override val errorGetCentralBankDataLD = MutableLiveData<String>()
    override fun getCentralBankData() {

        loading.value = true
        mainRepository.getCentralBankData().onEach { result ->
            result.onSuccess {
                successGetCentralBankDataLD.value = it
            }
            result.onFailure {
                errorGetCentralBankDataLD.value = it.message
            }
        }.catch {
            loading.value = false
            errorGetCentralBankDataLD.value = it.message
        }.launchIn(viewModelScope)
    }

    override val getLoadedTimeLD = MutableLiveData<String>()
    override fun getLoadedTime() {
        mainRepository.getLoadedTime().onEach {
            getLoadedTimeLD.value = it
        }.launchIn(viewModelScope)
    }

}