package hf.mobweb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import hf.mobweb.R
import hf.mobweb.data.Menu
import hf.mobweb.data.Restaurant
import hf.mobweb.databinding.ItemFoodBinding
import hf.mobweb.databinding.ItemRestaurantMenuBinding

class RestaurantMenuAdapter(private val listener: MenuClickListener) : RecyclerView.Adapter<RestaurantMenuAdapter.RestaurantMenuViewHolder>() {
    private val items = mutableListOf<Menu>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =  RestaurantMenuViewHolder(
        ItemRestaurantMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: RestaurantMenuViewHolder, position: Int) {
        val food = items[position]

        holder.binding.ivIcon.setImageResource(getImageResource(food.type))
        holder.binding.tvCategory.text = food.type.toString()
        holder.binding.tvDescription.text = food.description
        holder.binding.tvPrice.text = "${food.price.toString()} Ft"
    }

    override fun getItemCount(): Int = items.size

    @DrawableRes()
    private fun getImageResource(type: Menu.Type): Int {
        return when (type) {
            Menu.Type.STARTER -> R.drawable.canapes
            Menu.Type.SOUP -> R.drawable.soup
            Menu.Type.MAINCOURSE -> R.drawable.appetite
            Menu.Type.DESERT -> R.drawable.icecream
            Menu.Type.DRINKS -> R.drawable.drink
        }
    }

    fun update(menus: List<Menu>) {
        items.clear()
        items.addAll(menus)
        notifyDataSetChanged()
    }

    inner class RestaurantMenuViewHolder(val binding: ItemRestaurantMenuBinding) : RecyclerView.ViewHolder(binding.root)


}