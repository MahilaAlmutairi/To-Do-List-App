package com.mahila.todolistapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mahila.todolistapp.adapter.TaskRecycleViewAdapter
import com.mahila.todolistapp.databinding.FragmentMainBinding
import com.mahila.todolistapp.viewModel.TaskViewModel

class MainFragment : Fragment(){

    private val taskViewModel: TaskViewModel by viewModels()

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val adapter: TaskRecycleViewAdapter by lazy { TaskRecycleViewAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Data binding
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        // Setup RecyclerView
        setupRecyclerview()

        // Observe LiveData
        taskViewModel.getAll().observe(viewLifecycleOwner, { data ->
            adapter.setData(data)
            binding.rvRecycleView.scheduleLayoutAnimation()
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun setupRecyclerview() {
        val recyclerView = binding.rvRecycleView
        recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}