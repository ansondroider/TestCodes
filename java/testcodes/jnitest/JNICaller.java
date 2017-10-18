package testcodes.jnitest;

/**
 * Created by anson on 15-10-13.
 * JNI path /home/anson/devEnv/android/rk3188_jb422/packages/anson/JNICaller/
 */
public class JNICaller {
    static{
        System.loadLibrary("JNICaller");
    }

    public String mPath = "anson.lai4";
    public int format = 4;

    public static native int matchString(String pattern);
    public native void getCallerStringField();
    public native void getCallerIntField();
    public static native int testInitUri();
}
