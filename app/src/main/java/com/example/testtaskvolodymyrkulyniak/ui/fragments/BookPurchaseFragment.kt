package com.example.testtaskvolodymyrkulyniak.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.activityViewModels
import com.example.testtaskvolodymyrkulyniak.databinding.BookPurchaseFragmentBinding
import com.example.testtaskvolodymyrkulyniak.ui.BooksViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BookPurchaseFragment : Fragment() {

    lateinit var binding: BookPurchaseFragmentBinding
    private val viewModel by activityViewModels<BooksViewModel>()
    lateinit var destinationLink: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BookPurchaseFragmentBinding.inflate(inflater, container, false)

        destinationLink = arguments.let { it?.getString("url")!! }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(destinationLink)
        }
        super.onViewCreated(view, savedInstanceState)
    }
}