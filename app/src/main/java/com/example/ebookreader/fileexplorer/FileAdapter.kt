package com.example.ebookreader.fileexplorer

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ebookreader.R
import com.example.ebookreader.databinding.FragmentFileExplorerBinding
import java.io.File

class FileAdapter(private val context: Context,
                  private val files: List<File>,
                  private val folderListener: OnFolderClickedListener,
                  private val itemListener: OnFileSelectedListener,
                  private val binding: FragmentFileExplorerBinding
                  ): RecyclerView.Adapter<FileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        return FileViewHolder(LayoutInflater.from(context).inflate(R.layout.file_container, parent, false))
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        holder.fileName.text = files[position].name
//        holder.fileName.isSelected = true

        holder.imgFile.setOnClickListener {
            folderListener.onFolderClicked(files[position], binding)
        }

        holder.container.setOnClickListener {
            itemListener.onFileClicked(files[position], holder.overLay, binding)
        }
    }

    override fun getItemCount(): Int {
        return files.size
    }
}