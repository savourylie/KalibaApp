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

//        // Style UI
//        val loginDescription = binding.loginDescription
//        loginDescription.text =
//            TypeFaceStyler(this, loginDescription)
//            .styleText( "Kaliba", R.font.nunito_black)
//            .styleText( "Reader", R.font.nunito_extralight_italic)
//            .spannable
//
//        binding.invalidateAll()
    }
}
