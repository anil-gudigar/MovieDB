package com.app.discover.ui.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.app.common.ui.GridItemDecoration
import com.app.common.ui.hide
import com.app.common.ui.show
import com.app.core.db.Result
import com.app.core.di.Injectable
import com.app.core.di.injectViewModel
import com.app.discover.databinding.DiscoverFragmentBinding
import javax.inject.Inject

class DiscoverFragment : Fragment(), Injectable {

    companion object {
        fun newInstance() = DiscoverFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: DiscoverViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = injectViewModel(viewModelFactory)
        val binding = DiscoverFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.discoverLayout

        val adapter = DiscoverMovieAdapter()
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)

        binding.recyclerView.addItemDecoration(GridItemDecoration(0, 2))
        binding.recyclerView.adapter = adapter

        subscribeUi(binding, adapter)

        setHasOptionsMenu(true)
        return binding.discoverLayout
    }

    private fun subscribeUi(binding: DiscoverFragmentBinding, adapter: DiscoverMovieAdapter) {
        viewModel.moviesList.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    binding.progressBar.hide()
                    result.data?.let {
                        adapter.submitList(it)
                    }
                }
                Result.Status.LOADING -> binding.progressBar.show()
                Result.Status.ERROR -> {
                    binding.progressBar.hide()
                    //Snackbar.make(binding.discoverLayout, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }

}
