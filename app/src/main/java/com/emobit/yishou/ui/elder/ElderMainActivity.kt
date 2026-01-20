package com.emobit.yishou.ui.elder

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.emobit.yishou.R
import com.emobit.yishou.databinding.ActivityElderMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * 老人端主Activity
 * 包含底部导航栏和Fragment容器
 */
@AndroidEntryPoint
class ElderMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityElderMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityElderMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
        setupClickListeners()
    }

    private fun setupNavigation() {
        // 获取 NavHostFragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
        
        if (navHostFragment == null) {
            return
        }
        
        navController = navHostFragment.navController

        // 设置底部导航与 NavController 绑定
        binding.bottomNavigation.setupWithNavController(navController)
        
        // 防止重复点击当前页面导致的问题
        binding.bottomNavigation.setOnItemReselectedListener { 
            // 重新选择当前项时不做任何操作
        }
    }

    private fun setupClickListeners() {
        // 设置按钮点击 - 切换到设置页面
        binding.btnSettings.setOnClickListener {
            // 导航到设置 Fragment
            navController.navigate(R.id.elderSettingsFragment)
        }

        // 紧急按钮点击
        binding.btnEmergency.setOnClickListener {
            startActivity(Intent(this, EmergencyActivity::class.java))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
