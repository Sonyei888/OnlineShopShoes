package com.sonyei888.onlineshopshoes.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.sonyei888.onlineshopshoes.Helper.ChangeNumberItemsListener
import com.sonyei888.onlineshopshoes.Helper.ManagmentCart
import com.sonyei888.onlineshopshoes.databinding.ViewholderCartBinding
import com.sonyei888.onlineshopshoes.model.ItemsModel

class CartAdapter(
    private val listItemSelected: ArrayList<ItemsModel>,
    context: Context,
    var chargeNumberItemsListener: ChangeNumberItemsListener? = null
) : RecyclerView.Adapter<CartAdapter.Viewholder>() {
    class Viewholder(val binding: ViewholderCartBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    private val managementCart = ManagmentCart(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding =
            ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val item = listItemSelected[position]

        holder.binding.titleTxt.text = item.title
        holder.binding.feeEachItem.text = "$${item.price}"
        holder.binding.totalEachItem.text = "$${Math.round(item.numberInCart * item.price)}"
        holder.binding.numberItemTxt.text = item.numberInCart.toString()

        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .apply(RequestOptions().transform(CenterCrop()))
            .into(holder.binding.pic)


        holder.binding.plusCartBtn.setOnClickListener {
            managementCart.plusItem(listItemSelected,
                position, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    chargeNumberItemsListener?.onChanged()
                }
            })
        }
        holder.binding.minusCartBtn.setOnClickListener {
            managementCart.minusItem(
                listItemSelected,
                position,
                object : ChangeNumberItemsListener {
                    override fun onChanged() {
                        notifyDataSetChanged()
                        chargeNumberItemsListener?.onChanged()
                    }
                })
        }
    }

    override fun getItemCount(): Int = listItemSelected.size
}