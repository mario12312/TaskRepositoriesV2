package com.example.userprofile.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userprofile.R
import com.example.userprofile.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var mainrecycler: RecyclerView
    private var viewManager = LinearLayoutManager(context)

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding: MainFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.main_fragment, container, false
        )
        mainrecycler = binding.GitRepos



        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.run()
        // TODO: Use the ViewModel
        initializeAdapter()
    }

    private fun initializeAdapter(){
        mainrecycler.adapter =
            context?.let { RepositoriesRecyclerAdapter(MainViewModel(),viewModel.newlist, it) }
        mainrecycler.layoutManager = viewManager
        observeData()
    }

    fun observeData(){
        context?.let { RepositoriesRecyclerAdapter(MainViewModel(),viewModel.newlist, it) }
    }
}