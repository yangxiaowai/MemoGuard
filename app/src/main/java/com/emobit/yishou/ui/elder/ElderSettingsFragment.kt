package com.emobit.yishou.ui.elder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.emobit.yishou.databinding.FragmentElderSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * 老人端设置页面
 * 简化的设置选项，适老化设计
 */
@AndroidEntryPoint
class ElderSettingsFragment : Fragment() {

    private var _binding: FragmentElderSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentElderSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUserInfo()
        setupSwitches()
    }

    private fun setupUserInfo() {
        binding.apply {
            tvUserName.text = "王大爷"
            tvUserPhone.text = "138****8888"
        }
    }

    private fun setupSwitches() {
        binding.apply {
            switchVoice.isChecked = true
            switchNotification.isChecked = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
