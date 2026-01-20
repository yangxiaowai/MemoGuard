package com.emobit.yishou.ui.elder

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.emobit.yishou.databinding.FragmentElderNavigationBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * 老人端导航页面
 * 展示常用地点、开启AR实景导航
 */
@AndroidEntryPoint
class ElderNavigationFragment : Fragment() {

    private var _binding: FragmentElderNavigationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentElderNavigationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDestinations()
        setupClickListeners()
    }

    private fun setupDestinations() {
        // 设置常用地点数据
        binding.apply {
            tvHomeAddress.text = "北京市朝阳区幸福小区3号楼"
            tvHomeDistance.text = "距离 1.2 公里"
            
            tvMarketAddress.text = "朝阳菜市场"
            tvMarketDistance.text = "距离 500 米"
            
            tvParkAddress.text = "朝阳公园"
            tvParkDistance.text = "距离 800 米"
        }
    }

    private fun setupClickListeners() {
        // 回家导航
        binding.cardHome.setOnClickListener {
            startARNavigation("home")
        }

        // 去菜市场
        binding.cardMarket.setOnClickListener {
            startARNavigation("market")
        }

        // 去公园
        binding.cardPark.setOnClickListener {
            startARNavigation("park")
        }

        // 开始AR导航按钮
        binding.btnStartNav.setOnClickListener {
            startARNavigation("home")
        }
    }

    private fun startARNavigation(destination: String) {
        val intent = Intent(requireContext(), ARNavigationActivity::class.java).apply {
            putExtra("destination", destination)
        }
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
