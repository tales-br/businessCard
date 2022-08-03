package br.tales.businesscard.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.tales.businesscard.data.Card
import br.tales.businesscard.databinding.ItemCardBinding

class CardAdapter : ListAdapter<Card, CardAdapter.ViewHolder>(Diffcallback()){
    var listenerShare: (View) ->  Unit = {}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCardBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val itemCardBinding: ItemCardBinding) :
    RecyclerView.ViewHolder(itemCardBinding.root){
        fun bind(item: Card)
        {
            itemCardBinding.tvItemName.text = item.cardName
            itemCardBinding.tvItemCompany.text = item.cardCompany
            itemCardBinding.tvItemPhone.text = item.cardPhone
            itemCardBinding.tvItemEmail.text = item.cardEmail
            itemCardBinding.cvContent.setBackgroundColor(Color.parseColor(item.cardColor))
            itemCardBinding.cvContent.setOnClickListener{
                listenerShare(it)
            }
        }
    }
}

class Diffcallback: DiffUtil.ItemCallback<Card>()
{
    override fun areItemsTheSame(oldItem: Card, newItem: Card) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Card, newItem: Card) = oldItem.cardPK == newItem.cardPK
}