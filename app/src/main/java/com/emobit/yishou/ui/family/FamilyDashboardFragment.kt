package com.emobit.yishou.ui.family

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.emobit.yishou.databinding.FragmentFamilyDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * 子女端监护面板
 * 展示长辈实时状态、健康指标、今日活动
 */
@AndroidEntryPoint
class FamilyDashboardFragment : Fragment() {

    private var _binding: FragmentFamilyDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFamilyDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupElderStatus()
        setupHealthMetrics()
        setupTodayActivities()
    }

    private fun setupElderStatus() {
        binding.apply {
            tvElderName.text = "王大爷"
            tvStatusText.text = "状态正常"
            tvLocationText.text = "在家中 · 安全区域"
            tvUpdateTime.text = "更新于 2分钟前"
        }
    }

    private fun setupHealthMetrics() {
        binding.apply {
            tvHeartRate.text = "72"
            tvBloodOxygen.text = "98"
            tvSteps.text = "3,240"
            tvSleep.text = "7.5"
        }
    }

    private fun setupTodayActivities() {
        binding.apply {
            tvActivity1.text = "08:30 · 起床"
            tvActivity2.text = "09:00 · 服药 (降压药)"
            tvActivity3.text = "10:30 · 外出散步"
            tvActivity4.text = "12:00 · 回到家中"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
