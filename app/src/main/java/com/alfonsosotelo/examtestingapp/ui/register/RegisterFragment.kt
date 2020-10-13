package com.alfonsosotelo.examtestingapp.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.alfonsosotelo.examtestingapp.R
import com.alfonsosotelo.examtestingapp.ui.login.LoginFragmentDirections
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_register.*
import javax.inject.Inject

class RegisterFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: RegisterViewModel by viewModels{viewModelFactory}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.errorResponse.observe(viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.successResponse.observe(viewLifecycleOwner, Observer {
            if (it) {
                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToUserListFragment())
            }
        })

        btnRegister.setOnClickListener {
            viewModel.register(
                etUsername.text.toString(),
                etPassword.text.toString(),
                etEmail.text.toString()
            )
        }
    }
}