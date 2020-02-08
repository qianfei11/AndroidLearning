package com.assassinq.xposedjavahook;

import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static android.os.Debug.waitForDebugger;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class XposedMain implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam param) throws Throwable {
//        if (param.packageName.equals("apk")) {
            try {
                findAndHookMethod("android.content.res.Resources", param.classLoader, "getColor", int.class, new myGetColor());
                findAndHookMethod("android.telephony.TelephonyManager", param.classLoader, "getDeviceId", new myGetDeviceId());
//                waitForDebugger();
            } catch (Exception e) {
                XposedBridge.log(e);
            }
//        }
    }
}

// Hook getColor
class myGetColor extends XC_MethodHook {
    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        Log.d("DEBUG", "Before Hook getColor");
    }

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        Log.d("DEBUG", "After Hook getColor");
        int res = (int) param.getResult();
        res = res & ~0xFFFFFF | 0x00AAAA;
        param.setResult(res);
    }
}

// Hook getDeviceId
class myGetDeviceId extends XC_MethodHook {
    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        Log.d("DEBUG", "Before Hook getDeviceId");
    }

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        Log.d("DEBUG", "After Hook getDeviceId");
        String res = (String) param.getResult();
        Log.d("DEBUG", "IMEI: " + res);
        res = "assassinqkeepshumble";
        Log.d("DEBUG", "IMEI: " + res);
        param.setResult(res);
    }
}
