package com.example.ebookreader.fileexplorer

import android.Manifest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ebookreader.R
import com.example.ebookreader.databinding.FragmentFileExplorerBinding
import java.io.File


class FileExplorerFragment : Fragment() {

    private lateinit var fileList: List<File>
    lateinit var storage: File
    private lateinit var fileAdapter: FileAdapter
    private lateinit var permReqLauncher: ActivityResultLauncher<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentFileExplorerBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_file_explorer, container, false)

        val internalStorage = System.getenv("EXTERNAL_STORAGE")
        storage = File(internalStorage)
        binding.pathHolder.text = storage.absolutePath

        permReqLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){ isGranted ->
            if (isGranted) {
                displayFiles(binding)
            } else {
                parentFragmentManager.popBackStack()
            }
        }
        permReqLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)

        return binding.root
    }

    fun findFiles(file: File): ArrayList<File> {
        val arrayList = ArrayList<File>()
        val files = file.listFiles()

        files.forEach { singleFile ->
            if (singleFile.isDirectory && !singleFile.isHidden) {
                arrayList.add(singleFile)
            }
        }

        return arrayList
    }

    fun displayFiles(binding: FragmentFileExplorerBinding) {
        val recyclerView = binding.recyclerExplorer
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        fileList = ArrayList<File>()
        (fileList as ArrayList<File>).addAll(findFiles(storage))
        fileAdapter = FileAdapter(requireContext(), fileList)
        recyclerView.adapter = fileAdapter
    }
}






