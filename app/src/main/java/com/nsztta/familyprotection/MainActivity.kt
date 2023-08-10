package com.nsztta.familyprotection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttomBar=findViewById<BottomNavigationView>(R.id.buttom_Bar)

       buttomBar.setOnItemSelectedListener{
          if (it.itemId==R.id.nav_guard){
              inflateFragment(GuardFragment.newInstance())
          }
           if (it.itemId==R.id.nav_home){
               inflateFragment(HomeFragment.newInstance())
           }else if (it.itemId==R.id.nav_dashboard){
               inflateFragment(DashboardFragment.newInstance())
           }else if (it.itemId==R.id.nav_profile){
               inflateFragment(ProfileFragment.newInstance())
           }
           true
       }

    }

    private fun inflateFragment(newInstance:Fragment) {
        val transition = supportFragmentManager.beginTransaction()
        transition.replace(R.id.container,newInstance)
        transition.commit()
    }
}