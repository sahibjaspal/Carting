package shizzle.awesome.carting

data class CartItemUIModel(
        val brandName: String,
        val price: Float,
        val imgUrl: String,
        val uniqueId: String,
        val quantity: Int
) {
    override fun equals(other: Any?): Boolean {
        var temp = other as? CartItemUIModel
        return if (temp != null) {
            temp.uniqueId == temp.uniqueId
                    && temp.quantity == temp.quantity &&
                    temp.brandName == brandName &&
                    temp.price == price &&
                    temp.imgUrl == imgUrl
        } else {
            false
        }
    }
}