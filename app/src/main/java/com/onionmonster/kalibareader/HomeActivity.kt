package com.onionmonster.kalibareader

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.onionmonster.kalibareader.favorite.FavoriteFragment
import com.onionmonster.kalibareader.home.HomeFragment
import com.onionmonster.kalibareader.settings.SettingsFragment
import com.onionmonster.kalibareader.stats.StatsFragment

class HomeActivity: AppCompatActivity() {

    val TAG = "Dev/" + javaClass.simpleName

    val homeFragment = HomeFragment()
    val favFragment = FavoriteFragment()
    val statsFragment = StatsFragment()
    val settingsFragment = SettingsFragment()
    var active = homeFragment as Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val libraryUri = intent.data

        Log.d(TAG, libraryUri.toString())

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navigation = findViewById<BottomNavigationView>(R.id.navigation_bottom_bar)

        navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    supportFragmentManager.beginTransaction().hide(active).show(homeFragment).commit()
                    active = homeFragment
                    true
                }

                R.id.navigation_fav -> {
                    supportFragmentManager.beginTransaction().hide(active).show(favFragment).commit()
                    active = favFragment
                    true
                }

                R.id.navigation_stats -> {
                    supportFragmentManager.beginTransaction().hide(active).show(statsFragment).commit()
                    active = statsFragment
                    true
                }

                R.id.navigation_settings -> {
                    supportFragmentManager.beginTransaction().hide(active).show(settingsFragment).commit()
                    active = settingsFragment
                    true
                }

                else -> false
            }
        }

        val arguments = Bundle()
        arguments.putString("libraryUriStr", libraryUri.toString())
        homeFragment.arguments = arguments

        supportFragmentManager.beginTransaction().add(R.id.main_container, settingsFragment, "4").hide(settingsFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.main_container, statsFragment, "3").hide(statsFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.main_container, favFragment, "2").hide(favFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.main_container, homeFragment, "1").commit()

    }

}