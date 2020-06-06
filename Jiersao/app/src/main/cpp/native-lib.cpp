#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_cn_b3ale_jiersao_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jboolean JNICALL
Java_cn_b3ale_jiersao_MainActivity_checkPwd(JNIEnv *env, jobject thiz, jstring username,
                                            jstring password) {
    char *name = (char *)env->GetStringUTFChars(username, 0);
    for (int i = 0; i < strlen(name); i++) {
        name[i] += 1;
    }
    return strcmp(name, env->GetStringUTFChars(password, 0)) == 0;
}
