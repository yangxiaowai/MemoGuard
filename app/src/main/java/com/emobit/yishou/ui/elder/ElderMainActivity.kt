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

        // 底部导航栏已移除，老人端简化为单一界面
        // 所有功能通过语音交互触发
    }

    private fun setupClickListeners() {
        // 紧急按钮点击
        binding.btnEmergency.setOnClickListener {
            startActivity(Intent(this, EmergencyActivity::class.java))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
