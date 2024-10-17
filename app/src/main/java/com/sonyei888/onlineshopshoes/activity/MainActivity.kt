package com.sonyei888.onlineshopshoes.activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.sonyei888.onlineshopshoes.R
import com.sonyei888.onlineshopshoes.SliderAdapter
import com.sonyei888.onlineshopshoes.ViewModel.MainViewModel
import com.sonyei888.onlineshopshoes.databinding.ActivityMainBinding
import com.sonyei888.onlineshopshoes.model.SliderModel

class MainActivity : AppCompatActivity() {

    private val viewModel=MainViewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanner()
    }

    private fun initBanner() {
        binding.progressBarBanner.visibility= View.VISIBLE
        viewModel.banners.observe(this, Observer { item ->

        })
    }
    private fun banners(image:List<SliderModel>){
        binding.viewpagerSlider.adapter=SliderAdapter(image, binding.viewpagerSlider)
    }
}