package com.example.ebookreader.fileexplorer

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ebookreader.R
import java.io.File

class FileAdapter(private val context: Context,
                  private val files: List<File>
                  ): RecyclerView.Adapter<FileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        return FileViewHolder(LayoutInflater.from(context).inflate(R.layout.file_container, parent, false))
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        holder.fileName.text = files[position].name
//        holder.fileName.isSelected = true
    }

    override fun getItemCount(): Int {
        return files.size
    }
}