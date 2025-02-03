package com.artinc.jobber.fragments

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.artinc.jobber.R
import com.artinc.jobber.adapters.VacancyAdapter
import com.artinc.jobber.utils.VerticalMarginItemDecoration
import com.artinc.jobber.viewModels.ItemViewModel
import com.google.android.material.button.MaterialButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchNextFragment : Fragment(R.layout.fragment_next_state) {
    private val viewModel: ItemViewModel by viewModel()
    private lateinit var vacancyAdapter: VacancyAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupVacancyRecyclerView(view)
        observeViewModel()

        setupBackButton(view)

        viewModel.getVacancies()
    }

    private fun setupVacancyRecyclerView(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.vacancy_recycler)
        recyclerView.addItemDecoration(
            VerticalMarginItemDecoration(
                startMargin = (0 * resources.displayMetrics.density).toInt(),
                endMargin = (0 * resources.displayMetrics.density).toInt(),
                defaultMargin = (16 * resources.displayMetrics.density).toInt(),
            )
        )
        vacancyAdapter = VacancyAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = vacancyAdapter
    }

    private fun observeViewModel() {
        viewModel.vacancies.observe(viewLifecycleOwner) { items ->
            view?.findViewById<TextView>(R.id.vac_nums)?.text = "${items.size} вакансий"
            vacancyAdapter.updateItems(items)
        }
    }

    private fun setupBackButton(view: View) {
        val backButton: ImageButton = view.findViewById(R.id.back_btn)
        backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}