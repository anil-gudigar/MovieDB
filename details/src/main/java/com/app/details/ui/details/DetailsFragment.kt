package com.app.details.ui.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.common.ui.hide
import com.app.common.ui.show
import com.app.core.db.Result
import com.app.core.di.Injectable
import com.app.core.di.injectViewModel
import com.app.details.databinding.DetailsFragmentBinding
import javax.inject.Inject

class DetailsFragment : Fragment() , Injectable {

    companion object {
        fun newInstance() = DetailsFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: DetailsViewModel

    private lateinit var movieid: String
    val ARG_MOVIEID = "movieid"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = injectViewModel(viewModelFactory)

        viewModel.id = movieid

        val binding = DetailsFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.detailslayout
        subscribeUi(binding)
        return binding.detailslayout
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getString(ARG_MOVIEID)?.let {
            movieid = it
        }
    }

    private fun subscribeUi(binding: DetailsFragmentBinding) {

        viewModel.movie.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    binding.progressBar.hide()
                    result.data?.let {
                        binding.movie = it
                    }
                }
                Result.Status.LOADING -> binding.progressBar.show()
                Result.Status.ERROR -> {
                    binding.progressBar.hide()
                   //Snackbar.make(binding.detailsLayout, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }


}
