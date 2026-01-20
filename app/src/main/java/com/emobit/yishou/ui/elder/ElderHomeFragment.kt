package com.emobit.yishou.ui.elder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.emobit.yishou.R
import com.emobit.yishou.databinding.FragmentElderHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

/**
 * 老人端首页 Fragment
 * 展示3D数字人、健康状态、快捷功能入口
 */
@AndroidEntryPoint
class ElderHomeFragment : Fragment() {

    private var _binding: FragmentElderHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentElderHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTimeDisplay()
        setupGreeting()
        setupHealthCards()
        setupQuickActions()
        setupReminder()
        startAnimations()
    }

    private fun setupTimeDisplay() {
        // 更新时间显示
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val dateFormat = SimpleDateFormat("M月d日 EEEE", Locale.CHINESE)
        val now = Date()

        binding.tvTime.text = timeFormat.format(now)
        binding.tvDate.text = dateFormat.format(now)
    }

    private fun setupGreeting() {
        // 根据时间设置问候语
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val greeting = when {
            hour < 6 -> getString(R.string.greeting_night)
            hour < 9 -> getString(R.string.greeting_morning)
            hour < 12 -> getString(R.string.greeting_morning)
            hour < 14 -> getString(R.string.greeting_noon)
            hour < 18 -> getString(R.string.greeting_afternoon)
            hour < 21 -> getString(R.string.greeting_evening)
            else -> getString(R.string.greeting_night)
        }
        binding.tvGreeting.text = greeting
    }

    private fun setupHealthCards() {
        // 设置健康数据（模拟数据）
        binding.apply {
            // 心率
            tvHeartRateValue.text = "72"
            tvHeartRateLabel.text = getString(R.string.health_heart_rate)

            // 血氧
            tvBloodOxygenValue.text = "98"
            tvBloodOxygenLabel.text = getString(R.string.health_blood_oxygen)

            // 步数
            tvStepsValue.text = "3,240"
            tvStepsLabel.text = getString(R.string.health_steps)

            // 睡眠
            tvSleepValue.text = "7.5"
            tvSleepLabel.text = getString(R.string.health_sleep)
        }
    }

    private fun setupQuickActions() {
        binding.apply {
            // 回家导航
            cardNavigation.setOnClickListener {
                findNavController().navigate(R.id.elderNavigationFragment)
            }

            // 服药提醒
            cardMedication.setOnClickListener {
                findNavController().navigate(R.id.elderMedicationFragment)
            }

            // 找东西
            cardFindItem.setOnClickListener {
                findNavController().navigate(R.id.elderFindItemFragment)
            }

            // 忆往昔
            cardMemory.setOnClickListener {
                findNavController().navigate(R.id.elderMemoryFragment)
            }
        }

        // 设置待服药次数
        binding.tvMedicationBadge.text = "2"
    }

    private fun setupReminder() {
        // 设置提醒横幅
        binding.tvReminderTime.text = "下午 3:00"
        binding.tvReminderContent.text = "该吃降压药了"
    }

    private var isAnimating = false

    private fun startAnimations() {
        if (_binding == null || !isAdded) return
        
        // 入场动画
        val fadeIn = AnimationUtils.loadAnimation(requireContext(), android.R.anim.fade_in).apply {
            duration = 400
        }

        binding.timeContainer.startAnimation(fadeIn)

        // 头像呼吸动画
        startBreathingAnimation()
    }
    
    private fun startBreathingAnimation() {
        if (_binding == null || !isAdded || isAnimating) return
        isAnimating = true
        
        binding.avatarContainer.animate()
            .scaleX(1.02f)
            .scaleY(1.02f)
            .setDuration(2000)
            .withEndAction {
                if (_binding != null && isAdded) {
                    binding.avatarContainer.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(2000)
                        .withEndAction { 
                            isAnimating = false
                            startBreathingAnimation() 
                        }
                        .start()
                }
            }
            .start()
    }
    
    override fun onPause() {
        super.onPause()
        // 停止动画
        _binding?.avatarContainer?.animate()?.cancel()
        isAnimating = false
    }
    
    override fun onResume() {
        super.onResume()
        // 恢复动画
        if (!isAnimating) {
            startBreathingAnimation()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
