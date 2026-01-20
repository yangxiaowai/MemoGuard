package com.emobit.yishou.ui.elder

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.emobit.yishou.R
import com.emobit.yishou.databinding.ActivityEmergencyBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * 紧急求助页面
 * 跌倒检测、迷路预警、SOS呼叫
 */
@AndroidEntryPoint
class EmergencyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmergencyBinding
    private var countDownTimer: CountDownTimer? = null
    private var isEmergencyMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmergencyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 检查是否是跌倒检测触发
        val isFallDetected = intent.getBooleanExtra("fall_detected", false)
        if (isFallDetected) {
            showFallDetectedUI()
        } else {
            showNormalUI()
        }

        setupClickListeners()
    }

    private fun showNormalUI() {
        binding.apply {
            tvEmergencyTitle.text = getString(R.string.emergency_title)
            tvEmergencyDesc.text = getString(R.string.emergency_prompt)
            btnSOS.visibility = View.VISIBLE
            layoutFallDetected.visibility = View.GONE
        }
    }

    private fun showFallDetectedUI() {
        isEmergencyMode = true
        binding.apply {
            tvEmergencyTitle.text = getString(R.string.emergency_fall_detected)
            tvEmergencyDesc.text = getString(R.string.emergency_are_you_ok)
            btnSOS.visibility = View.GONE
            layoutFallDetected.visibility = View.VISIBLE
            
            // 开始倒计时
            startCountdown()
        }
    }

    private fun startCountdown() {
        countDownTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = (millisUntilFinished / 1000).toInt()
                binding.tvCountdown.text = getString(R.string.emergency_auto_call, seconds)
            }

            override fun onFinish() {
                // 自动呼叫家人
                makeEmergencyCall()
            }
        }.start()
    }

    private fun setupClickListeners() {
        // SOS 长按触发
        binding.btnSOS.setOnLongClickListener {
            makeEmergencyCall()
            true
        }

        // 我没事按钮
        binding.btnImFine.setOnClickListener {
            countDownTimer?.cancel()
            showSafeConfirmation()
        }

        // 立即呼叫按钮
        binding.btnCallNow.setOnClickListener {
            countDownTimer?.cancel()
            makeEmergencyCall()
        }

        // 返回按钮
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun makeEmergencyCall() {
        binding.apply {
            layoutFallDetected.visibility = View.GONE
            btnSOS.visibility = View.GONE
            layoutCalling.visibility = View.VISIBLE
            tvEmergencyTitle.text = getString(R.string.emergency_calling)
            tvEmergencyDesc.visibility = View.GONE
        }
    }

    private fun showSafeConfirmation() {
        binding.apply {
            layoutFallDetected.visibility = View.GONE
            layoutCalling.visibility = View.GONE
            layoutSafe.visibility = View.VISIBLE
            tvEmergencyTitle.text = getString(R.string.emergency_great)
            tvEmergencyDesc.text = getString(R.string.emergency_glad_safe)
            tvEmergencyDesc.visibility = View.VISIBLE
        }

        // 3秒后自动关闭
        binding.root.postDelayed({ finish() }, 3000)
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }
}
