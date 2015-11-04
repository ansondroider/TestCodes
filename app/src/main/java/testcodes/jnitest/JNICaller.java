package testcodes.jnitest;

/**
 * Created by anson on 15-10-13.
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


}
