package com.github.belyakovleonid.fragmentresultsample.sort

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import com.github.belyakovleonid.fragmentresultsample.R
import com.github.belyakovleonid.fragmentresultsample.databinding.FragmentSortBinding
import com.github.belyakovleonid.fragmentresultsample.sort.adapter.SortAdapter
import com.github.belyakovleonid.fragmentresultsample.sort.model.SortModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SortFragment : BottomSheetDialogFragment() {

    override fun getTheme() = R.style.AppBottomSheetDialogTheme

    private val viewModel: SortViewModel by viewModels()
    private lateinit var binding: FragmentSortBinding


    private val sortAdapter by lazy(LazyThreadSafetyMode.NONE) {
        SortAdapter(
            onSortSelected = viewModel::onSortSelected
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSortBinding.inflate(layoutInflater, container, false)
        // Получение текущей сортировки из аргументов
        viewModel.setSelectedSort(arguments?.getSerializable(ARGUMENT_KEY) as SortModel)
        setupView()
        observeLiveData()
        return binding.root
    }

    private fun setupView() = with(binding) {
        list.adapter = sortAdapter
        buttonApply.setOnClickListener {
            // Передача результата, т.е. выбранной сортировки
            setFragmentResult(
                REQUEST_KEY,
                bundleOf(RESULT_EXTRA_KEY to viewModel.currentSort)
            )
            dismiss()
        }
    }

    private fun observeLiveData() {
        viewModel.sorts.observe(viewLifecycleOwner) {
            sortAdapter.submitList(it)
        }
    }

    companion object {
        const val REQUEST_KEY = "sort_key"
        const val RESULT_EXTRA_KEY = "extra_key"
        private const val ARGUMENT_KEY = "args_key"

        fun getInstance(selectedSort: SortModel) = SortFragment().apply {
            arguments = bundleOf(ARGUMENT_KEY to selectedSort)
        }
    }
}