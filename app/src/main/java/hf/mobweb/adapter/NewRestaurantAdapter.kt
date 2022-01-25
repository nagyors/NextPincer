package hf.mobweb.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import hf.mobweb.R
import hf.mobweb.data.Restaurant
import hf.mobweb.databinding.ItemNewrestaurantBinding
import hf.mobweb.user.RestaurantActivity

class NewRestaurantAdapter(private val listener: RestaurantClickListener) :
    RecyclerView.Adapter<NewRestaurantAdapter.RestaurantViewHolder>() {

    private val items = mutableListOf<Restaurant>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RestaurantViewHolder(
        ItemNewrestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurantItem = items[position]

        holder.binding.ivIcon.setImageResource(getImageResource(restaurantItem.cuisine))
        holder.binding.tvName.text= restaurantItem.name
        holder.binding.tvCuisine.text = restaurantItem.cuisine
        holder.binding.tvPricing.text = restaurantItem.pricing
        holder.binding.tvRating.text = restaurantItem.rating.toString()

        holder.binding.ibRemove.setOnClickListener{
            listener.onItemDelete(restaurantItem)
            items.remove(restaurantItem)
            notifyDataSetChanged()
        }

        holder.binding.ibAdd.setOnClickListener{
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

    inner class RestaurantViewHolder(val binding: ItemNewrestaurantBinding) : RecyclerView.ViewHolder(binding.root)

}