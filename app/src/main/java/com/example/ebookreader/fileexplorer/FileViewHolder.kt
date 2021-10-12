package com.example.ebookreader.fileexplorer

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ebookreader.R
import com.example.ebookreader.databinding.FileContainerBinding
import com.example.ebookreader.databinding.FragmentFileExplorerBinding

class FileViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val container: CardView = itemView.findViewById(R.id.explorer_item)
    val fileName: TextView = itemView.findViewById(R.id.tv_file_name)
    val imgFile: ImageView = itemView.findViewById(R.id.img_file_type)
}