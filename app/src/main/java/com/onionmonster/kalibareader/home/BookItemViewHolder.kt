package com.onionmonster.kalibareader.home

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.onionmonster.kalibareader.R
import nl.siegmann.epublib.epub.EpubReader
import java.io.File
import java.io.FileInputStream

class BookItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val container: CardView = itemView.findViewById(R.id.book_item)
//    val fileName: TextView = itemView.findViewById(R.id.tv_file_name)
    val bookCover: ImageView = itemView.findViewById(R.id.book_cover)

    //    @Suppress("BlockingMethodInNonBlockingContext")
    fun getBookCover(bookFile: File): Bitmap {
        val fileInputStream = FileInputStream(bookFile)

        val epubReader = EpubReader()
        val book = epubReader.readEpub(fileInputStream)
        val metaData = book.metadata

        return BitmapFactory.decodeStream(book.coverImage.inputStream)
    }
}