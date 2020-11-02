package com.udacity.shoestore.ui.storelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.LayoutStoreItemBinding
import com.udacity.shoestore.models.Shoe

class StoreListAdapter : RecyclerView.Adapter<StoreListAdapter.StoreItemViewHolder>() {
    class StoreItemViewHolder(val binding: LayoutStoreItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    var items = listOf<Shoe>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var itemClickHandler: (Shoe) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<LayoutStoreItemBinding>(
            inflater,
            R.layout.layout_store_item,
            parent,
            false
        )

        return StoreItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoreItemViewHolder, position: Int) {
        val item = items[position]

        with(holder.binding) {
            shoe = item
            onClickListener = View.OnClickListener { itemClickHandler(item) }

            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = items.size
}