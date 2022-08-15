package com.medvedomg.marvelapp.presentation.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import coil.request.ImageRequest
import com.medvedomg.marvelapp.R
import com.medvedomg.marvelapp.databinding.FragmentDetailsBinding
import com.medvedomg.yelpapiapp.presentation.util.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {


    private val viewModel by viewModel<DetailsViewModel>()

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewStateLiveData.observe(viewLifecycleOwner, ::setState)
    }

    private fun setState(state: ViewState<DetailsModel>) {
        with(binding) {
            when (state) {
                is ViewState.Success -> {
                    loader.visibility = View.GONE
                    tvError.visibility = View.GONE
                    iv.visibility = View.VISIBLE
                    iv.load(state.data.imageUrl)
                    tvTitle.visibility = View.VISIBLE
                    tvTitle.text = state.data.title
                    tvDescription.visibility = View.VISIBLE
                    tvDescription.text = state.data.description
                }
                is ViewState.Error -> {
                    loader.visibility = View.GONE
                    iv.visibility = View.GONE
                    tvTitle.visibility = View.GONE
                    tvDescription.visibility = View.GONE
                    tvError.visibility = View.VISIBLE
                }
                is ViewState.Loading -> {
                    binding.loader.visibility = View.VISIBLE
                    binding.tvError.visibility = View.GONE
                    iv.visibility = View.GONE
                    tvTitle.visibility = View.GONE
                    tvDescription.visibility = View.GONE
                }
            }
        }
    }
}