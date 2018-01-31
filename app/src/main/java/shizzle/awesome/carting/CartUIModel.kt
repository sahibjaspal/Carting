package shizzle.awesome.carting

import android.databinding.BaseObservable
import android.databinding.Bindable

class CartUIModel : BaseObservable() {
    val items: ArrayList<CartItemUIModel> = ArrayList()

    fun setCartItems(newList: List<CartItemUIModel>) {
        //TODO don't blindly clear and all, intelligently remove/replace/add
        items.clear()
        items.addAll(newList)
    }

    @Bindable
    fun getCanCheckout(): Boolean {
        return items.size > 0
    }

    @Bindable
    fun getCartSubtotal(): Float {
        return 99.99f
    }

    @Bindable
    fun getItemCount(): Int {
        return 2
    }

}