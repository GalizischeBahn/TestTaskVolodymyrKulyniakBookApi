package com.example.testtaskvolodymyrkulyniak.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testtaskvolodymyrkulyniak.databinding.FragmentBookListBinding
import com.example.testtaskvolodymyrkulyniak.ui.BooksViewModel
import com.example.testtaskvolodymyrkulyniak.ui.adapters.BookAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookListFragment : Fragment() {

    lateinit var binding: FragmentBookListBinding
    private val viewModel by activityViewModels<BooksViewModel>()
    lateinit var query: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentBookListBinding.inflate(inflater, container, false)
        query = arguments.let{ it?.getString("query")!! }


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       // val items = viewModel.items
        val booksAdapter = BookAdapter { it ->
            findNavController().navigate(BookListFragmentDirections.actionBookListFragmentToBookPurchaseFragment(it))}
            super.onViewCreated(view, savedInstanceState)
        binding.bindAdapter(booksAdapter)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.fetchData(query).collect{
                    booksAdapter.submitData(it)
                }
            }
            }
        }

    }

private fun FragmentBookListBinding.bindAdapter(bookAdapter: BookAdapter) {
    list.adapter = bookAdapter
    list.layoutManager = LinearLayoutManager(list.context, LinearLayoutManager.VERTICAL, false)
    val decoration = DividerItemDecoration(list.context, DividerItemDecoration.VERTICAL)
    list.addItemDecoration(decoration)
}