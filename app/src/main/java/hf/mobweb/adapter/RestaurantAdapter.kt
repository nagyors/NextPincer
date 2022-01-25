package hf.mobweb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import hf.mobweb.R
import hf.mobweb.data.Restaurant
import hf.mobweb.databinding.ItemRestaurantBinding

class RestaurantAdapter(private val listener: RestaurantClickListener) :
    RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    private val items = mutableListOf<Restaurant>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RestaurantViewHolder(
        ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurantItem = items[position]

        holder.binding.ivIcon.setImageResource(getImageResource(restaurantItem.cuisine))
        holder.binding.tvName.text = restaurantItem.name
        holder.binding.tvCuisine.text = restaurantItem.cuisine.toString()
        holder.binding.ratingbar.rating = restaurantItem.rating
        holder.binding.tvPricing.text = restaurantItem.pricing

        holder.itemView.setOnClickListener {
            listener.onItemClicked(restaurantItem)
        }
    }

    override fun getItemCount(): Int = items.size

    @DrawableRes()
    private fun getImageResource(cuisine: String): Int {
        return when (cuisine) {
            "Fast Food" -> R.drawable.fastfood
            "Breakfast" -> R.drawable.breakfast
            "Local" -> R.drawable.soup
            "Asian" -> R.drawable.ramen
            "Pizza" -> R.drawable.pizza
            else -> R.drawable.soup
        }
    }

    fun addItem(item: Restaurant) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun update(restaurants: List<Restaurant>) {
        items.clear()
        items.addAll(restaurants)
        notifyDataSetChanged()
    }

    interface RestaurantClickListener {
        fun onItemChanged(item: Restaurant)
        fun onItemDelete(item: Restaurant)
        fun onItemClicked(item: Restaurant)
    }

    inner class RestaurantViewHolder(val binding: ItemRestaurantBinding) : RecyclerView.ViewHolder(binding.root)

}