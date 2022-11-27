package com.example.lab_intents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.lab_intents.databinding.ActivityImplicitBinding

class ImplicitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityImplicitBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        val result = packageManager.queryIntentActivities(intent, 0)

        var textResult = ""

        result.map{ it -> it.activityInfo.name }.forEach { textResult += it + "\n" }
        binding.textView3.text = textResult
    }
}