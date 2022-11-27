package com.example.lab_intents

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.lab_intents.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val ACTION_IMPLICIT = "com.example.lab_intents.IMPLICIT_ACTION"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Povikuva ekspliciten intent
        // Cheka na rezultat
        binding.explicitIntentBtn.setOnClickListener {
            val intent = Intent(this, ExplicitIntentActivity::class.java)
            startActivityForResult(intent, 0);
        }

        // Povikuva custom impliciten intent
        // Ne mi e isto imenuvan proektot, poradi toa e razlicno ime
        binding.implicitIntentBtn.setOnClickListener {
            val intent = Intent(ACTION_IMPLICIT)
            startActivity(intent)
        }

        // Prakja poraka
        binding.implicitSendIntentBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT, "MPiP Send Title")
            intent.putExtra(Intent.EXTRA_TEXT, "Content send from MainActivity")
            startActivity(intent)
        }

        // Otvara picker za sliki
        // Otvorenata slika e vratena kako rezultat
        binding.implicitSelectAndShowImgIntent.setOnClickListener {
            intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
//            startActivityForResult(intent, 1)
            startActivityForResult(Intent.createChooser(intent, "Select Photo: "), 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            if (requestCode == 0) {
                // TextView go mesti vo napishaniot tekst
                val text = data?.getStringExtra("text") ?: "Nothing was entered"
                binding.textView.text = text
            } else if (requestCode == 1) {
                // Ne znam zosto ne go otvara direkt vo galery tuku otvara spisok,
                // probav so imageView i go vrakja tocnoto URI na slikata,
                // galerijata ne saka da ja otvori direkt slikata
                // Mojata pretpostavka e deka ova e do nesto sto se smenilo vo galerijata kako raboti
                // poradi toa sto gledam na stackoverflow 3 saati vakov treba da e kodot
                // Mozebi raboti na postari verzii od android?
//                binding.imageView.setImageURI(data?.data)

                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = data?.data
//                intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI.buildUpon().appendPath(data?.dataString).build()
                intent.type = "image/*"
//                Toast.makeText(this, data?.dataString, Toast.LENGTH_LONG).show()
                startActivity(intent)

            }
        }
    }
}