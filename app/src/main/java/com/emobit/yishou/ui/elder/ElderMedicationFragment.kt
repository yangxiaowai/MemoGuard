package com.emobit.yishou.ui.elder

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.emobit.yishou.R
import com.emobit.yishou.databinding.FragmentElderMedicationBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * 老人端服药管理页面
 * 展示今日药品列表、服药记录、开始服药验证
 */
@AndroidEntryPoint
class ElderMedicationFragment : Fragment() {

    private var _binding: FragmentElderMedicationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentElderMedicationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupMedicationList()
        setupClickListeners()
    }

    private fun setupMedicationList() {
        binding.apply {
            // 设置药品数据 (模拟)
            tvMed1Name.text = "降压药"
            tvMed1Time.text = "早餐后 · 1片"
            tvMed1Status.text = "已服用"
            tvMed1Status.setBackgroundResource(R.drawable.bg_status_safe)
            
            tvMed2Name.text = "降糖药"
            tvMed2Time.text = "午餐后 · 2片"
            tvMed2Status.text = "待服用"
            tvMed2Status.setBackgroundResource(R.drawable.bg_status_warning)
            
            tvMed3Name.text = "维生素"
            tvMed3Time.text = "晚餐后 · 1片"
            tvMed3Status.text = "待服用"
            tvMed3Status.setBackgroundResource(R.drawable.bg_status_warning)
            
            // 统计
            tvTotalMeds.text = "3"
            tvTakenMeds.text = "1"
            tvPendingMeds.text = "2"
        }
    }

    private fun setupClickListeners() {
        // 开始服药验证
        binding.btnStartMedication.setOnClickListener {
            val intent = Intent(requireContext(), MedicationVerifyActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
