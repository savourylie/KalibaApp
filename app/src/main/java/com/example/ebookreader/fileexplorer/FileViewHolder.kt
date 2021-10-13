package com.example.ebookreader.fileexplorer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.ebookreader.R
import com.example.ebookreader.databinding.FragmentFileExplorerBinding
import java.io.File

class FileViewHolder private constructor (itemView: View,
                     val binding: FragmentFileExplorerBinding,
                     private val folderListener: OnFolderClickedListener,
                     private val itemListener: OnFileSelectedListener): RecyclerView.ViewHolder(itemView) {

    val container: CardView = itemView.findViewById(R.id.explorer_item)
    private val fileName: TextView = itemView.findViewById(R.id.tv_file_name)
    private val imgFile: ImageView = itemView.findViewById(R.id.img_file_type)
    val overLay: LinearLayout = itemView.findViewById(R.id.item_overlay)

    fun bind(file: File, position: Int) {

        fileName.text = file.name

        imgFile.setOnClickListener {
            folderListener.onFolderClicked(file, binding)
        }
    }

    companion object {
        fun from(parent: ViewGroup,
                 binding: FragmentFileExplorerBinding,
                 folderListener: OnFolderClickedListener,
                 itemListener: OnFileSelectedListener): FileViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.file_container, parent, false) as CardView

            return FileViewHolder(view, binding, folderListener, itemListener)
        }
    }
}