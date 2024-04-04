package com.example.myapplication
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

//Kullanıcı sınıfı
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var loginTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        var giris=binding.root
        setContentView(giris)

    binding.buttonkayitol.setOnClickListener {
        val kayitolsayfasi = Intent (this, KayitOlActivity::class.java)
        startActivity(kayitolsayfasi)
    }
    }
}
