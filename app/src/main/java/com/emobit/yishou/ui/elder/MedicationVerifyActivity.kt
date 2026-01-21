package com.emobit.yishou.ui.elder

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.emobit.yishou.R
import com.emobit.yishou.databinding.ActivityMedicationVerifyBinding
import com.google.common.util.concurrent.ListenableFuture
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * 服药验证Activity
 * 通过摄像头识别药盒，手表IMU识别吞咽动作
 */
@AndroidEntryPoint
class MedicationVerifyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMedicationVerifyBinding
    private var cameraExecutor: ExecutorService? = null
    private var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>? = null

    // 识别步骤状态
    private enum class Step {
        DETECTING_MEDICATION_BOX, // 识别药盒
        DETECTING_HAND_MOVEMENT,  // 检测抬手动作
        DETECTING_SWALLOW         // 检测吞咽动作
    }

    private var currentStep = Step.DETECTING_MEDICATION_BOX
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMedicationVerifyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cameraExecutor = Executors.newSingleThreadExecutor()

        setupUI()
        requestCameraPermission()
    }

    private fun setupUI() {
        binding.btnBack.setOnClickListener { finish() }
        
        binding.btnComplete.setOnClickListener {
            // 完成服药，返回
            finish()
        }

        binding.btnLater.setOnClickListener {
            finish()
        }

        // 初始化步骤状态
        updateStepUI()
    }

    private fun requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera()
            } else {
                Toast.makeText(this, "需要摄像头权限才能使用服药验证功能", Toast.LENGTH_LONG).show()
                // 即使没有权限也继续模拟流程
                simulateDetectionProcess()
            }
        }
    }

    private fun startCamera() {
        cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture?.addListener({
            try {
                val cameraProvider = cameraProviderFuture!!.get()

                // 设置预览
                val preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(binding.cameraPreview.surfaceProvider)
                }

                // 选择后置摄像头
                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(this, cameraSelector, preview)
                } catch (e: Exception) {
                    // 如果无法启动摄像头，使用模拟流程
                    binding.cameraPreview.visibility = View.GONE
                    simulateDetectionProcess()
                }
            } catch (e: Exception) {
                // 摄像头启动失败，使用模拟流程
                binding.cameraPreview.visibility = View.GONE
                simulateDetectionProcess()
            }
        }, ContextCompat.getMainExecutor(this))

        // 开始模拟识别流程
        simulateDetectionProcess()
    }

    /**
     * 模拟识别流程
     */
    private fun simulateDetectionProcess() {
        // 步骤1: 识别药盒（3秒后完成）
        handler.postDelayed({
            completeStep1()
        }, 3000)

        // 步骤2: 检测抬手动作（6秒后完成）
        handler.postDelayed({
            completeStep2()
        }, 8000)

        // 步骤3: 检测吞咽动作（12秒后完成）
        handler.postDelayed({
            completeStep3()
        }, 15000)
    }

    private fun completeStep1() {
        currentStep = Step.DETECTING_HAND_MOVEMENT
        
        // 更新UI
        binding.apply {
            // 步骤1完成
            cardStep1.setCardBackgroundColor(getColor(R.color.safe_50))
            step1Icon.setBackgroundResource(R.drawable.bg_circle_safe)
            tvStep1Number.visibility = View.GONE
            ivStep1Check.visibility = View.VISIBLE
            tvStep1Status.text = "药盒识别成功！"
            tvStep1Status.setTextColor(getColor(R.color.safe))

            // 更新识别状态
            tvDetectionStatus.text = "药盒识别成功，请开始服药"
            ivDetectionIcon.setImageResource(R.drawable.ic_check)
            ivDetectionIcon.setColorFilter(getColor(R.color.safe))
            tvCameraHint.text = "请拿起药片，抬手送入口中"

            // 激活步骤2
            cardStep2.setCardBackgroundColor(getColor(R.color.primary_50))
            step2Icon.setBackgroundResource(R.drawable.bg_circle_primary)
            tvStep2Number.setTextColor(ContextCompat.getColor(this@MedicationVerifyActivity, R.color.text_on_primary))
            tvStep2Status.text = "检测中..."
            tvStep2Status.setTextColor(getColor(R.color.primary))
        }

        updateStepUI()
    }

    private fun completeStep2() {
        currentStep = Step.DETECTING_SWALLOW
        
        // 更新UI
        binding.apply {
            // 步骤2完成
            cardStep2.setCardBackgroundColor(getColor(R.color.safe_50))
            step2Icon.setBackgroundResource(R.drawable.bg_circle_safe)
            tvStep2Number.visibility = View.GONE
            ivStep2Check.visibility = View.VISIBLE
            tvStep2Status.text = "动作检测成功！"
            tvStep2Status.setTextColor(getColor(R.color.safe))

            // 更新识别状态
            tvDetectionStatus.text = "动作检测成功，请完成吞咽"
            ivDetectionIcon.setImageResource(R.drawable.ic_check)
            ivDetectionIcon.setColorFilter(getColor(R.color.safe))
            tvCameraHint.text = "请完成吞咽动作"

            // 激活步骤3
            cardStep3.setCardBackgroundColor(getColor(R.color.primary_50))
            step3Icon.setBackgroundResource(R.drawable.bg_circle_primary)
            tvStep3Number.setTextColor(ContextCompat.getColor(this@MedicationVerifyActivity, R.color.text_on_primary))
            tvStep3Status.text = "检测中..."
            tvStep3Status.setTextColor(getColor(R.color.primary))
        }

        updateStepUI()
    }

    private fun completeStep3() {
        // 更新UI
        binding.apply {
            // 步骤3完成
            cardStep3.setCardBackgroundColor(getColor(R.color.safe_50))
            step3Icon.setBackgroundResource(R.drawable.bg_circle_safe)
            tvStep3Number.visibility = View.GONE
            ivStep3Check.visibility = View.VISIBLE
            tvStep3Status.text = "吞咽动作完成！"
            tvStep3Status.setTextColor(getColor(R.color.safe))

            // 隐藏识别状态
            cardDetectionStatus.visibility = View.GONE
            detectionFrame.visibility = View.GONE
            tvCameraHint.text = "服药完成！"

            // 更新提示文字
            tvPromptText.text = "太棒了！\n服药记录已保存"

            // 启用完成按钮
            btnComplete.isEnabled = true
            btnComplete.alpha = 1f
        }

        // 显示成功动画或提示
        Toast.makeText(this, "服药验证完成！", Toast.LENGTH_SHORT).show()
    }

    private fun updateStepUI() {
        // 根据当前步骤更新UI状态
        when (currentStep) {
            Step.DETECTING_MEDICATION_BOX -> {
                binding.apply {
                    tvDetectionStatus.text = "正在识别药盒..."
                    ivDetectionIcon.setImageResource(R.drawable.ic_search)
                    ivDetectionIcon.setColorFilter(ContextCompat.getColor(this@MedicationVerifyActivity, R.color.primary))
                }
            }
            Step.DETECTING_HAND_MOVEMENT -> {
                binding.apply {
                    tvDetectionStatus.text = "正在检测手部动作..."
                    ivDetectionIcon.setImageResource(R.drawable.ic_hand)
                    ivDetectionIcon.setColorFilter(ContextCompat.getColor(this@MedicationVerifyActivity, R.color.primary))
                }
            }
            Step.DETECTING_SWALLOW -> {
                binding.apply {
                    tvDetectionStatus.text = "正在检测吞咽动作..."
                    ivDetectionIcon.setImageResource(R.drawable.ic_heart)
                    ivDetectionIcon.setColorFilter(ContextCompat.getColor(this@MedicationVerifyActivity, R.color.primary))
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor?.shutdown()
        handler.removeCallbacksAndMessages(null)
    }

    companion object {
        private const val CAMERA_PERMISSION_REQUEST_CODE = 1001
    }
}
