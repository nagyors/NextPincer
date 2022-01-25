package hf.mobweb.fragments.menu

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hf.mobweb.adapter.MenuClickListener
import hf.mobweb.adapter.NewFoodAdapter
import hf.mobweb.adapter.RestaurantMenuAdapter
import hf.mobweb.data.Menu
import hf.mobweb.data.MenuDatabase
import hf.mobweb.databinding.MenuCategoryBinding
import hf.mobweb.user.RestaurantActivity
import kotlin.concurrent.thread
import kotlin.properties.Delegates

class DesertMenuFragment : Fragment(), MenuClickListener {
    private lateinit var binding: MenuCategoryBinding

    private lateinit var database: MenuDatabase
    private lateinit var adapter: RestaurantMenuAdapter

    var restaurantID by Delegates.notNull<Long>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = MenuCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = MenuDatabase.getDatabase(view.context)

        binding.tvCategory.text = "Desert"

        init((activity as RestaurantActivity).getRestID())
    }

    private fun init(id: Long){
        adapter = RestaurantMenuAdapter(this)
        binding.rvMenu.layoutManager = LinearLayoutManager(requireView().context)
        binding.rvMenu.adapter = adapter
        loadItemsInBackgroundById(id)
    }

    private fun loadItemsInBackgroundById(ID: Long) {
        thread {
            var items = mutableListOf<Menu>()
            val allMenu = database.menuDao().findMenuForRestaurant(ID.toString())
            for(food in allMenu){
                if(food.type.toString() == "DESERT")
                    items.add(food)
            }
            activity?.runOnUiThread(Runnable {
                adapter.update(items)
            })
        }
    }


    override fun onItemChanged(item: Menu) {
        TODO("Not yet implemented")
    }

    override fun onItemDelete(item: Menu) {
        TODO("Not yet implemented")
    }
}