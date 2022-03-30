package com.github.yeeun_yun97.clone.today_phase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuoteViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
    lateinit var quote: Quote;
    val quoteText= itemView.findViewById<TextView>(R.id.QuoteListItem_quoteTextView)

    fun bind(q:Quote){
        this.quote=q
        quoteText.setText(quote.text)
    }

}

class QuoteAdapter(private val dataList: List<Quote>) : RecyclerView.Adapter<QuoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(viewType,parent,false)
        return QuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    override fun getItemViewType(position: Int): Int =R.layout.item_quote_list
}