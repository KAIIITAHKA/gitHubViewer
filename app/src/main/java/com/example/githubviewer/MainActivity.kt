package com.example.githubviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubviewer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportFragmentManager.beginTransaction().replace(R.id.top_placeHolder, AuthFragment()).commit()
    }
}