package hf.mobweb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import hf.mobweb.admin.AdminActivty
import hf.mobweb.databinding.ActivityLoginBinding
import hf.mobweb.user.UserActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.btnLogin.setOnClickListener{
            when{
                binding.switchAdmin.isChecked == true -> {
                    startActivity(Intent(this, AdminActivty::class.java))
                }
                binding.switchAdmin.isChecked == false-> {
                    startActivity(Intent(this, UserActivity::class.java))
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
}