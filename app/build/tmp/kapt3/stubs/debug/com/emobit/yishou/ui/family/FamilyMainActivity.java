package com.emobit.yishou.ui.family;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import com.emobit.yishou.R;
import com.emobit.yishou.databinding.ActivityFamilyMainBinding;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * 子女监护端主Activity
 * 包含底部导航栏和Fragment容器
 */
@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0014J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/emobit/yishou/ui/family/FamilyMainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/emobit/yishou/databinding/ActivityFamilyMainBinding;", "navController", "Landroidx/navigation/NavController;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onSupportNavigateUp", "", "setupNavigation", "app_debug"})
public final class FamilyMainActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.emobit.yishou.databinding.ActivityFamilyMainBinding binding;
    private androidx.navigation.NavController navController;
    
    public FamilyMainActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupNavigation() {
    }
    
    @java.lang.Override()
    public boolean onSupportNavigateUp() {
        return false;
    }
}