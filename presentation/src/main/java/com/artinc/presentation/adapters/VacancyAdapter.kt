package com.artinc.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.artinc.presentation.R
import com.artinc.presentation.fragments.EmptyFragment
import com.artinc.domain.models.Vacancy
import com.artinc.presentation.utils.getPluralForm
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class VacancyAdapter(
    private var vacancies: List<Vacancy>,
    private val fragment: Fragment
) : RecyclerView.Adapter<VacancyAdapter.VacancyViewHolder>() {
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

        holder.itemLookNum.visibility = View.VISIBLE
        holder.itemSal.visibility = View.VISIBLE

        if (item.lookingNumber == null) {
            holder.itemLookNum.visibility = View.GONE
        }

        if (item.salary.short == null && item.salary.full == "Уровень дохода не указан") {
            holder.itemSal.visibility = View.GONE
        }

        holder.itemLookNum.text = "Сейчас просматривает ${item.lookingNumber} ${getPluralForm(item.lookingNumber ?: 0, listOf("человек", "человека", "человек"))}"
        holder.itemTitle.text = item.title
        holder.itemSal.text = item.salary.short
        holder.itemCity.text = item.address.town
        holder.itemCompany.text = item.company
        holder.itemExp.text = item.experience.previewText
        holder.itemPub.text = "Опубликовано ${formatDate(item.publishedDate)}"
        when(item.isFavorite){
            true -> holder.itemFavBtn.setImageResource(R.drawable.icon_heart_active)
            false -> holder.itemFavBtn.setImageResource(R.drawable.icon_fav)
        }

        holder.itemView.setOnClickListener {
            openFragment(EmptyFragment())
        }

        holder.itemFavBtn.setOnClickListener {
            item.isFavorite = !item.isFavorite
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = vacancies.size

    fun updateItems(newItems: List<Vacancy>) {
        vacancies = newItems
        notifyDataSetChanged()
    }

    private fun openFragment(newFragment: Fragment) {
        fragment.parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, newFragment) // Заменить на id твоего контейнера
            .addToBackStack(null)
            .commit()
    }

    private fun formatDate(inputDate: String): String {
        val parsedDate = LocalDate.parse(inputDate) // Парсим строку в дату
        val formatter = DateTimeFormatter.ofPattern("d MMMM", Locale("ru")) // Форматируем в нужный вид
        return parsedDate.format(formatter)
    }
}