package com.ptn.gedebook.book.presentation.fragment

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.leanback.app.VerticalGridSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.VerticalGridPresenter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ptn.gedebook.book.domain.entity.Book
import com.ptn.gedebook.book.presentation.presenter.CardPresenter
import com.ptn.gedebook.book.presentation.state.BookUiState
import com.ptn.gedebook.book.presentation.viewmodel.BookViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookFragment: VerticalGridSupportFragment() {

    private lateinit var adapter: ArrayObjectAdapter

    private val viewModel by viewModels<BookViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupFragment()
        setupAdapter()
        observeBookFlow()
        viewModel.getBooks()
    }

    private fun setupFragment() {
        val gridPresenter = VerticalGridPresenter().apply {
            numberOfColumns = NUM_OF_COLUMNS
        }
        setGridPresenter(gridPresenter)
        setOnItemViewClickedListener { _, item, _, _ ->
            if (item is Book) {
                Toast.makeText(
                    requireContext(),
                    "${item.title} is selected",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun setupAdapter() {
        adapter = ArrayObjectAdapter(CardPresenter())
        setAdapter(adapter)
    }

    private fun observeBookFlow() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.bookFlow.collectLatest {
                    when (it) {
                        is BookUiState.Success -> {
                            title = "List of Books"
                            adapter.addAll(START_INDEX, it.books)
                        }
                        is BookUiState.Empty -> title = "No books found"
                        is BookUiState.Failed -> title = "Failed to load books"
                        is BookUiState.Loading -> {
                            title = ""
                            adapter.clear()
                            progressBarManager.show()
                        }
                        is BookUiState.LoadComplete -> progressBarManager.hide()
                    }
                }
            }
        }
    }

    companion object {

        const val TAG = "BookFragment"

        private const val NUM_OF_COLUMNS = 5

        private const val START_INDEX = 0
    }
}
