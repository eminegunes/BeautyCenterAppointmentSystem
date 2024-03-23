package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.example.myapplication.databinding.ActivityGirisBinding

private  lateinit var binding:ActivityGirisBinding
class giris : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGirisBinding.inflate(layoutInflater)
        val view = binding.root.also {
            setContentView(it)
        }

    }

    fun next(view: View){

    }

}