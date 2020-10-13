package com.alfonsosotelo.examtestingapp.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfonsosotelo.examtestingapp.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_user_list.*
import javax.inject.Inject

class UserListFragment: DaggerFragment()  {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: UserListViewModel by viewModels{viewModelFactory}

    private val userAdapter by lazy { UserAdapter().apply {
        getClickObservable().subscribe {
            findNavController().navigate(UserListFragmentDirections.actionUserListFragmentToUserDetailsFragment(it.entity.id))
        }
    } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val viewManager = LinearLayoutManager(context)


        recyclerView.apply {
            layoutManager = viewManager
            adapter = userAdapter
        }

        viewModel.users.observe(viewLifecycleOwner, Observer {
            userAdapter.submitList(it)
        })

    }
}