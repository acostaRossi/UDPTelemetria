import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TelemetryAgent {

    public static void main(String[] args) {

        while(true) {
            try {
                SystemInfo si = new SystemInfo();
                GlobalMemory gmemory = si.getHardware().getMemory();
                long totalMemory = gmemory.getTotal();
                long usedMemory = totalMemory - gmemory.getAvailable();
                String os = si.getOperatingSystem().getFamily();
                long time = System.currentTimeMillis();
                String s = time + ";" + totalMemory + ";" + usedMemory + ";" + os;
                byte[] buffer = s.getBytes();
                InetAddress address = InetAddress.getByName("127.0.0.1");
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 9000);
                DatagramSocket socket = new DatagramSocket();
                socket.send(packet);

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
