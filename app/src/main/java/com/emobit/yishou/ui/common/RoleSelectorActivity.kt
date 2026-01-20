package com.emobit.yishou.ui.common

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.emobit.yishou.R
import com.emobit.yishou.databinding.ActivityRoleSelectorBinding
import com.emobit.yishou.ui.elder.ElderMainActivity
import com.emobit.yishou.ui.family.FamilyMainActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * 角色选择页面
 * 用户选择"我是长辈"或"我是家属"进入对应端口
 */
@AndroidEntryPoint
class RoleSelectorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoleSelectorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // 安装启动屏幕
        installSplashScreen()
        
        super.onCreate(savedInstanceState)
        binding = ActivityRoleSelectorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupClickListeners()
        startAnimations()
    }

    private fun setupUI() {
        // 设置状态栏
        window.statusBarColor = getColor(android.R.color.transparent)
    }

    private fun setupClickListeners() {
        // 老人端入口
        binding.cardElder.setOnClickListener {
            navigateToElderApp()
        }

        // 子女端入口
        binding.cardFamily.setOnClickListener {
            navigateToFamilyApp()
        }
    }

    private fun startAnimations() {
        // Logo 动画
        val fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in).apply {
            duration = 600
        }
        binding.ivLogo.startAnimation(fadeIn)
        binding.tvAppName.startAnimation(fadeIn)
        binding.tvSlogan.startAnimation(fadeIn)

        // 卡片入场动画
        val slideUp = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left).apply {
            duration = 500
            startOffset = 200
        }
        binding.cardElder.startAnimation(slideUp)

        val slideUp2 = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left).apply {
            duration = 500
            startOffset = 300
        }
        binding.cardFamily.startAnimation(slideUp2)
    }

    private fun navigateToElderApp() {
        val intent = Intent(this, ElderMainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }

    private fun navigateToFamilyApp() {
        val intent = Intent(this, FamilyMainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }
}
