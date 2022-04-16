package com.github.yeeun_yun97.clone.today_phase.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.yeeun_yun97.clone.today_phase.R
import com.github.yeeun_yun97.clone.today_phase.databinding.ItemQuoteListBinding
import com.github.yeeun_yun97.clone.today_phase.data.model.Quote

class QuoteAdapter(private val editOperation: (Int) -> Unit) :
    RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>() {
    //model
    private var dataList: MutableList<Quote> = Quote.readAllQuotes()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        var binding: ItemQuoteListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_quote_list,
            parent,
            false
        )
        return QuoteViewHolder(binding, ::removeItem, editOperation)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    fun refreshDataSet() {
        dataList = Quote.readAllQuotes()
        notifyDataSetChanged()
    }

    private fun removeItem(idx: Int) {
        Quote.deleteQuoteByIndex(idx)
        refreshDataSet()
    }

    /* inner class ViewHolder */
    class QuoteViewHolder(
        private val binding: ItemQuoteListBinding,
        private val removeOperation: (Int) -> Unit,
        private val editOperation: (Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(quote: Quote) {
            this.binding.quote = quote
            this.binding.onClick = View.OnClickListener {
                when (it.id) {
                    R.id.QuoteListItem_quoteEditButton -> editOperation(quote.idx)
                    R.id.QuoteListItem_quoteRemoveButton -> removeOperation(quote.idx)
                }
            }
        }
    }

}