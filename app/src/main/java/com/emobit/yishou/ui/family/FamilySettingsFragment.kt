package com.emobit.yishou.ui.family

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.emobit.yishou.R
import com.emobit.yishou.databinding.FragmentFamilySettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FamilySettingsFragment : Fragment() {
    private var _binding: FragmentFamilySettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFamilySettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupProfile()
        setupQuickSettings()
        setupVolumeSlider()
        setupClickListeners()
    }

    private fun setupProfile() {
        // 设置个人资料信息（实际应从ViewModel获取）
        binding.tvElderName.text = "张阿姨"
        binding.tvElderInfo.text = "75岁·北京市"
        binding.tvSystemInfo.text = "系统配图与个性化"
    }

    private fun setupQuickSettings() {
        // 设置开关状态（实际应从ViewModel获取）
        binding.switchVoiceBroadcast.isChecked = true
        binding.switchLocationReminder.isChecked = true
        binding.switchARGlassesConnection.isChecked = true
        binding.switchMedicationReminder.isChecked = true
        binding.switchEmergencyMode.isChecked = false

        // 设置监听器
        binding.switchVoiceBroadcast.setOnCheckedChangeListener { _, isChecked ->
            // TODO: 保存设置
        }
        binding.switchLocationReminder.setOnCheckedChangeListener { _, isChecked ->
            // TODO: 保存设置
        }
        binding.switchARGlassesConnection.setOnCheckedChangeListener { _, isChecked ->
            // TODO: 保存设置
        }
        binding.switchMedicationReminder.setOnCheckedChangeListener { _, isChecked ->
            // TODO: 保存设置
        }
        binding.switchEmergencyMode.setOnCheckedChangeListener { _, isChecked ->
            // TODO: 保存设置
        }
    }

    private fun setupVolumeSlider() {
        binding.sliderVolume.value = 75f
        binding.tvVolumeLevel.text = "75%"

        binding.sliderVolume.addOnChangeListener { _, value, _ ->
            val volumePercent = value.toInt()
            binding.tvVolumeLevel.text = "$volumePercent%"
            // TODO: 保存音量设置
        }
    }

    private fun setupClickListeners() {
        binding.btnEdit.setOnClickListener {
            // TODO: 打开编辑资料页面
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
