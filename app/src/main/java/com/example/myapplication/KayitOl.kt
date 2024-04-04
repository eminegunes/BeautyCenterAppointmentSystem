package com.example.myapplication
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityKayitOlBinding
import com.example.myapplication.databinding.ActivityMainBinding

class KayitOlActivity : AppCompatActivity() {
    lateinit var binding: ActivityKayitOlBinding
    private lateinit var loginTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityKayitOlBinding.inflate(layoutInflater)
        var kayitOl=binding.root
        setContentView(kayitOl)

        // Giriş sayfasına geri dönme
        val registerButton = findViewById<Button>(R.id.registerButton)
        loginTextView.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}