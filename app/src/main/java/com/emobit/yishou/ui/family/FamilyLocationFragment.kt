package com.emobit.yishou.ui.family

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.emobit.yishou.R
import com.emobit.yishou.databinding.FragmentFamilyLocationBinding
import com.emobit.yishou.ui.elder.ARNavigationActivity
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

/**
 * 家属端位置追踪页面
 * 展示老人实时位置、徘徊检测
 */
@AndroidEntryPoint
class FamilyLocationFragment : Fragment() {

    private var _binding: FragmentFamilyLocationBinding? = null
    private val binding get() = _binding!!

    // 徘徊检测状态
    private var isWanderingDetected = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFamilyLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCurrentLocation()
        setupWanderingDetection()
        setupClickListeners()
    }

    private fun setupCurrentLocation() {
        // 设置详细地址
        binding.tvDetailedAddress.text = "北京市朝阳区建国路88号"

        // 设置更新时间
        val timeFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        binding.tvUpdateTime.text = timeFormat.format(Date())
    }

    private fun setupWanderingDetection() {
        // 模拟徘徊检测状态（实际应从ViewModel或数据源获取）
        isWanderingDetected = false // 可以设置为 true 来显示警告

        if (isWanderingDetected) {
            // 警告状态
            binding.tvWanderingStatus.text = getString(R.string.wandering_warning)
            binding.tvWanderingStatus.setBackgroundResource(R.drawable.bg_pill_warning)
            binding.tvWanderingStatus.setTextColor(
                resources.getColor(R.color.warning, requireContext().theme)
            )
            binding.tvWanderingStatusValue.text = getString(R.string.wandering_detected)
        } else {
            // 正常状态
            binding.tvWanderingStatus.text = getString(R.string.wandering_normal)
            binding.tvWanderingStatus.setBackgroundResource(R.drawable.bg_pill_safe)
            binding.tvWanderingStatus.setTextColor(
                resources.getColor(R.color.safe, requireContext().theme)
            )
            binding.tvWanderingStatusValue.text = getString(R.string.wandering_no_abnormality)
        }

        // 设置DBSCAN聚类分析结果 - 活动区域数量
        binding.tvActiveAreas.text = "3${getString(R.string.wandering_active_areas)}"
    }

    private fun setupClickListeners() {
        // 导航至此按钮
        binding.btnNavigateToHere.setOnClickListener {
            // 导航到当前位置
            val intent = Intent(requireContext(), ARNavigationActivity::class.java).apply {
                putExtra("destination", "current_location")
                putExtra("address", binding.tvDetailedAddress.text.toString())
            }
            startActivity(intent)
        }

        // 回家路线按钮
        binding.btnRouteHome.setOnClickListener {
            // 导航回家
            val intent = Intent(requireContext(), ARNavigationActivity::class.java).apply {
                putExtra("destination", "home")
            }
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
