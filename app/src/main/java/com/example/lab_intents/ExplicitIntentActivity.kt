package com.example.lab_intents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.lab_intents.databinding.ActivityExplicitIntentBinding

class ExplicitIntentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityExplicitIntentBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.saveToMainBtn.setOnClickListener {
            if(binding.editText.text.toString() == "") {
                Toast.makeText(this, "Please enter something", Toast.LENGTH_LONG).show()
            }else {
                val result = binding.editText.text.toString()
                val data = Intent()
                data.putExtra("text", result)
                setResult(RESULT_OK, data)
                finish()
            }
        }

        binding.cancelBtn.setOnClickListener {
            finish()
        }
    }
}