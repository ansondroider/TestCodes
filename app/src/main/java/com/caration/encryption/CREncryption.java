package com.caration.encryption;

/**
 * Created by anson on 15-9-16.
 */
public class CREncryption {
    static{
        System.loadLibrary("crencryption");
    }

    public native boolean isEncryption();

}
