package com.artinc.jobber.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.artinc.jobber.R
import com.artinc.jobber.models.Offer

class OfferAdapter(private var offers: List<Offer>) : RecyclerView.Adapter<OfferAdapter.OfferViewHolder>() {
    class OfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemBack: CardView = itemView.findViewById(R.id.offer_back)
        val itemIcon: ImageView = itemView.findViewById(R.id.offer_icon)
        val itemTitle: TextView = itemView.findViewById(R.id.offer_title)
        val itemBtn: TextView = itemView.findViewById(R.id.offer_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_offer, parent, false)
        return OfferViewHolder(view)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val colorMap: Map<String, Int> = mapOf(
            "near_vacancies" to R.color.darkBlue,
            "level_up_resume" to R.color.darkGreen,
            "temporary_job" to R.color.darkGreen
        )

        val iconMap: Map<Int, Int> = mapOf(
            1 to R.drawable.icon_search,
            2 to R.drawable.icon_star,
            3 to R.drawable.icon_vacancy
        )

        val item = offers[position]

        if (item.button == null) {
            holder.itemBtn.visibility = View.GONE
        }

        if (item.id == null) {
            holder.itemBack.visibility = View.GONE
        }

        colorMap[item.id]?.let { colorId ->
            val color = ContextCompat.getColor(holder.itemBack.context, colorId)
            holder.itemBack.setCardBackgroundColor(color)
        }
        iconMap[position + 1]?.let { iconId ->
            holder.itemIcon.setImageResource(iconId)
        }
        holder.itemTitle.text = item.title
    }

    override fun getItemCount(): Int = offers.size

    fun updateItems(newItems: List<Offer>) {
        offers = newItems
        notifyDataSetChanged()
    }
}