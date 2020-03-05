package zjgsu.is.qff.homework01;

public class MyClassmate {
    public String getInfo(String title) {
        String result;
        switch (title) {
            case "我自己":
                result = "1712190426 真大龙";
                break;
            case "班长":
                result = "1712190426 钱非凡";
                break;
            case "学委":
                result = "1712190201 袁玲玲";
                break;
            case "团支书":
                result = "1712190410 贾潇风";
                break;
            default:
                result = "我爱安全";
        }
        return result;
    }
}
