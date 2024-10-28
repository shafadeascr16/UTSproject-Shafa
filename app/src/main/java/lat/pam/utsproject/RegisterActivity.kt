package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val nameEditText = findViewById<EditText>(R.id.edit_text_name)
        val emailEditText = findViewById<EditText>(R.id.edit_text_email)
        val passwordEditText = findViewById<EditText>(R.id.edit_text_password)
        val registerButton = findViewById<Button>(R.id.button_register)

        registerButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Save the data in SharedPreferences
            val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("name", name)
            editor.putString("email", email)
            editor.putString("password", password)
            editor.apply()  // Save changes

            Log.d("RegisterActivity", "Registered Email: $email, Password: $password")

            // Show a toast message and return to MainActivity
            Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()  // Close RegisterActivity
        }
    }
}
