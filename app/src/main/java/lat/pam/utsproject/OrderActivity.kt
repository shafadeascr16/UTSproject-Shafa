package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OrderActivity : AppCompatActivity() {

    private lateinit var foodNameTextView: TextView
    private lateinit var servingsEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var notesEditText: EditText
    private lateinit var orderButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        // Find the views in the XML layout
        foodNameTextView = findViewById(R.id.etFoodName)
        servingsEditText = findViewById(R.id.etServings)
        nameEditText = findViewById(R.id.etName)
        notesEditText = findViewById(R.id.etNotes)
        orderButton = findViewById(R.id.btnOrder)

        // Retrieve the data passed from ListFoodActivity
        val foodName = intent.getStringExtra("food_name")
        foodNameTextView.text = foodName

        // Handle the order button click
        orderButton.setOnClickListener {
            // Get user input values
            val servings = servingsEditText.text.toString()
            val name = nameEditText.text.toString()
            val notes = notesEditText.text.toString()

            // Validate inputs
            if (servings.isEmpty() || name.isEmpty()) {
                Toast.makeText(this, "Please enter the required fields", Toast.LENGTH_SHORT).show()
            } else {
                // Pass the data to ConfirmationActivity
                val intent = Intent(this, ConfirmationActivity::class.java)
                intent.putExtra("food_name", foodName)
                intent.putExtra("servings", servings)
                intent.putExtra("name", name)
                intent.putExtra("notes", notes)
                startActivity(intent)
            }
        }

        // Apply window insets (this is optional if you need full-screen support)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
