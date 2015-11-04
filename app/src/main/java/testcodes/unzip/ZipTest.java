package testcodes.unzip;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipFile;

/**
 * Created by anson on 15-10-29.
 */
public class ZipTest {

    public static void unzipFile(){
        try {
            ZipFile zFile = new ZipFile(new File("/mnt/sdcard/test.zip"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
