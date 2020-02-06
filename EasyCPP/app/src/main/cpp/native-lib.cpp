#include <jni.h>
#include <string>
#include "log.h"

extern "C" {
JNIEXPORT jstring JNICALL
Java_com_assassinq_easycpp_MainActivity_sayHello(JNIEnv *env, jobject obj) {
    return env->NewStringUTF("Hello World");
}

jstring sayHi(JNIEnv *env, jobject obj) {
    return env->NewStringUTF("Hi World");
}

static int registerNativeMethods(JNIEnv *env, const char *className, JNINativeMethod *gMethods,
                                 int numMethods) {
    jclass clazz;
    clazz = env->FindClass(className);
    if (clazz == NULL) {
        return JNI_FALSE;
    }
    if (env->RegisterNatives(clazz, gMethods, numMethods) < 0) {
        return JNI_FALSE;
    }
    return JNI_TRUE;
}

static const char *gClassName = "com/assassinq/easycpp/MainActivity";
static JNINativeMethod gMethods[] = {
        {"sayHi", "()Ljava/lang/String;", (void *) sayHi},
};

JNIEXPORT jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *env = NULL;
    if (vm->GetEnv((void **) &env, JNI_VERSION_1_6) != JNI_OK) {
        LOGE("This jni version is not supported");
        return -1;
    }
    if (registerNativeMethods(env, gClassName, gMethods, sizeof(gMethods) / sizeof(gMethods[0])) ==
        JNI_FALSE) {
        LOGE("Unable to register native methods");
        return -1;
    }
    LOGE("Methods loaded successfully");
    return JNI_VERSION_1_6;
}
}