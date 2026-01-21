package com.emobit.yishou.ui.elder

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.emobit.yishou.R
import com.emobit.yishou.databinding.FragmentElderHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

/**
 * 老人端首页 Fragment
 * 简化为只显示3D数字人和语音交互
 * 所有设置和管理功能都在家属端完成
 */
@AndroidEntryPoint
class ElderHomeFragment : Fragment(), TextToSpeech.OnInitListener {

    private var _binding: FragmentElderHomeBinding? = null
    private val binding get() = _binding!!

    // 语音识别和合成
    private var speechRecognizer: SpeechRecognizer? = null
    private var textToSpeech: TextToSpeech? = null
    private var isListening = false
    private var isSpeaking = false

    // Unity3D数字人接口（占位，实际需要Unity集成）
    private var unityDigitalAvatar: UnityDigitalAvatarInterface? = null

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

        initializeVoiceServices()
        setupUnityAvatar()
        setupVoiceInteraction()
        speakGreeting()
    }

    private fun initializeVoiceServices() {
        // 初始化语音合成
        textToSpeech = TextToSpeech(requireContext(), this)

        // 初始化语音识别
        if (SpeechRecognizer.isRecognitionAvailable(requireContext())) {
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(requireContext())
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            textToSpeech?.language = Locale.CHINESE
            textToSpeech?.setSpeechRate(0.9f) // 稍微慢一点，适合老年人
        }
    }

    private fun setupUnityAvatar() {
        // TODO: 初始化Unity Player View
        // unityDigitalAvatar = UnityDigitalAvatarManager.getInstance()
        // unityDigitalAvatar?.initialize(requireContext(), binding.unityPlayerView)
        
        // 临时显示占位图
        binding.ivAvatarPlaceholder.visibility = View.VISIBLE
        
        // 设置数字人状态
        binding.tvAvatarStatus.text = "小忆在线"
        
        // 获取Unity数字人实例（占位实现）
        unityDigitalAvatar = UnityDigitalAvatarManager.getInstance()
        
        // 触发数字人待机动画
        unityDigitalAvatar?.playAnimation("Idle")
    }

    private fun setupVoiceInteraction() {
        // 长按开始录音
        binding.btnVoice.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    startListening()
                    true
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    stopListening()
                    true
                }
                else -> false
            }
        }
    }

    private fun startListening() {
        if (isListening || !SpeechRecognizer.isRecognitionAvailable(requireContext())) return
        
        isListening = true
        binding.apply {
            // 显示录音状态
            tvVoiceStatus.visibility = View.VISIBLE
            tvVoiceStatus.text = "正在聆听..."
            tvRecognizedText.visibility = View.GONE
            
            // 显示波纹动画
            voiceWaveIndicator.visibility = View.VISIBLE
            voiceWaveIndicator.animate()
                .scaleX(1.5f)
                .scaleY(1.5f)
                .alpha(0.3f)
                .setDuration(800)
                .withEndAction {
                    if (isListening) {
                        voiceWaveIndicator.animate()
                            .scaleX(1.2f)
                            .scaleY(1.2f)
                            .alpha(0.5f)
                            .setDuration(800)
                            .start()
                    }
                }
                .start()
            
            // 数字人表情变化：倾听
            unityDigitalAvatar?.setExpression("Listening")
            unityDigitalAvatar?.playAnimation("Listen")
        }

        // 开始语音识别
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "zh-CN")
            putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
        }

        // 设置识别结果监听
        speechRecognizer?.setRecognitionListener(object : android.speech.RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {
                // 准备就绪
            }

            override fun onBeginningOfSpeech() {
                binding.tvVoiceStatus.text = "正在识别..."
            }

            override fun onRmsChanged(rmsdB: Float) {
                // 音量变化，可用于可视化
            }

            override fun onBufferReceived(buffer: ByteArray?) {
                // 接收音频缓冲区
            }

            override fun onEndOfSpeech() {
                binding.tvVoiceStatus.text = "识别完成"
            }

            override fun onError(error: Int) {
                isListening = false
                binding.apply {
                    tvVoiceStatus.visibility = View.GONE
                    voiceWaveIndicator.visibility = View.GONE
                }
                speak("抱歉，我没有听清楚，请再说一遍")
            }

            override fun onResults(results: Bundle?) {
                val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                if (!matches.isNullOrEmpty()) {
                    val recognizedText = matches[0]
                    onSpeechRecognized(recognizedText)
                }
                isListening = false
                binding.apply {
                    tvVoiceStatus.visibility = View.GONE
                    voiceWaveIndicator.visibility = View.GONE
                }
            }

            override fun onPartialResults(partialResults: Bundle?) {
                val matches = partialResults?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                if (!matches.isNullOrEmpty()) {
                    binding.tvRecognizedText.text = matches[0]
                    binding.tvRecognizedText.visibility = View.VISIBLE
                }
            }

            override fun onEvent(eventType: Int, params: Bundle?) {
                // 事件回调
            }
        })

        speechRecognizer?.startListening(intent)
    }

    private fun stopListening() {
        if (!isListening) return
        
        isListening = false
        binding.apply {
            // 隐藏录音状态
            tvVoiceStatus.visibility = View.GONE
            voiceWaveIndicator.visibility = View.GONE
            voiceWaveIndicator.animate().cancel()
            voiceWaveIndicator.scaleX = 1.2f
            voiceWaveIndicator.scaleY = 1.2f
            voiceWaveIndicator.alpha = 0.5f
        }

        speechRecognizer?.stopListening()

        // 数字人表情变化：思考
        unityDigitalAvatar?.setExpression("Thinking")
        unityDigitalAvatar?.playAnimation("Think")
    }

    private fun onSpeechRecognized(text: String) {
        binding.apply {
            tvRecognizedText.text = text
            tvRecognizedText.visibility = View.VISIBLE
        }

        // 处理语音指令
        processVoiceCommand(text)
    }

    private fun processVoiceCommand(command: String) {
        val lowerCommand = command.lowercase(Locale.getDefault())
        
        // 数字人表情变化：理解
        unityDigitalAvatar?.setExpression("Happy")
        unityDigitalAvatar?.playAnimation("Nod")

        when {
            // 导航相关
            lowerCommand.contains("回家") || lowerCommand.contains("导航") -> {
                speak("好的，我来帮您导航回家！")
                navigateToARNavigation("home")
            }
            lowerCommand.contains("菜市场") -> {
                speak("好的，我来带您去菜市场！")
                navigateToARNavigation("market")
            }
            lowerCommand.contains("公园") -> {
                speak("好的，我来带您去公园！")
                navigateToARNavigation("park")
            }
            
            // 服药相关
            lowerCommand.contains("吃药") || lowerCommand.contains("服药") -> {
                speak("好的，我来监督您吃药！")
                navigateToMedicationVerify()
            }
            
            // 找东西
            lowerCommand.contains("找") && (lowerCommand.contains("钥匙") || lowerCommand.contains("眼镜") || lowerCommand.contains("东西")) -> {
                speak("好的，我来帮您找东西！")
                // navigateToFindItem()
            }
            
            // 聊天
            lowerCommand.contains("聊天") || lowerCommand.contains("说话") -> {
                speak("好的，我们聊聊天吧！")
                // startChat()
            }
            
            // 默认回复
            else -> {
                speak("我听不太清楚，您可以说：回家、吃药、找东西等等")
            }
        }
    }

    private fun navigateToARNavigation(destination: String) {
        val intent = Intent(requireContext(), ARNavigationActivity::class.java).apply {
            putExtra("destination", destination)
        }
        startActivity(intent)
    }

    private fun navigateToMedicationVerify() {
        val intent = Intent(requireContext(), MedicationVerifyActivity::class.java)
        startActivity(intent)
    }

    private fun speak(text: String) {
        if (isSpeaking) return
        
        isSpeaking = true
        binding.apply {
            // 显示数字人说话气泡
            cardAvatarSpeech.visibility = View.VISIBLE
            tvAvatarSpeech.text = text
            
            // 数字人表情变化：说话
            unityDigitalAvatar?.setExpression("Speaking")
            unityDigitalAvatar?.playAnimation("Talk")
        }

        textToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        
        // 延迟隐藏气泡
        binding.cardAvatarSpeech.postDelayed({
            binding.cardAvatarSpeech.visibility = View.GONE
            isSpeaking = false
            
            // 数字人恢复待机
            unityDigitalAvatar?.setExpression("Neutral")
            unityDigitalAvatar?.playAnimation("Idle")
        }, text.length * 100L + 500) // 根据文字长度计算时间
    }

    private fun speakGreeting() {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val greeting = when {
            hour < 6 -> "爷爷/奶奶，这么晚了还没睡呀"
            hour < 9 -> "爷爷/奶奶，早上好！"
            hour < 12 -> "爷爷/奶奶，上午好！"
            hour < 14 -> "爷爷/奶奶，中午好！"
            hour < 18 -> "爷爷/奶奶，下午好！"
            hour < 21 -> "爷爷/奶奶，晚上好！"
            else -> "爷爷/奶奶，这么晚了，注意休息哦"
        }
        
        // 延迟一点再说话，等界面加载完成
        binding.root.postDelayed({
            speak(greeting)
        }, 500)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        
        speechRecognizer?.destroy()
        textToSpeech?.stop()
        textToSpeech?.shutdown()
        
        _binding = null
    }
}

/**
 * Unity3D数字人接口
 * 用于控制3D数字人的动画、表情等
 * 实际项目中需要与Unity集成
 */
interface UnityDigitalAvatarInterface {
    /**
     * 播放动画
     * @param animationName 动画名称：Idle, Listen, Think, Talk, Nod, Walk等
     */
    fun playAnimation(animationName: String)
    
    /**
     * 设置表情
     * @param expression 表情名称：Neutral, Happy, Listening, Thinking, Speaking, Concerned等
     */
    fun setExpression(expression: String)
    
    /**
     * 说话时口型同步
     * @param isSpeaking 是否在说话
     */
    fun setSpeaking(isSpeaking: Boolean)
    
    /**
     * 设置位置和旋转（用于AR导航）
     */
    fun setPosition(x: Float, y: Float, z: Float)
    fun setRotation(yaw: Float)
}

/**
 * Unity数字人管理器（占位实现）
 * 实际项目中需要调用Unity Player的方法
 */
class UnityDigitalAvatarManager {
    companion object {
        @Volatile
        private var INSTANCE: UnityDigitalAvatarInterface? = null

        fun getInstance(): UnityDigitalAvatarInterface {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: object : UnityDigitalAvatarInterface {
                    override fun playAnimation(animationName: String) {
                        // TODO: 调用Unity方法播放动画
                        // UnityPlayer.UnitySendMessage("DigitalAvatar", "PlayAnimation", animationName)
                    }

                    override fun setExpression(expression: String) {
                        // TODO: 调用Unity方法设置表情
                        // UnityPlayer.UnitySendMessage("DigitalAvatar", "SetExpression", expression)
                    }

                    override fun setSpeaking(isSpeaking: Boolean) {
                        // TODO: 调用Unity方法控制口型
                        // UnityPlayer.UnitySendMessage("DigitalAvatar", "SetSpeaking", isSpeaking.toString())
                    }

                    override fun setPosition(x: Float, y: Float, z: Float) {
                        // TODO: 调用Unity方法设置位置
                        // UnityPlayer.UnitySendMessage("DigitalAvatar", "SetPosition", "$x,$y,$z")
                    }

                    override fun setRotation(yaw: Float) {
                        // TODO: 调用Unity方法设置旋转
                        // UnityPlayer.UnitySendMessage("DigitalAvatar", "SetRotation", yaw.toString())
                    }
                }.also { INSTANCE = it }
            }
        }
    }
}
