package testcodes.base;

import java.nio.ByteBuffer;
import java.util.Random;

/**
 * Created by anson on 15-9-9.
 */
public class ByteBufferTest {
    public static void testByteBufferFIFO(){
        int size = 100 * 104;
        int loopCount = 100;

        ByteBuffer byteBuf = ByteBuffer.allocate(size);

        for(int i = 0; i < loopCount; i ++){
            byteBuf.put(getRandomBytes(100));
        }
        LOG.logByteArrayBinary(byteBuf.array());
    }

    public static byte[] getRandomBytes(int size){
        byte[] data = new byte[size + 4];
        //data[0] = (byte)((0xFF000000 & size) >> 24);
        //data[1] = (byte)((0x00FF0000 & size) >> 16);
        //data[2] = (byte)((0x0000FF00 & size) >> 8);
        //data[3] = (byte)((0xFF & size));
        data[0] = data[1] = data[2] = data[3] = 0x00;
        Random r = new Random(size);
        for(int i = 0; i < size; i++){
            data[i + 4] = (byte)r.nextInt();
        }

        return data;
    }
}
