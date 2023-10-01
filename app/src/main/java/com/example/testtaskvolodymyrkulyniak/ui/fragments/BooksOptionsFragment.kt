package com.example.testtaskvolodymyrkulyniak.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testtaskvolodymyrkulyniak.databinding.FragmentBooksOptionsBinding
import com.example.testtaskvolodymyrkulyniak.ui.BooksViewModel
import com.example.testtaskvolodymyrkulyniak.ui.adapters.BookOptionsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BooksOptionsFragment : Fragment() {

    lateinit var binding: FragmentBooksOptionsBinding
    private val viewModel by activityViewModels<BooksViewModel>()
    lateinit var imgUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBooksOptionsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val recyclerView = binding.recyclerView
        val adapter = BookOptionsAdapter { navigateAndUpdateQuery(it) }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(view.context, 2)

        super.onViewCreated(view, savedInstanceState)
    }

    fun navigateAndUpdateQuery(query: String) {
        viewModel.fetchData(query)
        findNavController().navigate(BooksOptionsFragmentDirections.actionBooksOptionsFragmentToGiphyListFragment(query))
    }

}