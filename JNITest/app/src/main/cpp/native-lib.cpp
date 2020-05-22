#include <jni.h>
#include <string>

extern "C" JNIEXPORT jlong JNICALL
Java_cn_b3ale_jnitest_MainActivity_longFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    unsigned long num = 1712190426;
    return num * 2;
}
