package com.sonyei888.onlineshopshoes.activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.sonyei888.onlineshopshoes.Adapter.CartAdapter
import com.sonyei888.onlineshopshoes.Helper.ChangeNumberItemsListener
import com.sonyei888.onlineshopshoes.Helper.ManagementCart
import com.sonyei888.onlineshopshoes.databinding.ActivityCartBinding

class CartActivity : BaseActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var managentCart: ManagementCart
    private var tax: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managentCart = ManagementCart(this)


        setVariable()
        initCartList()
        calculateCart()
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener { finish() }
    }

    private fun initCartList() {
        binding.viewCart.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        binding.viewCart.adapter =
            CartAdapter(managentCart.getListCart(), this, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    calculateCart();
                }
            });

        with(binding) {
            emptyTxt.visibility =
                if (managentCart.getListCart().isEmpty()) View.VISIBLE else View.GONE;
            scrollView2.visibility =
                if (managentCart.getListCart().isEmpty()) View.GONE else View.VISIBLE;
        }
    }

    private fun calculateCart() {
        val percenTax = 0.02
        val delivery = 10.0
        tax = Math.round((managentCart.getTotalFee() * percenTax) * 100) / 100.0
        val total = Math.round((managentCart.getTotalFee() + tax + delivery) * 100) / 100
        val itemTotal = Math.round(managentCart.getTotalFee() * 100) / 100

        with(binding) {
            totalFeeTxt.text = "$$itemTotal"
            taxTxt.text = "$$tax"
            deliveryTxt.text = "$$delivery"
            totalTxt.text = "$$total"
        }
    }

}