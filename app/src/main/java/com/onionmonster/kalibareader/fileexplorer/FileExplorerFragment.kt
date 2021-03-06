package com.onionmonster.kalibareader.fileexplorer

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.onionmonster.kalibareader.HomeActivity

import com.onionmonster.kalibareader.R
import com.onionmonster.kalibareader.databinding.FragmentFileExplorerBinding
import java.io.File


class FileExplorerFragment : Fragment(), OnFolderClickedListener, OnFileSelectedListener {

    private val TAG = javaClass.simpleName

    lateinit var binding: FragmentFileExplorerBinding

    private lateinit var fileList: List<File>
    lateinit var storage: File
    lateinit var currentPath: File
    var selectedPathString = MutableLiveData<String?>()

    private lateinit var fileAdapter: FileAdapter
    private lateinit var permReqLauncher: ActivityResultLauncher<String>
    private var data = ""
    private var itemSelectedPos = -1

    override fun onAttach(@NonNull context: Context) {
        super.onAttach(context)

        val internalStorage = System.getenv("EXTERNAL_STORAGE")
        storage = File(internalStorage)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_file_explorer,
            container,
            false)

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
                    selectedPathString.value = null
                } else {
                    selectedPathString.value = null
                    parentFragmentManager.popBackStack()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        selectedPathString.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "Observer: " + it.toString())

            if (it == null) {
                changeOkButtonColor(false)
            } else {
                changeOkButtonColor(true)
            }
        })

        binding.okButton.setOnClickListener {
            selectedPathString.value?.let {
                val libraryUri = Uri.fromFile(File(it))

                val intent = Intent(context, HomeActivity::class.java).apply {
                    data = libraryUri
                }
                startActivity(intent)
            }
        }

        binding.backButton.setOnClickListener {
            selectedPathString.value = null
            parentFragmentManager.popBackStack()
        }

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
        fileAdapter = FileAdapter(this, this, binding)
        fileAdapter.submitList(fileList)
        recyclerView.adapter = fileAdapter
        binding.pathHolder.text = file.absolutePath
    }

    override fun onFolderClicked(file: File, binding: FragmentFileExplorerBinding) {
        if (file.isDirectory) {
            currentPath = file
            selectedPathString.value = file.absolutePath
            displayFiles(file, binding)
        }
    }

    override fun onFileClicked(file: File,
                               holder: FileViewHolder, selected: Boolean) {

        selectedPathString.value = if (selected) file.absolutePath else null

        Log.d(TAG, selectedPathString.value.toString())
    }

    private fun changeOkButtonColor(highlight: Boolean = true) {

        if (highlight) {
            binding.okButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.highlighted_button_text))
            binding.okButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.highlighted_button_bg))
        } else {
            binding.okButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorSecondary))
            binding.okButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
        }

    }
}






