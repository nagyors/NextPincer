package hf.mobweb.user

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import hf.mobweb.adapter.RestaurantAdapter
import hf.mobweb.data.Restaurant
import hf.mobweb.data.RestaurantDatabase
import hf.mobweb.databinding.ActivityUserBinding
import kotlin.concurrent.thread

class UserActivity : AppCompatActivity(), RestaurantAdapter.RestaurantClickListener {
    private lateinit var binding: ActivityUserBinding

    private lateinit var database: RestaurantDatabase
    private lateinit var adapter: RestaurantAdapter

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        database = RestaurantDatabase.getDatabase(applicationContext)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = RestaurantAdapter(this)
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = adapter
        loadItemsInBackground()
    }

    private fun loadItemsInBackground() {
        thread {
            val items = database.restaurantDao().getAll()
            runOnUiThread {
                adapter.update(items)
            }
        }
    }

    override fun onItemChanged(item: Restaurant) {
        thread {
            database.restaurantDao().update(item)
        }
    }

    override fun onItemDelete(item: Restaurant) {
        thread{
            database.restaurantDao().deleteItem(item)
        }
    }

    override fun onItemClicked(item: Restaurant){
        thread {
            var intent = Intent(this, RestaurantActivity::class.java)
            intent.putExtra("rest", item.id)
            intent.putExtra("name", item.name)
            startActivity(intent)
        }
    }
}