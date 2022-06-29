package uz.anorbank.currencyexchange.presenter.ui

import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.anorbank.currencyexchange.R
import uz.anorbank.currencyexchange.data.local.CurrencyEnum
import uz.anorbank.currencyexchange.data.remote.dataclasses.AnorBankDataItem
import uz.anorbank.currencyexchange.databinding.FragmentMainBinding
import uz.anorbank.currencyexchange.presenter.viewmodel.MainFragmentVM
import uz.anorbank.currencyexchange.presenter.viewmodel.MainFragmentVMImpl
import uz.anorbank.currencyexchange.utils.showToast
import xyz.teamgravity.checkinternet.CheckInternet

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding by viewBinding(FragmentMainBinding::bind)
    private val viewModel: MainFragmentVM by viewModels<MainFragmentVMImpl>()
    private lateinit var nameViews: Array<AppCompatTextView>
    private lateinit var sellRateViews: Array<AppCompatTextView>
    private lateinit var buyRateViews: Array<AppCompatTextView>
    private lateinit var cbRateViews: Array<AppCompatTextView>
    private lateinit var flagViews: Array<AppCompatImageView>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setObservers()
        initValues()
        setListeners()
        viewModel.getAnorBankData()
        viewModel.getLoadedTime()
        CheckInternet().check { connected ->
            if (!connected) showToast("Интернет не подключен!")
        }
    }

    private fun initValues() = with(binding) {
        nameViews = arrayOf(tvBaseName1, tvBaseName2, tvBaseName3)
        sellRateViews = arrayOf(tvRateSell1, tvRateSell2, tvRateSell3)
        buyRateViews = arrayOf(tvRateBuy1, tvRateBuy2, tvRateBuy3)
        cbRateViews = arrayOf(tvRateCb1, tvRateCb2, tvRateCb3)
        flagViews = arrayOf(imgFlag1, imgFlag2, imgFlag3)
    }

    private fun setListeners() {
        binding.reload.setOnRefreshListener {
            viewModel.getAnorBankData()
            viewModel.getLoadedTime()
        }
    }

    private fun setObservers() = with(viewModel) {
        successGetAnorBankDataLD.observe(viewLifecycleOwner) {
            setResult(it.list)
            binding.reload.isRefreshing = false
        }

        errorGetAnorBankDataLD.observe(viewLifecycleOwner) {
            binding.reload.isRefreshing = false
            showToast(it)
        }

        loading.observe(viewLifecycleOwner) { binding.loading.isVisible = it }

        getLoadedTimeLD.observe(viewLifecycleOwner) {
            binding.tvTime.text = Html.fromHtml("Обновлено $it", HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }

    private fun setResult(list: List<AnorBankDataItem>) {
        for (i in list.indices) {
            val item = list[i]
            nameViews[i].text = item.baseName
            sellRateViews[i].text = item.rateSell.toString()
            buyRateViews[i].text = item.rateBuy.toString()
            cbRateViews[i].text = item.rateCB.toString()

            when (item.baseName) {
                CurrencyEnum.EUR.name -> flagViews[i].setImageResource(R.drawable.ic_euro)
                CurrencyEnum.USD.name -> flagViews[i].setImageResource(R.drawable.ic_us)
                CurrencyEnum.RUB.name -> flagViews[i].setImageResource(R.drawable.ic_russia)
            }
        }
    }
}