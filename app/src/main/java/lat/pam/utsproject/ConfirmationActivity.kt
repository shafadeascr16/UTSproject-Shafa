package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ConfirmationActivity : AppCompatActivity() {

    private lateinit var foodNameTextView: TextView
    private lateinit var servingsTextView: TextView
    private lateinit var nameTextView: TextView
    private lateinit var notesTextView: TextView
    private lateinit var backToMenuButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        // Find the views in the XML layout
        foodNameTextView = findViewById(R.id.tvFoodName)
        servingsTextView = findViewById(R.id.tvServings)
        nameTextView = findViewById(R.id.tvName)
        notesTextView = findViewById(R.id.tvNotes)
        backToMenuButton = findViewById(R.id.backtoMenu)

        // Retrieve data from Intent
        val foodName = intent.getStringExtra("food_name")
        val servings = intent.getStringExtra("servings")
        val name = intent.getStringExtra("name")
        val notes = intent.getStringExtra("notes")

        // Set the retrieved data to the TextViews
        foodNameTextView.text = "Food Name: $foodName"
        servingsTextView.text = "Number of Servings: $servings"
        nameTextView.text = "Ordering Name: $name"
        notesTextView.text = "Additional Notes: $notes"

        // Handle the back to menu button
        backToMenuButton.setOnClickListener {
            val intent = Intent(this, ListFoodActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        // Apply window insets (optional for full-screen support)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

