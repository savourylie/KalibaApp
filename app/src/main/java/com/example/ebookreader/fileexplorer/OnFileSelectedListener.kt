package com.example.ebookreader.fileexplorer

import com.example.ebookreader.databinding.FragmentFileExplorerBinding
import java.io.File

interface OnFileSelectedListener {
    fun onFileClicked(file: File, binding: FragmentFileExplorerBinding)
}

interface OnFolderClickedListener {
    fun onFolderClicked(file: File, binding: FragmentFileExplorerBinding)
}