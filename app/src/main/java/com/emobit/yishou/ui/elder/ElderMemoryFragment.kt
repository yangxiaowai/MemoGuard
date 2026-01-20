package com.emobit.yishou.ui.elder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.emobit.yishou.databinding.FragmentElderMemoryBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * 老人端记忆唤醒页面
 * 数字怀旧疗法，地标触发记忆，老照片回忆
 */
@AndroidEntryPoint
class ElderMemoryFragment : Fragment() {

    private var _binding: FragmentElderMemoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentElderMemoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMemoryCards()
    }

    private fun setupMemoryCards() {
        binding.apply {
            tvMemory1Title.text = "1995年 工厂表彰大会"
            tvMemory1Location.text = "北京第一机床厂"
            
            tvMemory2Title.text = "孙女满月酒"
            tvMemory2Location.text = "家中"
            
            tvMemory3Title.text = "金婚纪念日"
            tvMemory3Location.text = "北海公园"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
