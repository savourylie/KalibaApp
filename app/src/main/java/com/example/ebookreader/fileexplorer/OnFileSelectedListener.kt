package com.example.ebookreader.fileexplorer

import android.widget.LinearLayout
import com.example.ebookreader.databinding.FragmentFileExplorerBinding
import java.io.File

interface OnFileSelectedListener {
    fun onFileClicked(file: File, layout: LinearLayout, binding: FragmentFileExplorerBinding)
}

interface OnFolderClickedListener {
    fun onFolderClicked(file: File, binding: FragmentFileExplorerBinding)
}