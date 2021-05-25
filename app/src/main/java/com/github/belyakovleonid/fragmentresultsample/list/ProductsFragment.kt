package com.github.belyakovleonid.fragmentresultsample.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.github.belyakovleonid.fragmentresultsample.databinding.FragmentListBinding
import com.github.belyakovleonid.fragmentresultsample.list.adapter.SampleListAdapter
import com.github.belyakovleonid.fragmentresultsample.sort.SortFragment
import com.github.belyakovleonid.fragmentresultsample.sort.model.SortModel

class ProductsFragment : Fragment() {

    private val viewModel: ListViewModel by viewModels()
    private lateinit var binding: FragmentListBinding

    private val listAdapter = SampleListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(SortFragment.REQUEST_KEY) { key, bundle ->
            // Применение новой сортировки, полученной от SortFragment
            viewModel.setSelectedSort(
                bundle.getParcelable(SortFragment.RESULT_EXTRA_KEY) ?: SortModel.DEFAULT
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(layoutInflater, container, false)
        setupView()
        observeLiveData()
        return binding.root
    }

    private fun setupView() = with(binding) {
        list.adapter = listAdapter
        sort.setOnClickListener {
            // Показ SortFragment и передача ему текущей сортировки
            val sortDialog = SortFragment.getInstance(viewModel.getSelectedSort())
            sortDialog.show(parentFragmentManager, null)
        }
    }

    private fun observeLiveData() {
        viewModel.items.observe(viewLifecycleOwner) {
            listAdapter.submitList(it)
        }
        viewModel.hasSort.observe(viewLifecycleOwner) {
            binding.vSortAppliedIcon.isVisible = it
        }
    }
}