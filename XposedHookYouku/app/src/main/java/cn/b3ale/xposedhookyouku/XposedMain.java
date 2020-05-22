package cn.b3ale.xposedhookyouku;

import android.app.Application;
import android.content.Context;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

// https://www.wxwenku.com/d/106409039
// http://gttiankai.github.io/2017/08/31/%E7%BC%96%E5%86%99xposed%E6%A8%A1%E5%9D%97hook%E5%A4%9Adex%E7%9A%84apk/
// https://www.jianshu.com/p/8742dbf5ec3a
public class XposedMain implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        XposedHelpers.findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                ClassLoader classLoader = ((Context)param.args[0]).getClassLoader();
                Class<?> hookClass = null;
                try {
                    hookClass = classLoader.loadClass("com.youku.upsplayer.module.VideoInfo");
                    XposedBridge.log("[+] Find com.youku.upsplayer.module.VideoInfo!");
                    XposedHelpers.findAndHookMethod(hookClass, "getAd", new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log("[+] After Hooked Method.");
                            param.setResult(null);
                            XposedBridge.log("[+] Clear ad!");
                        }
                    });
//                    XposedHelpers.findAndHookMethod(hookClass, "setAd", String.class, new XC_MethodHook() {
//                        @Override
//                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                            XposedBridge.log("[+] Before Hooked Method.");
//                            param.args[0] = null;
//                            XposedBridge.log("[+] Set ad = NULL!");
//                        }
//                    });
                } catch (Exception e) {
//                    XposedBridge.log("[!] Can not find com.youku.upsplayer.module.VideoInfo...");
//                    XposedBridge.log(e.getMessage());
                }
            }
        });
    }
}
