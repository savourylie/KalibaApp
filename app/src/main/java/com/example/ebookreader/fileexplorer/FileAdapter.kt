package com.example.ebookreader.fileexplorer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ebookreader.R
import com.example.ebookreader.databinding.FragmentFileExplorerBinding
import java.io.File

class FileAdapter(
                  private val folderListener: OnFolderClickedListener,
                  private val itemListener: OnFileSelectedListener,
                  private val binding: FragmentFileExplorerBinding
                  ): ListAdapter<File, FileViewHolder>(FileDiffCallback()) {

    private var selectedPosition = -1

//    override fun getItemCount() = files.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        return FileViewHolder.from(parent, binding, folderListener, itemListener)
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        val file = getItem(position)

        if (selectedPosition == position) {
            holder.overLay.apply {
                visibility = View.VISIBLE
                isSelected = true
            }
        } else {
            holder.overLay.apply {
                visibility = View.INVISIBLE
                isSelected = false
            }
        }

        holder.container.setOnClickListener {
            if (selectedPosition == position) {
                selectedPosition = -1
                notifyDataSetChanged()
                itemListener.onFileClicked(file, holder, false)

            } else {
                selectedPosition = position
                notifyDataSetChanged()
                itemListener.onFileClicked(file, holder, true)
            }
        }

        holder.bind(file, position)
    }
}

class FileDiffCallback: DiffUtil.ItemCallback<File>() {
    override fun areItemsTheSame(oldItem: File, newItem: File): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: File, newItem: File): Boolean {
        return oldItem == newItem
    }

}