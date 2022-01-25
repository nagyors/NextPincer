package hf.mobweb.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hf.mobweb.databinding.ActivityRestaurantBinding

import hf.mobweb.adapter.MenuPageAdapter
import hf.mobweb.data.MenuDatabase
import hf.mobweb.data.RestaurantDatabase
import kotlin.concurrent.thread
import kotlin.properties.Delegates


class RestaurantActivity: AppCompatActivity() {
    private lateinit var binding: ActivityRestaurantBinding

    private lateinit var restaurantDatabase: RestaurantDatabase

    var restaurantID by Delegates.notNull<Long>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)
        restaurantDatabase = RestaurantDatabase.getDatabase(applicationContext)

        var intent = getIntent()
        restaurantID = intent.getLongExtra("rest", 0)


        binding.toolbar.setTitle(intent.getStringExtra("name"))
        setSupportActionBar(binding.toolbar)

        binding.vpMenu.adapter = MenuPageAdapter(supportFragmentManager)
    }

    internal fun getRestID(): Long = restaurantID
}