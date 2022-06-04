package com.assignment.weatherapp.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.assignment.weatherapp.R
import com.assignment.weatherapp.databinding.FragmentHomeBinding
import com.assignment.weatherapp.presentation.viewmodel.WeatherViewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        setButtonClickListener()
        setSearchListener()
        observeSearchedLocation()
    }

    private fun setButtonClickListener() {
        binding.btnSubmit.setOnClickListener {
            viewModel.searchLocation(binding.searchView.editText?.text.toString())
        }

    }

    fun viewProgress() {
        binding.btnSubmit.visibility = View.GONE
        binding.progressCircular.visibility = View.VISIBLE

    }

    fun viewButton() {
        binding.btnSubmit.visibility = View.VISIBLE
        binding.progressCircular.visibility = View.GONE

    }

    private fun setSearchListener() {

        binding.searchView.setEndIconOnClickListener {
            binding.searchView.editText!!.text = null

        }


        binding.searchView.editText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0 != null && p0?.length > 0) {
                    binding.btnSubmit.isEnabled = true
                    binding.searchView.endIconDrawable =
                        resources.getDrawable(R.drawable.ic_close_circle, null)
                } else if (p0 == null || p0.isEmpty()) {
                    binding.btnSubmit.isEnabled = false
                    binding.searchView.endIconDrawable = null


                }

            }
        })

    }

    fun observeSearchedLocation() {
        viewModel.searchedLocation.observe(viewLifecycleOwner) { response ->
            when (response) {
                is com.assignment.weatherapp.data.util.Resource.Success -> {


                    viewButton()
                    binding.searchView.editText!!.text = null

                    if (viewModel.getNavigationValue.value == false) {
                        response.data?.let {
                            if (it.errorBody != null) {
                                viewModel.setErrorMessage(it.errorBody!!.error.message)
                                viewModel.setNavigationValue(true)

                                findNavController().navigate(
                                    R.id.action_homeFragment_to_errorFragment,

                                    )

                            } else {
                                viewModel.setNavigationValue(true)

                                findNavController().navigate(
                                    R.id.action_homeFragment_to_successFragment,

                                    )
                            }
                        }
                    }

                }

                is com.assignment.weatherapp.data.util.Resource.Error -> {
                    viewButton()
                    binding.searchView.editText!!.text = null

                    if (viewModel.getNavigationValue.value == false) {

                        if (response.data != null) {
                            viewModel.setErrorMessage(response.data.errorBody!!.error.message)
                            viewModel.setNavigationValue(true)

                            findNavController().navigate(
                                R.id.action_homeFragment_to_errorFragment,

                                )

                        } else {
                            response.message?.let {

                                viewModel.setErrorMessage(it)
                                viewModel.setNavigationValue(true)

                                findNavController().navigate(
                                    R.id.action_homeFragment_to_errorFragment,

                                    )
                            }
                        }
                    }

                }

                is com.assignment.weatherapp.data.util.Resource.Loading -> {
                    if (viewModel.getNavigationValue.value == false)
                        viewProgress()
                }

            }
        }
    }
}