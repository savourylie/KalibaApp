package com.onionmonster.kalibareader.home

import android.graphics.BitmapFactory
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onionmonster.kalibareader.R
import com.onionmonster.kalibareader.databinding.FragmentHomeBinding
import com.onionmonster.kalibareader.fileexplorer.FileAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import nl.siegmann.epublib.epub.EpubReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.util.*


class HomeFragment : Fragment() {

    val TAG = "Dev/" + javaClass.simpleName

    lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this, HomeViewModelFactory(activity.application)).get(HomeViewModel::class.java)
    }

    private val bookAdapter = BookAdapter()

    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_home,
            container,
            false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.homeRecyclerview.adapter = bookAdapter

        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.book_item_divider)!!)

        with (binding.homeRecyclerview) {
            setHasFixedSize(true)
            addItemDecoration(dividerItemDecoration)

            layoutManager = GridLayoutManager(context, 3)
            adapter = bookAdapter
        }

        val libraryUriStr = requireArguments().getString("libraryUriStr")
        viewModel.showBookFilesToFragment(libraryUriStr!!)
        displayBookFiles()

        return binding.root
    }

    private fun displayBookFiles() {
        viewModel.bookFileList.observe(viewLifecycleOwner, {
            bookAdapter.submitList(it)
        })
    }
}