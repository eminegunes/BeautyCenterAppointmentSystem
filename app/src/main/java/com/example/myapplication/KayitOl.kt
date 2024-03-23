package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityKayitOlBinding
import com.example.myapplication.databinding.ActivityMainBinding

class KayitOl : AppCompatActivity() {
    private lateinit var binding: ActivityKayitOlBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKayitOlBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}