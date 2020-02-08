package com.assassinq.cydiajavahook;

import android.util.Log;

import com.saurik.substrate.MS;

import java.lang.reflect.Method;

public class CydiaMain {
    static void initialize() {
        // Hook IMEI
        MS.hookClassLoad("android.telephony.TelephonyManager", new MS.ClassLoadHook() {
            @Override
            public void classLoaded(Class<?> resources) {
                Method getDeviceId;
                try {
                    getDeviceId = resources.getMethod("getDeviceId");
                } catch (Exception e) {
                    getDeviceId = null;
                }
                if (getDeviceId != null) {
                    final  MS.MethodPointer old = new MS.MethodPointer();
                    MS.hookMethod(resources, getDeviceId, new MS.MethodHook() {
                        @Override
                        public Object invoked(Object res, Object... args) throws Throwable {
                            String id = null;
                            try {
                                id = (String) old.invoke(res, args);
                                Log.d("DEBUG", "getDeviceId" + id);
                                id = "assassinqkeepshumble";
                            } catch (Exception e) {
                                Log.d("DEBUG", "Hook Error: " + e);
                            }
                            return id;
                        }
                    }, old);
                }
            }
        });
        // Hook System Color
        MS.hookClassLoad("android.content.res.Resources", new MS.ClassLoadHook() {
            @Override
            public void classLoaded(Class<?> resources) {
                Method getColor;
                try {
                    getColor = resources.getMethod("getColor", Integer.TYPE);
                } catch (Exception e) {
                    getColor = null;
                }
                if (getColor != null) {
                    final MS.MethodPointer old = new MS.MethodPointer();
                    MS.hookMethod(resources, getColor, new MS.MethodHook() {
                        @Override
                        public Object invoked(Object res, Object... args) throws Throwable {
                            int color = 0;
                            try {
                                color = (Integer) old.invoke(res, args);
                                Log.d("DEBUG", "getColor" + Integer.toHexString(color));
                                color = color & ~0xFFFFFF | 0x00AAAA;
                            } catch (Exception e) {
                                Log.d("DEBUG", "Hook Error: " + e);
                            }
                            return color;
                        }
                    }, old);
                }
            }
        });
    }
}
