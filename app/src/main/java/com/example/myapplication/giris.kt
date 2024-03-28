import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

//Kullanıcı sınıfı
class GirisActivity : AppCompatActivity() {

    private lateinit var loginTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_giris)

        // loginTextView değişkenine layout dosyasındaki TextView'i bağlamak için findViewById kullanılır.
        loginTextView = findViewById(R.id.loginButton)

        loginTextView.setOnClickListener {
            // Buraya tıklanma durumunda yapılacak işlemler eklenir.
            val intent = Intent(this, KayitOlActivity::class.java)
            startActivity(intent)
        }

        // Diğer giriş işlemleri...
    }

    // Diğer giriş işlemleri...
}

data class User(
    val id: Int,
    val username: String,
    val password: String,
    val email: String
)

class AuthenticationSystem {
    private val users = mutableListOf<User>()

    // Kayıt işlevi
    fun register(user: User) {
        users.add(user)
    }

    // Giriş işlevi
    fun login(username: String, password: String): User? {
        return users.find { it.username == username && it.password == password }
    }

    // Kullanıcı adı kullanılıyor mu kontrolü
    fun isUsernameTaken(username: String): Boolean {
        return users.any { it.username == username }
    }

    // E-posta kullanılıyor mu kontrolü
    fun isEmailTaken(email: String): Boolean {
        return users.any { it.email == email }
    }
}
