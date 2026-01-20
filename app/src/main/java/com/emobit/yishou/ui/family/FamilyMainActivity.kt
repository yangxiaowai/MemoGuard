package com.emobit.yishou.ui.family

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.emobit.yishou.R
import com.emobit.yishou.databinding.ActivityFamilyMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * 子女监护端主Activity
 * 包含底部导航栏和Fragment容器
 */
@AndroidEntryPoint
class FamilyMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFamilyMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFamilyMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_family) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavigationFamily.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
