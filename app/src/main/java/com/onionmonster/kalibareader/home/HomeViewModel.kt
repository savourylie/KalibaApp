package com.onionmonster.kalibareader.home

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nl.siegmann.epublib.epub.EpubReader
import java.io.File
import java.io.FileInputStream
import java.util.*

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    val TAG = javaClass.simpleName

    val bookFileList =  MutableLiveData<List<File>>()

    fun showBookFilesToFragment(libraryUriStr: String) {
        viewModelScope.launch {
            val libraryUri = Uri.parse(libraryUriStr)
            val libraryFile = File(libraryUri.path!!)

            bookFileList.value = findBookFiles(libraryFile)
        }
    }

    private suspend fun findBookFiles(file: File): ArrayList<File> = withContext (Dispatchers.IO) {
        Log.d(TAG, Throwable().stackTrace[0].methodName)

        val arrayList: ArrayList<File> = ArrayList()

        val files: Array<File>? = file.listFiles()

        if (files != null) {
            for (singleFile in files) {
                if (singleFile.isDirectory && !singleFile.isHidden) {
                    arrayList.add(singleFile)
                }
            }

            for (singleFile in files) {
                if (singleFile.name.lowercase(Locale.getDefault()).endsWith(".epub")) {
                    arrayList.add(singleFile)
                }
            }
        }

        arrayList
    }


}