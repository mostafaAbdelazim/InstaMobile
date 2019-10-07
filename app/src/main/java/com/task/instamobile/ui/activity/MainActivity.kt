package com.task.instamobile.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import com.task.instamobile.R
import kotlinx.android.synthetic.main.activity_main.*

const val CATEGORIES_COLLECTION_REF = "Categories"
const val RECIPES_COLLECTION_REF = "Recipes"

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = customToolbar
        setSupportActionBar(toolbar)
        drawerLayout = drawer_layout
        navController = this.findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener { p0 ->
            p0.isChecked = true
            drawerLayout.closeDrawers()
            when (p0.itemId) {
                R.id.actionHome -> {
                    if (navController.currentDestination?.id != R.id.actionHome) {
                        navController.navigate(R.id.actionHome)
                    }
                }
                R.id.actionCategories -> {
                    if (navController.currentDestination?.id != R.id.actionCategories) {
                        navController.navigate(R.id.actionCategories)
                    }
                }
            }
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    fun showSnackBar(message: String) {
        Snackbar.make(this.findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show()
    }
}