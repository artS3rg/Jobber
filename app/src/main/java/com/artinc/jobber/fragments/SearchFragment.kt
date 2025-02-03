package com.artinc.jobber.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.artinc.jobber.R
import com.artinc.jobber.adapters.OfferAdapter
import com.artinc.jobber.adapters.VacancyAdapter
import com.artinc.jobber.utils.HorizontalMarginItemDecoration
import com.artinc.jobber.utils.VerticalMarginItemDecoration
import com.artinc.jobber.viewModels.ItemViewModel
import com.google.android.material.button.MaterialButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(R.layout.fragment_search) {
    private val viewModel: ItemViewModel by viewModel()
    private lateinit var offerAdapter: OfferAdapter
    private lateinit var vacancyAdapter: VacancyAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupOfferRecyclerView(view)
        setupVacancyRecyclerView(view)
        observeViewModel()

        view.findViewById<MaterialButton>(R.id.full_btn).setOnClickListener {
            openNextState()
        }

        viewModel.getVacancies()
        viewModel.getOffers()
    }

    private fun openNextState() {
        val nextFragmnet = SearchNextFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, nextFragmnet)
            .addToBackStack(null)
            .commit()
    }

    private fun setupOfferRecyclerView(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.offers_recycler)
        recyclerView.addItemDecoration(
            HorizontalMarginItemDecoration(
                startMargin = (16 * resources.displayMetrics.density).toInt(),
                endMargin = (16 * resources.displayMetrics.density).toInt(),
                defaultMargin = (8 * resources.displayMetrics.density).toInt(),
            )
        )
        offerAdapter = OfferAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = offerAdapter
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
            val firstThreeItems = items.take(3)
            view?.findViewById<MaterialButton>(R.id.full_btn)?.text = "Еще ${items.size} вакансии"
            vacancyAdapter.updateItems(firstThreeItems)
        }

        viewModel.offers.observe(viewLifecycleOwner) { items ->
            offerAdapter.updateItems(items)
        }
    }
}