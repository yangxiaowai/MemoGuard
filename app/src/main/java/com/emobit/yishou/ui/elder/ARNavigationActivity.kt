package com.emobit.yishou.ui.elder

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.emobit.yishou.R
import com.emobit.yishou.databinding.ActivityArNavigationBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * AR实景导航页面
 * 使用摄像头提供AR实景导航，3D数字人引导
 */
@AndroidEntryPoint
class ARNavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArNavigationBinding
    private var isWandering = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val destination = intent.getStringExtra("destination") ?: "home"
        setupNavigation(destination)
        setupClickListeners()
    }

    private fun setupNavigation(destination: String) {
        binding.apply {
            when (destination) {
                "home" -> {
                    tvDestination.text = "回家"
                    tvAddress.text = "北京市朝阳区幸福小区3号楼"
                    tvDistance.text = "1.2 公里"
                    tvEta.text = "约 15 分钟"
                }
                "market" -> {
                    tvDestination.text = "朝阳菜市场"
                    tvAddress.text = "朝阳区建国路88号"
                    tvDistance.text = "500 米"
                    tvEta.text = "约 6 分钟"
                }
                else -> {
                    tvDestination.text = "朝阳公园"
                    tvAddress.text = "朝阳区朝阳公园南路1号"
                    tvDistance.text = "800 米"
                    tvEta.text = "约 10 分钟"
                }
            }
            
            tvNavInstruction.text = "前方 50 米左转"
        }
    }

    private fun setupClickListeners() {
        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnEndNav.setOnClickListener {
            finish()
        }

        // 模拟徘徊检测
        binding.btnSimulateWandering.setOnClickListener {
            showWanderingAlert()
        }

        binding.btnContinueNav.setOnClickListener {
            hideWanderingAlert()
        }

        binding.btnCallFamily.setOnClickListener {
            // 呼叫家人
        }
    }

    private fun showWanderingAlert() {
        isWandering = true
        binding.apply {
            layoutWanderingAlert.visibility = View.VISIBLE
            cardNavInfo.alpha = 0.5f
        }
    }

    private fun hideWanderingAlert() {
        isWandering = false
        binding.apply {
            layoutWanderingAlert.visibility = View.GONE
            cardNavInfo.alpha = 1f
        }
    }
}
