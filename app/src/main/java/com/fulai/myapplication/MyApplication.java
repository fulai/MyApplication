package com.fulai.myapplication;

import android.app.ActivityManager;
import android.app.Application;
import android.os.Process;

import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;

import java.util.List;

/**
 * Created by Dengmao on 17/8/9.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initSophix();
    }

    private void initSophix() {
        if (mainProcess()) {
            String appVersion;
            try {
                appVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            } catch (Exception e) {
                appVersion = "1.0.0.0";
            }

            // initialize最好放在attachBaseContext最前面
            SophixManager.getInstance().setContext(this)
                    .setAppVersion(appVersion)
                    .setAesKey(null)
                    .setEnableDebug(true)
                    .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                        @Override
                        public void onLoad(final int mode, final int code, final String info, final int
                                handlePatchVersion) {
                            // 补丁加载回调通知
                            if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                                // 表明补丁加载成功
                            } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                                // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                                // 建议: 用户可以监听进入后台事件, 然后应用自杀，以此加快应用补丁
                                // 建议调用killProcessSafely，详见1.3.2.3
                                // SophixManager.getInstance().killProcessSafely();
                            } else if (code == PatchStatus.CODE_LOAD_FAIL) {
                                // 内部引擎异常, 推荐此时清空本地补丁, 防止失败补丁重复加载
                                // SophixManager.getInstance().cleanPatches();
                            } else {
                                // 其它错误信息, 查看PatchStatus类说明
                            }
                        }
                    }).initialize();
            SophixManager.getInstance().queryAndLoadNewPatch();
        }
    }

    private boolean mainProcess() {
        String packageName = getPackageName();
        String processName = null;
        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : runningAppProcesses) {
            if (info.pid == Process.myPid()) {
                processName = info.processName;
                break;
            }
        }
        if (packageName.equals(processName)) {
            return true;
        }
        return false;
    }
}
