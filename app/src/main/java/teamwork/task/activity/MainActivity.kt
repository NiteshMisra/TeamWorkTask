package teamwork.task.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.navigation.NavigationView
import teamwork.task.R
import teamwork.task.adapter.TabAdapter
import teamwork.task.databinding.ActivityMainBinding
import teamwork.task.extras.UserPref
import teamwork.task.fragments.ContactUs
import teamwork.task.fragments.Images
import teamwork.task.fragments.ViewImages
import teamwork.task.viewmodel.UserViewModel

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawer,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        toggle.syncState()
        binding.drawer.addDrawerListener(toggle)
        binding.navView.setNavigationItemSelectedListener(this)

        val tabAdapter = TabAdapter(supportFragmentManager)
        tabAdapter.addFragment(ContactUs.newInstance(), "ContactUs")
        tabAdapter.addFragment(Images.newInstance(), "Images")
        tabAdapter.addFragment(ViewImages.newInstance(), "View Images")

        binding.viewPager.adapter = tabAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        val header = binding.navView.getHeaderView(0)
        val email : TextView = header.findViewById(R.id.email)
        val name : TextView = header.findViewById(R.id.name)

        val userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.getUser(this,UserPref.userEmail()!!).observe(this, Observer {
            it?.let {
                email.text = it.userEmail
                name.text = it.userName
            }
        })

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.nav_logOut){
            UserPref.isLoggedIn(false)
            val intent = Intent(this,LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        binding.drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (binding.drawer.isDrawerOpen(GravityCompat.START)){
            binding.drawer.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }

}