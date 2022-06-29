package uz.anorbank.currencyexchange.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.anorbank.currencyexchange.R
import uz.anorbank.currencyexchange.data.local.CurrencyEnum
import uz.anorbank.currencyexchange.data.remote.dataclasses.AnorBankDataItem
import uz.anorbank.currencyexchange.databinding.ItemAnorBankBinding

class DataAdapter : ListAdapter<AnorBankDataItem, DataAdapter.DataViewHolder>(DiffItem) {

    object DiffItem : DiffUtil.ItemCallback<AnorBankDataItem>() {
        override fun areItemsTheSame(oldItem: AnorBankDataItem, newItem: AnorBankDataItem): Boolean = true
        override fun areContentsTheSame(oldItem: AnorBankDataItem, newItem: AnorBankDataItem): Boolean = true
    }

    inner class DataViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding by viewBinding(ItemAnorBankBinding::bind)

        fun bind() = with(binding) {
            val item = getItem(absoluteAdapterPosition)
            tvBaseName.text = item.baseName
            tvRateBuy.text = item.rateBuy.toString()
            tvRateSell.text = item.rateSell.toString()
            tvRateCb.text = item.rateCB.toString()

            when (item.baseName) {
                CurrencyEnum.EUR.name -> imgFlag.setImageResource(R.drawable.ic_euro)
                CurrencyEnum.USD.name -> imgFlag.setImageResource(R.drawable.ic_us)
                CurrencyEnum.RUB.name -> imgFlag.setImageResource(R.drawable.ic_russia)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_anor_bank, parent, false))

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind()
    }

}