package shizzle.awesome.carting


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import shizzle.awesome.carting.databinding.FragmentCartBinding

/**
 * A simple [Fragment] subclass.
 *
 */
class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding

    private val viewModel: CartViewModel by lazy {
        ViewModelProviders.of(this).get(CartViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        binding.model = viewModel.uiModel
        binding.cartItems.adapter = CartItemsAdapter(viewModel.uiModel.items)

        viewModel.fetchCart.observe(this, Observer {
            if (it != null)
                (binding.cartItems.adapter as CartItemsAdapter).updateCartItems(it)
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchLatestCart()
    }

}
