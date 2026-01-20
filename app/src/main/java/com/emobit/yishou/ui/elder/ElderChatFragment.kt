package com.emobit.yishou.ui.elder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.emobit.yishou.databinding.FragmentElderChatBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * 老人端聊天页面
 * 与3D数字人进行语音/文字交互
 */
@AndroidEntryPoint
class ElderChatFragment : Fragment() {

    private var _binding: FragmentElderChatBinding? = null
    private val binding get() = _binding!!

    private var isListening = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentElderChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupClickListeners()
        addWelcomeMessage()
    }

    private fun setupUI() {
        // 设置初始状态
        binding.tvListeningHint.visibility = View.GONE
    }

    private fun setupClickListeners() {
        // 麦克风按钮
        binding.btnMic.setOnClickListener {
            toggleListening()
        }

        // 快捷回复按钮
        binding.chipHappy.setOnClickListener {
            sendMessage("今天很开心")
        }
        binding.chipWalk.setOnClickListener {
            sendMessage("想出去走走")
        }
        binding.chipPhoto.setOnClickListener {
            sendMessage("看看老照片")
        }
        binding.chipTired.setOnClickListener {
            sendMessage("有点累了")
        }
    }

    private fun addWelcomeMessage() {
        // 添加AI欢迎消息
        binding.tvAiMessage1.text = "您好呀！我是小忆，有什么可以帮您的吗？"
    }

    private fun toggleListening() {
        isListening = !isListening
        if (isListening) {
            binding.tvListeningHint.visibility = View.VISIBLE
            binding.tvListeningHint.text = "正在聆听…请说话"
            binding.btnMic.setBackgroundResource(com.emobit.yishou.R.drawable.bg_button_danger)
        } else {
            binding.tvListeningHint.visibility = View.GONE
            binding.btnMic.setBackgroundResource(com.emobit.yishou.R.drawable.bg_button_primary)
        }
    }

    private fun sendMessage(message: String) {
        // 这里添加发送消息的逻辑
        // 实际项目中会调用AI服务
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
