package lat.pam.utsproject

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginButton = findViewById<Button>(R.id.btnLogin)
        val registerText = findViewById<TextView>(R.id.tvRegister)
        val usernameEditText = findViewById<EditText>(R.id.etUsername)
        val passwordEditText = findViewById<EditText>(R.id.etPassword)

        // Set click listener for register text
        registerText.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Set click listener for login button
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Retrieve stored data from SharedPreferences
            val sharedPreferences: SharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
            val storedUsername = sharedPreferences.getString("email", null)
            val storedPassword = sharedPreferences.getString("password", null)

            Log.d("MainActivity", "Stored Email: $storedUsername, Password: $storedPassword")

            // Validate login
            if (username == storedUsername && password == storedPassword) {
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()

                // Navigate to the next activity
                val intent = Intent(this, ListFoodActivity::class.java) // Replace with your next activity
                startActivity(intent)
                finish() // Close MainActivity
            } else {
                Toast.makeText(this, "Invalid credentials, please try again.", Toast.LENGTH_SHORT).show()
            }
        }

        // Handle window insets for better UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
