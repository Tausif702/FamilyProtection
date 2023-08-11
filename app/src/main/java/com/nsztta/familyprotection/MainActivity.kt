package com.nsztta.familyprotection

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    val permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.CAMERA,
        Manifest.permission.READ_CONTACTS
    )
    val permissionCode = 1420


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttomBar = findViewById<BottomNavigationView>(R.id.buttom_Bar)

        buttomBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_guard -> {
                    inflateFragment(GuardFragment.newInstance())
                }

                R.id.nav_home -> {
                    inflateFragment(HomeFragment.newInstance())
                }

                R.id.nav_dashboard -> {
                    inflateFragment(MapsFragment())
                }

                R.id.nav_profile -> {
                    inflateFragment(ProfileFragment.newInstance())
                }
            }
            true
        }
        buttomBar.selectedItemId = R.id.nav_home
        /* fundtion for create ask permission by the user  */
        askForPermission()

    }

    private fun askForPermission() {
        ActivityCompat.requestPermissions(this, permissions, permissionCode)

    }

    private fun allPermistionGranted(): Boolean {
        for (item in permissions) {
            if (ContextCompat.checkSelfPermission(this, item) != PackageManager.PERMISSION_GRANTED)
                return false
        }
        return true
    }

    // this function is used for open permitted device....
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == permissionCode) {
            if (allPermistionGranted()) {
                //openCamra()

            } else {

            }
        }


    }

    //open camra funtion
    private fun openCamra() {
        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        startActivity(intent)

    }

    private fun inflateFragment(newInstance: Fragment) {
        val transition = supportFragmentManager.beginTransaction()
        transition.replace(R.id.container, newInstance)
        transition.commit()
    }
}