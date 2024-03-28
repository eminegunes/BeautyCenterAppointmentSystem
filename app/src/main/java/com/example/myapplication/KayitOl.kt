import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class KayitOlActivity : AppCompatActivity() {
    private lateinit var loginTextView: TextView
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kayit_ol)

        // Giriş sayfasına geri dönme
         val registerButton = findViewById<Button>(R.id.registerButton)
         loginTextView.setOnClickListener {
            val intent = Intent(this, GirisActivity::class.java)
            startActivity(intent)
        }

        // Diğer kayıt işlemleri...
    }

    // Diğer kayıt işlemleri...
}
