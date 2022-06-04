package com.assignment.weatherapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.assignment.weatherapp.R
import com.assignment.weatherapp.databinding.FragmentErrorBinding
import com.assignment.weatherapp.presentation.viewmodel.WeatherViewModel


class ErrorFragment : Fragment() {

    private lateinit var binding: FragmentErrorBinding
    private lateinit var viewModel: WeatherViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_error, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel = (activity as MainActivity).viewModel
        binding.weatherviewmodel = viewModel


    }
}