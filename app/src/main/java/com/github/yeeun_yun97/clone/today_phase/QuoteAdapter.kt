package com.github.yeeun_yun97.clone.today_phase

import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuoteAdapter(private val pref: SharedPreferences) :
    RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>() {
    private var dataList: MutableList<Quote> = getDataList()

    class QuoteViewHolder(private val itemView: View, private val removeOperation : (Int)->Unit) : RecyclerView.ViewHolder(itemView) {
        lateinit var quote: Quote;
        val quoteText = itemView.findViewById<TextView>(R.id.QuoteListItem_quoteTextView)
        val quoteAuthorText =
            itemView.findViewById<TextView>(R.id.QuoteListItem_quoteAuthorTextView)
        val quoteRemoveButton =
            itemView.findViewById<ImageView>(R.id.QuoteListItem_quoteRemoveButton)
        val quoteEditButton = itemView.findViewById<ImageView>(R.id.QuoteListItem_quoteEditButton)

        fun bind(position: Int, q: Quote) {
            this.quote = q
            quoteText.setText(quote.text)
            quoteAuthorText.setText(quote.from)
            quoteRemoveButton.setOnClickListener {removeOperation(q.idx)}
            quoteEditButton.setOnClickListener {}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        var removeOperation : (Int) -> Unit = ::removeItem
        return QuoteViewHolder(view,removeOperation)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        holder.bind(position, dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    override fun getItemViewType(position: Int): Int = R.layout.item_quote_list

    public fun update(){
        dataList = getDataList()
        notifyDataSetChanged()
    }

    private fun getDataList(): MutableList<Quote> = Quote.getQuotesFromPreference(pref)

    private fun removeItem(position: Int) {
        Quote.removeQuoteFromPreference(pref, position)
        update()
    }
}