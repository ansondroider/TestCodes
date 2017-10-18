package testcodes.base;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by anson on 15-10-14.
 */
public class SocketTest {
    public static void testSendUDP(String content, String ip, int port){
        byte[] buf = content.getBytes();
        int len = buf.length;

        DatagramPacket pack = new DatagramPacket(buf, len);
        try {
            pack.setAddress(InetAddress.getByName(ip));
            pack.setPort(port);
            DatagramSocket sock = new DatagramSocket();
            sock.send(pack);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
