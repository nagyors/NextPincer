package hf.mobweb.admin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import hf.mobweb.adapter.MenuClickListener
import hf.mobweb.adapter.NewFoodAdapter
import hf.mobweb.data.Menu
import hf.mobweb.data.MenuDatabase
import hf.mobweb.databinding.ActivityAddFoodBinding
import hf.mobweb.fragments.NewFoodDialogFragment
import kotlin.concurrent.thread
import kotlin.properties.Delegates

class AddFoodActivity : AppCompatActivity(), MenuClickListener, NewFoodDialogFragment.NewFoodDialogListener {
    private lateinit var binding: ActivityAddFoodBinding

    private lateinit var database: MenuDatabase
    private lateinit var adapter: NewFoodAdapter
    var restaurantID by Delegates.notNull<Long>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        database = MenuDatabase.getDatabase(applicationContext)

        var intent = getIntent()
        restaurantID = intent.getLongExtra("rest", 0)

        binding.fab.setOnClickListener{
            val fragment = NewFoodDialogFragment()
            val mBundle = Bundle()
            mBundle.putString("restID", restaurantID.toString())
            fragment.arguments = mBundle
            fragment.show(supportFragmentManager, NewFoodDialogFragment.TAG)
        }
        init(restaurantID)
    }

    private fun init(id: Long) {
        adapter = NewFoodAdapter(this)
        binding.rvMenu.layoutManager = LinearLayoutManager(this)
        binding.rvMenu.adapter = adapter
        loadItemsInBackgroundById(id)
    }

    private fun loadItemsInBackgroundById(ID: Long) {
        thread {
            val items = database.menuDao().findMenuForRestaurant(ID.toString())
            runOnUiThread {
                adapter.update(items)
            }
        }
    }

    override fun onItemChanged(item: Menu) {
        TODO("Not yet implemented")
    }

    override fun onItemDelete(item: Menu) {
        thread{
            database.menuDao().deleteItem(item)
        }
    }

    override fun onFoodCreated(newItem: Menu) {
        thread {
            database.menuDao().insert(newItem)
            runOnUiThread{
                adapter.addItem(newItem)
            }
        }
    }

}