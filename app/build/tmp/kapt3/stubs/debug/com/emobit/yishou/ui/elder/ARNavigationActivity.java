package com.emobit.yishou.ui.elder;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.emobit.yishou.R;
import com.emobit.yishou.databinding.ActivityArNavigationBinding;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * AR实景导航页面
 * 使用摄像头提供AR实景导航，3D数字人引导
 */
@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0002J\u0012\u0010\t\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0014J\b\u0010\f\u001a\u00020\bH\u0002J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/emobit/yishou/ui/elder/ARNavigationActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/emobit/yishou/databinding/ActivityArNavigationBinding;", "isWandering", "", "hideWanderingAlert", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupClickListeners", "setupNavigation", "destination", "", "showWanderingAlert", "app_debug"})
public final class ARNavigationActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.emobit.yishou.databinding.ActivityArNavigationBinding binding;
    private boolean isWandering = false;
    
    public ARNavigationActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupNavigation(java.lang.String destination) {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void showWanderingAlert() {
    }
    
    private final void hideWanderingAlert() {
    }
}