package com.artinc.presentation.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.artinc.presentation.R
import com.artinc.presentation.adapters.VacancyAdapter
import com.artinc.presentation.utils.VerticalMarginItemDecoration
import com.artinc.presentation.utils.getPluralForm
import com.artinc.presentation.viewModels.ItemViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteFragment : Fragment(R.layout.fragment_favourite) {
    private val viewModel: ItemViewModel by viewModel()
    private lateinit var vacancyAdapter: VacancyAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupVacancyRecyclerView(view)
        observeViewModel()

        viewModel.getVacancies()
    }

    private fun setupVacancyRecyclerView(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.fav_recycler)
        recyclerView.addItemDecoration(
            VerticalMarginItemDecoration(
                startMargin = (0 * resources.displayMetrics.density).toInt(),
                endMargin = (0 * resources.displayMetrics.density).toInt(),
                defaultMargin = (16 * resources.displayMetrics.density).toInt(),
            )
        )
        vacancyAdapter = VacancyAdapter(emptyList(), this)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = vacancyAdapter
    }

    private fun observeViewModel() {
        viewModel.vacancies.observe(viewLifecycleOwner) { items ->
            val visibleItems = items.filter { it.isFavorite }
            view?.findViewById<TextView>(R.id.fav_num)?.text = "${visibleItems.size} ${getPluralForm(visibleItems.size, listOf("вакансия", "вакансии", "вакансий"))}"
            vacancyAdapter.updateItems(visibleItems)
        }
    }
}