package com.example.ebookreader.fileexplorer

import android.Manifest
import android.content.Context

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.NonNull
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ebookreader.R
import com.example.ebookreader.databinding.FragmentFileExplorerBinding
import java.io.File


class FileExplorerFragment : Fragment(), OnFolderClickedListener, OnFileSelectedListener {

    private val TAG = javaClass.simpleName

    private lateinit var fileList: List<File>
    lateinit var storage: File
    lateinit var currentPath: File
    var selectedPathString: String? = null

    private lateinit var fileAdapter: FileAdapter
    private lateinit var permReqLauncher: ActivityResultLauncher<String>
    private var data = ""

    override fun onAttach(@NonNull context: Context) {
        super.onAttach(context)

        val internalStorage = System.getenv("EXTERNAL_STORAGE")
        storage = File(internalStorage)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentFileExplorerBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_file_explorer, container, false)

        binding.pathHolder.text = storage.absolutePath

        permReqLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){ isGranted ->
            if (isGranted) {
                displayFiles(storage, binding)
            } else {
                parentFragmentManager.popBackStack()
            }
        }
        permReqLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (::currentPath.isInitialized && currentPath.absolutePath != storage.absolutePath) {
                    currentPath = File(currentPath.parentFile.absolutePath)
                    displayFiles(currentPath, binding)
                } else {
                    activity?.onBackPressed()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        return binding.root
    }

    private fun findFiles(file: File): ArrayList<File> {
        val arrayList = ArrayList<File>()
        val files = file.listFiles()

        files?.forEach { singleFile ->
            if (singleFile.isDirectory && !singleFile.isHidden) {
                arrayList.add(singleFile)
            }
        }

        return arrayList
    }

    private fun displayFiles(file: File, binding: FragmentFileExplorerBinding) {
        val recyclerView = binding.recyclerExplorer
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        fileList = ArrayList<File>()
        (fileList as ArrayList<File>).addAll(findFiles(file))
        fileAdapter = FileAdapter(requireContext(), fileList, this, this, binding)
        recyclerView.adapter = fileAdapter
    }

    override fun onFolderClicked(file: File, binding: FragmentFileExplorerBinding) {
        if (file.isDirectory) {
            currentPath = file
            displayFiles(file, binding)
            binding.pathHolder.text = file.absolutePath
        }
    }

    override fun onFileClicked(file: File, layout: LinearLayout, binding: FragmentFileExplorerBinding) {
        if (layout.visibility != View.VISIBLE) {
            layout.visibility = View.VISIBLE
            selectedPathString = file.absolutePath
        } else {
            layout.visibility = View.INVISIBLE
            selectedPathString = null
        }
    }
}






