#include <jni.h>
#include <string>
#include <string.h>
#include <android/log.h>

#ifndef LOG_TAG
#define LOG_TAG "DEBUG"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN, LOG_TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)
#define LOGF(...) __android_log_print(ANDROID_LOG_FATAL, LOG_TAG, __VA_ARGS__)
#endif

extern "C" JNIEXPORT jstring JNICALL
Java_cn_b3ale_jnianother_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

/*
 * Return sum of two numbers using C
 * @param a: the first number
 * @param b: the second number
 * @return: a + b
 */
extern "C"
JNIEXPORT jint JNICALL
Java_cn_b3ale_jnianother_MainActivity_addInt(
        JNIEnv *env,
        jobject thiz,
        jint a, jint b) {
    int sum = a + b;
    return sum;
}

/*
 * Return string using C
 * @param a: input string
 * @return: "This is your string: " + a
 */
extern "C"
JNIEXPORT jstring JNICALL
Java_cn_b3ale_jnianother_MainActivity_addString(
        JNIEnv *env,
        jobject thiz,
        jstring a) {
    const char *inputString = env->GetStringUTFChars(a, 0);
    const char *prev = "This is your string: ";
    int len1 = strlen(inputString), len2 = strlen(prev);
    int length = len1 + len2;
    char *result = (char *)malloc(length);
    strcpy(result, prev);
    strcat(result , inputString);
    return env->NewStringUTF(result);
}

/*
 * Check username and password using C
 * @param a: the first string
 * @param b: the second string
 * @return 1 if a[i] + 3 == b[i] else 0
 */
extern "C"
JNIEXPORT jint JNICALL
Java_cn_b3ale_jnianother_MainActivity_checkPwd(
        JNIEnv *env,
        jobject thiz,
        jstring a, jstring b) {
    const char *username = env->GetStringUTFChars(a, 0);
    const char *password = env->GetStringUTFChars(b, 0);
    if (strlen(username) != strlen(password)) {
        LOGD("[!] Length not equal...");
        return 0;
    }
    for (int i = 0; i < strlen(username); i++) {
        if (username[i] + 3 != password[i]) {
            LOGD("[!] Compare failed...");
            return 0;
        }
    }
    LOGD("[*] Compare successful!");
    return 1;
}