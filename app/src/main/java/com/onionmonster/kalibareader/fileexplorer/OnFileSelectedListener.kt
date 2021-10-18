package com.onionmonster.kalibareader.fileexplorer

import com.onionmonster.kalibareader.databinding.FragmentFileExplorerBinding
import java.io.File

interface OnFileSelectedListener {
    fun onFileClicked(file: File, holder: FileViewHolder, selected: Boolean)
}

interface OnFolderClickedListener {
    fun onFolderClicked(file: File, binding: FragmentFileExplorerBinding)
}