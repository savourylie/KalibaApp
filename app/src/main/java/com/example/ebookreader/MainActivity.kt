package com.example.ebookreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.ebookreader.databinding.ActivityMainBinding
import com.example.ebookreader.utils.TypeFaceStyler

class MainActivity : AppCompatActivity() {
    val TAG = javaClass.simpleName
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
