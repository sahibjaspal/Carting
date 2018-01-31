package shizzle.awesome.carting

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import shizzle.awesome.carting.databinding.ItemCartBinding

class CartItemsAdapter(private val items: ArrayList<CartItemUIModel>) : RecyclerView.Adapter<CartItemsAdapter.CartItemViewHolder>() {

    /**
     * Pass in the entire list of new items
     */
    fun updateCartItems(newCartItems: ArrayList<CartItemUIModel>) {
        val diffCallback = CartItemDiffCallback(items, newCartItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items.clear()
        items.addAll(newCartItems)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CartItemViewHolder = CartItemViewHolder(ItemCartBinding.inflate(LayoutInflater.from(parent?.context), parent, true))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CartItemViewHolder?, position: Int) {
        holder?.bind(items[position])
    }

    class CartItemViewHolder(private val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cartItemUIModel: CartItemUIModel) {
            binding.item = cartItemUIModel
            //TODO need an adapter of spannable strings for the spinner
            var listOfQuantities: Array<String> = (1..10).map { amount -> "Qty:$amount" }.toTypedArray()
            binding.itemCartProductQuantity.adapter = ArrayAdapter<String>(binding.itemCartProductQuantity.context, android.R.layout.simple_dropdown_item_1line, listOfQuantities)
        }
    }

    class CartItemDiffCallback(private val oldList: List<CartItemUIModel>, private val newList: List<CartItemUIModel>) : DiffUtil.Callback() {

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldList[oldItemPosition].uniqueId == newList[newItemPosition].uniqueId) &&
                    (oldList[oldItemPosition].quantity == newList[newItemPosition].quantity)
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition] == newList[newItemPosition]
    }
}