package hf.mobweb.admin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import hf.mobweb.adapter.NewRestaurantAdapter
import hf.mobweb.data.Restaurant
import hf.mobweb.data.RestaurantDatabase
import hf.mobweb.databinding.ActivityAdminBinding
import hf.mobweb.fragments.NewRestaurantDialogFragment
import hf.mobweb.user.RestaurantActivity
import kotlin.concurrent.thread

class AdminActivty : AppCompatActivity(), NewRestaurantAdapter.RestaurantClickListener,
    NewRestaurantDialogFragment.NewRestaurantDialogListener{
    private lateinit var binding: ActivityAdminBinding

    private lateinit var database: RestaurantDatabase
    private lateinit var adapter: NewRestaurantAdapter

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        database = RestaurantDatabase.getDatabase(applicationContext)

        binding.fab.setOnClickListener {
            NewRestaurantDialogFragment().show(
                supportFragmentManager,
                NewRestaurantDialogFragment.TAG
            )
        }

        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = NewRestaurantAdapter(this)
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

    override fun onItemClicked(item: Restaurant) {
        thread {
            var intent = Intent(this, AddFoodActivity::class.java)
            intent.putExtra("rest", item.id)
            startActivity(intent)
        }
    }

    override fun onRestaurantCreated(newItem: Restaurant) {
        thread {
            database.restaurantDao().insert(newItem)

            runOnUiThread {
                adapter.addItem(newItem)
            }
        }
    }
}