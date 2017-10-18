package com.caration.encryption;

import com.anson.acode.ALog;

/**
 * Created by anson on 15-9-16.
 */
public class CREncryption {
    static{
        System.loadLibrary("crencryption");
    }

    public native boolean isEncryption();

    public static void testEncryption(){
        CREncryption cre = new CREncryption();
        ALog.d("isEncryption = " + cre.isEncryption());
    }

}
