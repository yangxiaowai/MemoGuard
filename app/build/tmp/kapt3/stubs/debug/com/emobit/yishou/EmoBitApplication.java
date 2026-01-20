package com.emobit.yishou;

import android.app.Application;
import dagger.hilt.android.HiltAndroidApp;

/**
 * EmoBit·忆守 应用程序入口
 * 基于时空智能与数字孪生的认知障碍代偿系统
 */
@dagger.hilt.android.HiltAndroidApp()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016\u00a8\u0006\u0006"}, d2 = {"Lcom/emobit/yishou/EmoBitApplication;", "Landroid/app/Application;", "()V", "onCreate", "", "Companion", "app_debug"})
public final class EmoBitApplication extends android.app.Application {
    private static com.emobit.yishou.EmoBitApplication instance;
    @org.jetbrains.annotations.NotNull()
    public static final com.emobit.yishou.EmoBitApplication.Companion Companion = null;
    
    public EmoBitApplication() {
        super();
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/emobit/yishou/EmoBitApplication$Companion;", "", "()V", "<set-?>", "Lcom/emobit/yishou/EmoBitApplication;", "instance", "getInstance", "()Lcom/emobit/yishou/EmoBitApplication;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.emobit.yishou.EmoBitApplication getInstance() {
            return null;
        }
    }
}