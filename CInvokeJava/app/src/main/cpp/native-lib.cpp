#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_cn_b3ale_cinvokejava_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT void JNICALL
Java_cn_b3ale_cinvokejava_MainActivity_cJava(JNIEnv *env, jobject thiz) {
    // Find class
    // jclass FindClass(const char* name)
    jclass clazz = env->FindClass("cn/b3ale/cinvokejava/MainActivity");
    // Find Method
    // jmethodID GetMethodID(jclass clazz, const char* name, const char* sig)
    jmethodID method = env->GetMethodID(clazz, "logPrint", "()V");
    // Create instance
    // jobject AllocObject(jclass clazz)
    jobject obj = env->AllocObject(clazz);
    // Invoke object
    // void CallVoidMethod(jobject obj, jmethodID methodID, ...)
    env->CallVoidMethod(obj, method);
}
