package com.example.myapplication.ui
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.ui.authentication.component.Secenekler
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint

//Kullanıcı sınıfı
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        auth=Firebase.auth


    }
    fun girisyap(view: View) {
        val email = binding.editTextEmail.text.toString()
        val password = binding.passwordEditText.text.toString()
        if (email.isNotEmpty() && password.isNotEmpty()) {
            Toast.makeText(this, "E mail ve şifrenizi giriniz!", Toast.LENGTH_LONG).show()
        } else {
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                val intent = Intent(this@MainActivity, Secenekler::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }
        fun kayitol(view: View) {
            val email = binding.editTextEmail.text.toString()
            val password = binding.passwordEditText.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                Toast.makeText(this, "E mail ve şifrenizi giriniz!", Toast.LENGTH_LONG).show()
            } else {
                auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                    val intent = Intent(this@MainActivity, Secenekler::class.java)
                    startActivity(intent)
                    finish()
                }.addOnFailureListener {
                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
                }

            }
        }
    }