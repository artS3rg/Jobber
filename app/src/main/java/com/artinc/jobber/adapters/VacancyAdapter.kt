package com.artinc.jobber.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.artinc.jobber.R
import com.artinc.jobber.models.Vacancy
import com.google.android.material.button.MaterialButton

class VacancyAdapter(private var vacancies: List<Vacancy>) : RecyclerView.Adapter<VacancyAdapter.VacancyViewHolder>() {
    class VacancyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemLookNum: TextView = itemView.findViewById(R.id.vac_look_num)
        val itemTitle: TextView = itemView.findViewById(R.id.vac_title)
        val itemSal: TextView = itemView.findViewById(R.id.vac_sal)
        val itemCity: TextView = itemView.findViewById(R.id.vac_city)
        val itemCompany: TextView = itemView.findViewById(R.id.vac_company)
        val itemExp: TextView = itemView.findViewById(R.id.vac_exp)
        val itemPub: TextView = itemView.findViewById(R.id.vac_publish)
        val itemFavBtn: ImageButton = itemView.findViewById(R.id.vac_fav_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vacancy, parent, false)
        return VacancyViewHolder(view)
    }

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        val item = vacancies[position]

        if (item.lookingNumber == null) {
            holder.itemLookNum.visibility = View.GONE
        }

        if (item.salary.short == null && item.salary.full == "Уровень дохода не указан") {
            holder.itemSal.visibility = View.GONE
        }

        holder.itemLookNum.text = "Сейчас просматривает ${item.lookingNumber} человек"
        holder.itemTitle.text = item.title
        holder.itemSal.text = item.salary.short
        holder.itemCity.text = item.address.town
        holder.itemCompany.text = item.company
        holder.itemExp.text = item.experience.previewText
        holder.itemPub.text = item.publishedDate
        when(item.isFavorite){
            true -> holder.itemFavBtn.setImageResource(R.drawable.icon_heart_active)
            false -> holder.itemFavBtn.setImageResource(R.drawable.icon_fav)
        }
    }

    override fun getItemCount(): Int = vacancies.size

    fun updateItems(newItems: List<Vacancy>) {
        vacancies = newItems
        notifyDataSetChanged()
    }
}