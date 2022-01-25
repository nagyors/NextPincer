package hf.mobweb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import hf.mobweb.R
import hf.mobweb.data.Menu
import hf.mobweb.databinding.ItemFoodBinding

class NewFoodAdapter(private val listener: MenuClickListener) : RecyclerView.Adapter<NewFoodAdapter.MenuViewHolder>() {

    private val items = mutableListOf<Menu>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MenuViewHolder(
        ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: NewFoodAdapter.MenuViewHolder, position: Int) {
        val food = items[position]

        holder.binding.tvName.text = food.name
        holder.binding.tvCategory.text = food.type.toString()
        holder.binding.tvDescription.text = food.description
        holder.binding.tvPrice.text = food.price.toString()

        holder.binding.ivIcon.setImageResource(getImageResource(food.type))

        holder.binding.ibRemove.setOnClickListener{
            listener.onItemDelete(food)
            items.remove(food)
            notifyDataSetChanged()
        }
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

    fun addItem(item: Menu) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun update(menus: List<Menu>) {
        items.clear()
        items.addAll(menus)
        notifyDataSetChanged()
    }

    inner class MenuViewHolder(val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root)
}