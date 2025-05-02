package com.example.myapplication

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.showToast(message: String) {
  Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

}

fun View.gone(){
    visibility = View.GONE

}

fun View.visible(){
    visibility= View.VISIBLE

}
