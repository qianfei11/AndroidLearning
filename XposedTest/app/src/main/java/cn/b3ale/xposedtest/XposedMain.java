package cn.b3ale.xposedtest;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XposedMain implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (loadPackageParam.packageName.equals("com.example.user.login")) { // 定位包名
            XposedBridge.log("[+] Package found!");
            Class clazz = loadPackageParam.classLoader.loadClass("com.example.user.login.MainActivity"); // 定位类名
            XposedHelpers.findAndHookMethod(clazz, "CheckIDPwd", String.class, String.class, new XC_MethodHook() { // Hook指定方法，并设置传入参数
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable { // 在调用方法前Hook
//                    super.beforeHookedMethod(param);
//                    XposedBridge.log("[+] Before Hooked Method");
//                    param.args[0] = "b3ale";
//                    param.args[1] = "1e4c6a18edc3adb0569a9af2740f3cfc5000b0e0d2eaefeaf8667ced6b003c21";
//                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable { // 在调用方法后Hook
                    super.afterHookedMethod(param);
                    XposedBridge.log("[+] After Hooked Method");
                    param.setResult(true);
                }
            });
        } else {
            XposedBridge.log("[!] Package not found...");
        }
    }
}
