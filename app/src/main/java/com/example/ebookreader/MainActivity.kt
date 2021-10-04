package com.example.ebookreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.ebookreader.utils.TypeFaceStyler

class MainActivity : AppCompatActivity() {
    val TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_main)

        // Style UI
        val loginDescription: TextView = findViewById(R.id.login_description)
        loginDescription.text =
            TypeFaceStyler(this, loginDescription)
            .styleText( "Kaliba", R.font.nunito_black)
            .styleText( "Reader", R.font.nunito_extralight_italic)
            .spannable
    }
}
