package shizzle.awesome.carting

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class CartViewModel : ViewModel() {

    val uiModel: CartUIModel = CartUIModel()

    //TODO feel free to use a different model here facing the network
    var fetchCart: LiveData<ArrayList<CartItemUIModel>> = MutableLiveData<ArrayList<CartItemUIModel>>()

    val getItems: MutableLiveData<ArrayList<CartItemUIModel>> = MutableLiveData<ArrayList<CartItemUIModel>>()

    fun fetchLatestCart() {
        //TODO fetch from network
    }

    fun getItemsForInitializingAdapter() {
        getItems.value = uiModel.items
    }

}