package com.emobit.yishou.ui.family

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.emobit.yishou.R
import com.emobit.yishou.databinding.FragmentFamilyDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * 子女端监护面板
 * 展示长辈实时状态、系统状态、快捷功能、今日提醒
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
        setupSystemStatus()
        setupQuickFunctions()
        setupTodayReminders()
    }

    private fun setupElderStatus() {
        binding.apply {
            tvElderName.text = "张阿姨"
            tvStatusText.text = "安全状态良好"
            tvBatteryLevel.text = "85%"
            
            // 更新当前时间
            val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            tvCurrentTime.text = timeFormat.format(Date())
        }
    }

    private fun setupSystemStatus() {
        binding.apply {
            tvGpsStatus.text = "正常"
            tvArStatus.text = "已连接"
            tvSteps.text = "3,245步"
            tvHeartRate.text = "75 BPM"
        }
    }

    private fun setupQuickFunctions() {
        binding.apply {
            // 位置追踪
            btnLocationTracking.setOnClickListener {
                findNavController().navigate(R.id.familyLocationFragment)
            }
            
            // AR辅助
            btnArAssist.setOnClickListener {
                findNavController().navigate(R.id.familyARFragment)
            }
            
            // 健康提醒 
            btnHealthReminder.setOnClickListener {
                // 导航到健康提醒页面
            }
            
            // 数字同伴
            btnDigitalCompanion.setOnClickListener {
                // 导航到数字同伴页面
            }
        }
    }

    private fun setupTodayReminders() {
        binding.apply {
            // 提醒1 - 已完成
            tvReminder1Title.text = "服用早晨药物"
            tvReminder1Time.text = "09:00"
            tvReminder1Status.text = "已完成"
            
            // 提醒2 - 已完成
            tvReminder2Title.text = "午餐时间"
            tvReminder2Time.text = "12:00"
            tvReminder2Status.text = "已完成"
            
            // 提醒3 - 待完成
            tvReminder3Title.text = "散步锻炼"
            tvReminder3Time.text = "14:30"
            
            // 提醒4 - 待完成
            tvReminder4Title.text = "服用晚间药物"
            tvReminder4Time.text = "18:00"
            
            // 查看全部按钮
            btnViewAll.setOnClickListener {
                // 导航到全部提醒页面
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
