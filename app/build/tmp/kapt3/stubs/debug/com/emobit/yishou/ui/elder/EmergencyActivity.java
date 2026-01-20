package com.emobit.yishou.ui.elder;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.emobit.yishou.R;
import com.emobit.yishou.databinding.ActivityEmergencyBinding;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * 紧急求助页面
 * 跌倒检测、迷路预警、SOS呼叫
 */
@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\u0012\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\b\u0010\u000e\u001a\u00020\nH\u0014J\b\u0010\u000f\u001a\u00020\nH\u0002J\b\u0010\u0010\u001a\u00020\nH\u0002J\b\u0010\u0011\u001a\u00020\nH\u0002J\b\u0010\u0012\u001a\u00020\nH\u0002J\b\u0010\u0013\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/emobit/yishou/ui/elder/EmergencyActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/emobit/yishou/databinding/ActivityEmergencyBinding;", "countDownTimer", "Landroid/os/CountDownTimer;", "isEmergencyMode", "", "makeEmergencyCall", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "setupClickListeners", "showFallDetectedUI", "showNormalUI", "showSafeConfirmation", "startCountdown", "app_debug"})
public final class EmergencyActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.emobit.yishou.databinding.ActivityEmergencyBinding binding;
    @org.jetbrains.annotations.Nullable()
    private android.os.CountDownTimer countDownTimer;
    private boolean isEmergencyMode = false;
    
    public EmergencyActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void showNormalUI() {
    }
    
    private final void showFallDetectedUI() {
    }
    
    private final void startCountdown() {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void makeEmergencyCall() {
    }
    
    private final void showSafeConfirmation() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
}