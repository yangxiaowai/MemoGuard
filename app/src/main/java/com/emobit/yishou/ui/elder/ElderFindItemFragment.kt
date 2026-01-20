package com.emobit.yishou.ui.elder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.emobit.yishou.databinding.FragmentElderFindItemBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * 老人端寻物辅助页面
 * 帮助老人找回常用物品，使用记忆锚点
 */
@AndroidEntryPoint
class ElderFindItemFragment : Fragment() {

    private var _binding: FragmentElderFindItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentElderFindItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupItemList()
    }

    private fun setupItemList() {
        // 设置记忆锚点物品列表
        binding.apply {
            tvItem1Name.text = "钥匙"
            tvItem1Location.text = "客厅茶几上"
            tvItem1Time.text = "2小时前"
            
            tvItem2Name.text = "眼镜"
            tvItem2Location.text = "卧室床头柜"
            tvItem2Time.text = "4小时前"
            
            tvItem3Name.text = "遥控器"
            tvItem3Location.text = "沙发右侧"
            tvItem3Time.text = "30分钟前"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
