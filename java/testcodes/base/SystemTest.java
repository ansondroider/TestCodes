package testcodes.base;

import com.anson.acode.ALog;

/**
 * Created by anson on 15-9-11.
 */
public class SystemTest {

    public static void testArrayCopy(){
        byte[] src = {0x0, 0x1, 0x2, 0x3, 0x4, 0x5, 0x6, 0x7, 0x8, 0x9, 0xa,};
        byte[] dest = new byte[5];

        System.arraycopy(src, 4, dest, 0, dest.length);

        ALog.logBytes(dest);
    }
}
