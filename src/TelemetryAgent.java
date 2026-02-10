import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TelemetryAgent {

    public static void main(String[] args) {

        try {
            for(int i = 0; i < 10000; i++) {
                String s = "Ciao Server sono l'Agent " + i;
                byte[] buffer = s.getBytes();
                InetAddress address = InetAddress.getByName("127.0.0.1");
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 9000);
                DatagramSocket socket = new DatagramSocket();
                socket.send(packet);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
