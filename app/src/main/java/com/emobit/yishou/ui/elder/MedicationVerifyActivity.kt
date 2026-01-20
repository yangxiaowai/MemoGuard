package com.emobit.yishou.ui.elder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.emobit.yishou.databinding.ActivityMedicationVerifyBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * 服药验证Activity
 * 通过摄像头识别药盒，手表IMU识别吞咽动作
 */
@AndroidEntryPoint
class MedicationVerifyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMedicationVerifyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMedicationVerifyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        binding.btnBack.setOnClickListener { finish() }
        binding.btnComplete.setOnClickListener { 
            // 完成服药，返回
            finish()
        }
    }
}
